package com.johnny.jdk.simpleCompilerApi;

import com.johnny.jdk.simpleCompilerApi.testcase.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager.Location;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * Description: mainTest for fileManager
 * <p>
 * Author: yang_tao
 * Date  : 2017-08-11 10:00
 */
public class MainTest {

    private static final Logger logger = LoggerFactory.getLogger(MainTest.class);

    private static String CLASS_BASE_PATH = "D:\\base";

    private static String OBJECT_EXECUTOR_CODE = "package aa;\n" +
            "\n" +
            "/**\n" +
            " * Description:\n" +
            " * <p>\n" +
            " * Author: yang_tao\n" +
            " * Date  : 2017-08-11 10:42\n" +
            " */\n" +
            "public class ObjectExecutor<M extends Object> implements Executor {\n" +
            "\n" +
            "    private M object;\n" +
            "\n" +
            "    public ObjectExecutor(M mObject) {\n" +
            "        object = mObject;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void execute() {\n" +
            "        execute(object);\n" +
            "    }\n" +
            "\n" +
            "    private void execute(M object) {\n" +
            "        System.out.println(object);\n" +
            "    }\n" +
            "}\n";


    public static void main(String[] args) throws Exception {
        compiler();
//        execute();
    }

    public static void compiler() throws Exception {
        //get Compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //define the diagnostic object, which will be used to save the diagnostic information
        DiagnosticCollector<? super JavaFileObject> diagnosticCollector =
                new DiagnosticCollector<>();
        //get standard JavaFileManager with DiagnosticCollector
        StandardJavaFileManager standardJavaFileManager = compiler.getStandardFileManager(diagnosticCollector, null, null);

        Location location = StandardLocation.CLASS_OUTPUT;
        try {
            standardJavaFileManager.setLocation(location, Arrays.asList(new File(CLASS_BASE_PATH)));
            JavaFileObject javaFileObject = new StringSourceJavaFileObject("com.johnny.jdk.simpleCompilerApi.testcase.ObjectExecutor", OBJECT_EXECUTOR_CODE);

            Iterable<String> options = Arrays.asList("-classpath", getClassBasePath(), "-XDuseUnsharedTable");
            CompilationTask task = compiler.getTask(null, standardJavaFileManager, diagnosticCollector,
                    options, null, Arrays.asList(javaFileObject));
            boolean executed = task.call();

            //prints the Diagnostic's information
            for (Diagnostic diagnostic : diagnosticCollector.getDiagnostics()) {
                System.out.println(diagnostic.getLineNumber());
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("error");
        }
    }

    public static void execute() throws Exception {
        ClassLoader classLoader = MainTest.class.getClassLoader();
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new File(CLASS_BASE_PATH).toURI().toURL()}, classLoader);
        Class aClass = urlClassLoader.loadClass("aa.ObjectExecutor");
        Constructor constructor = aClass.getConstructor(Object.class);
        String strObj = "my name is yangtao ";
        Executor executor = (Executor) constructor.newInstance(strObj);
        executor.execute();
        System.out.println("====================");
        urlClassLoader.close();
    }

    public static String getClassBasePath() {
        ClassLoader classLoader = MainTest.class.getClassLoader();
        URL[] urls = ((URLClassLoader) classLoader).getURLs();
        String cp = ClassLoaderUtils.buildClassPath(urls);
        return cp;
    }
}
