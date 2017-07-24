package com.johnny.jdk.compilerApi;

import com.google.common.collect.Lists;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.util.List;

/**
 * Description: My Compiler
 * <p>
 * Author: yang_tao
 * Date  : 2017-07-18 14:28
 */
public class CustomerCompiler {
    // 用于存放javac命令对应的选项, 比如 '-target' '1.5'
    private final List<String> options;
    // 真正执行编译过程的编译器
    private final JavaCompiler compiler;
    // 自定义实现，用于管理编译相关文件
    private final FileManageImpl javaFileManage;
    // 诊断信息收集器
    private DiagnosticCollector<JavaFileObject> diagnostics;
    // 自定义类加载器
    private final ClassLoadImpl classLoad;


    public CustomerCompiler(ClassLoader parentLoader, List<String> options) {
        this.options = options;
        //使用系统默认编译器
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.classLoad = new ClassLoadImpl(parentLoader);
        this.diagnostics = new DiagnosticCollector<>();
        JavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        // 创建文件管理器
        this.javaFileManage = new FileManageImpl(fileManager, classLoad);
    }

    //编译方法
    public Class<?> compile(String classFullName, String sourceCode) throws Exception {
        /*
         * 首先将sourceCode转换成CompilerAPI中对应的抽象表示，然后放入javaFileManager管理
         */
        int dotPos = classFullName.lastIndexOf('.');
        String className = dotPos == -1 ? classFullName : classFullName.substring(dotPos + 1);
        String packageName = dotPos == -1 ? "" : classFullName.substring(1, dotPos);
        JavaFileObjectImpl javaFileObject = new JavaFileObjectImpl(className, sourceCode);
        javaFileManage.putFileForInput(StandardLocation.SOURCE_PATH, packageName, className + ".java", javaFileObject);
        /**
         * 执行编译过程
         */
        List<JavaFileObject> sources = Lists.newArrayList();
        sources.add(javaFileObject);
        // 获取task并执行
        CompilationTask task = compiler.getTask(null, javaFileManage, diagnostics, options, null, sources);
        Boolean result = task.call();
        if (!result) {
            List<Diagnostic<? extends JavaFileObject>> list = diagnostics.getDiagnostics();
            for (Diagnostic<? extends JavaFileObject> diagnostic : list) {
                System.out.println(diagnostic);
            }
        }
        /**
         * 获取Class实例
         */
        return classLoad.loadClass(classFullName);
    }


}
