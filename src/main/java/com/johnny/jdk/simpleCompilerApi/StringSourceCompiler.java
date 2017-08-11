package com.johnny.jdk.simpleCompilerApi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

/**
 * Date: 2016年2月5日 下午1:02:10 <br/>
 */
public class StringSourceCompiler {
    static final String JAVA_EXTENSION = ".java";
    // 用于存放javac命令对应的选项，比如'-target' '1.5'
    private final List<String> options;
    // 真正执行编译过程的编译器
    private final JavaCompiler compiler;
    // 自定义实现，用于管理编译相关文件
    private final FileManagerImpl javaFileManager;
    // 诊断信息收集器
    private DiagnosticCollector<JavaFileObject> diagnostics;
    // 自定义类加载器
    private final ClassLoaderImpl classLoader;

    public StringSourceCompiler(ClassLoader parentLoader, List<String> options) {
        this.options = options;
        // 使用系统默认的编译器
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.classLoader = new ClassLoaderImpl(parentLoader);
        this.diagnostics = new DiagnosticCollector<JavaFileObject>();
        JavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        // 创建文件管理器
        this.javaFileManager = new FileManagerImpl(fileManager, classLoader);
    }

    //编译方法
    public Class<?> compile(String classFullName, String sourceCode) throws Exception {
        /*
         * 首先将sourceCode转换成CompilerAPI中对应的抽象表示，然后放入javaFileManager管理
         */
        int dotPos = classFullName.lastIndexOf('.');
        String className = dotPos == -1 ? classFullName : classFullName.substring(dotPos + 1);
        String packageName = dotPos == -1 ? "" : classFullName.substring(0, dotPos);
        JavaFileObjectImpl javaFileObject = new JavaFileObjectImpl(className, sourceCode);
        javaFileManager.putFileForInput(StandardLocation.SOURCE_PATH, packageName, className + JAVA_EXTENSION, javaFileObject);
        /*
         * 执行编译过程
         */
        List<JavaFileObject> sources = new ArrayList<JavaFileObject>();
        sources.add(javaFileObject);
        // 获取Task并执行
        CompilationTask task = compiler.getTask(null, javaFileManager, diagnostics, options, null, sources);
        Boolean result = task.call();
        if (!result) {
            List<Diagnostic<? extends JavaFileObject>> list = diagnostics.getDiagnostics();
            for (Diagnostic<? extends JavaFileObject> diagnostic : list) {
                System.out.println(diagnostic);
            }
        }
        /*
         * 获取Class实例
         */
        return classLoader.loadClass(classFullName);
    }

    static URI toURI(String name) {
        try {
            return new URI(name);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    final class JavaFileObjectImpl extends SimpleJavaFileObject {
        // If kind == CLASS, this stores byte code from openOutputStream
        private ByteArrayOutputStream byteCode;
        // 存放java源码
        private final CharSequence source;

        JavaFileObjectImpl(final String baseName, final CharSequence source) {
            super(StringSourceCompiler.toURI(baseName + StringSourceCompiler.JAVA_EXTENSION), Kind.SOURCE);
            this.source = source;
        }

        JavaFileObjectImpl(final String name, final Kind kind) {
            super(StringSourceCompiler.toURI(name), kind);
            source = null;
        }

        /**
         * 返回java源码
         *
         * @see javax.tools.SimpleJavaFileObject#getCharContent(boolean)
         */
        @Override
        public CharSequence getCharContent(final boolean ignoreEncodingErrors) throws UnsupportedOperationException {
            if (source == null)
                throw new UnsupportedOperationException("getCharContent()");
            return source;
        }

        /**
         * Return an input stream for reading the byte code
         *
         * @see javax.tools.SimpleJavaFileObject#openInputStream()
         */
        @Override
        public InputStream openInputStream() {
            return new ByteArrayInputStream(getByteCode());
        }

        /**
         * Return an output stream for writing the bytecode
         *
         * @see javax.tools.SimpleJavaFileObject#openOutputStream()
         */
        @Override
        public OutputStream openOutputStream() {
            byteCode = new ByteArrayOutputStream();
            return byteCode;
        }

        /**
         * @return the byte code generated by the compiler
         */
        byte[] getByteCode() {
            return byteCode.toByteArray();
        }
    }

    final class FileManagerImpl extends ForwardingJavaFileManager<JavaFileManager> {
        private final ClassLoaderImpl classLoader;
        // Internal map of filename URIs to JavaFileObjects.
        private final Map<URI, JavaFileObject> fileObjects = new HashMap<URI, JavaFileObject>();

        public FileManagerImpl(JavaFileManager fileManager, ClassLoaderImpl classLoader) {
            super(fileManager);
            this.classLoader = classLoader;
        }

        public ClassLoader getClassLoader() {
            return classLoader;
        }

        @Override
        public FileObject getFileForInput(Location location, String packageName, String relativeName) throws IOException {
            FileObject o = fileObjects.get(uri(location, packageName, relativeName));
            if (o != null)
                return o;
            return super.getFileForInput(location, packageName, relativeName);
        }

        public void putFileForInput(StandardLocation location, String packageName, String relativeName, JavaFileObject file) {
            fileObjects.put(uri(location, packageName, relativeName), file);
        }

        private URI uri(Location location, String packageName, String relativeName) {
            return StringSourceCompiler.toURI(location.getName() + '/' + packageName + '/' + relativeName);
        }

        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String qualifiedName, Kind kind, FileObject outputFile) throws IOException {
            JavaFileObject file = new JavaFileObjectImpl(qualifiedName, kind);
            classLoader.add(qualifiedName, file);
            return file;
        }

        @Override
        public ClassLoader getClassLoader(JavaFileManager.Location location) {
            return classLoader;
        }

        @Override
        public String inferBinaryName(Location loc, JavaFileObject file) {
            String result;
            // For our JavaFileImpl instances, return the file's name, else
            // simply run the default implementation
            if (file instanceof JavaFileObjectImpl)
                result = file.getName();
            else
                result = super.inferBinaryName(loc, file);
            return result;
        }

        @Override
        public Iterable<JavaFileObject> list(Location location, String packageName, Set<Kind> kinds, boolean recurse) throws IOException {
            Iterable<JavaFileObject> result = super.list(location, packageName, kinds, recurse);
            ArrayList<JavaFileObject> files = new ArrayList<JavaFileObject>();
            if (location == StandardLocation.CLASS_PATH && kinds.contains(JavaFileObject.Kind.CLASS)) {
                for (JavaFileObject file : fileObjects.values()) {
                    if (file.getKind() == Kind.CLASS && file.getName().startsWith(packageName))
                        files.add(file);
                }
                files.addAll(classLoader.files());
            } else if (location == StandardLocation.SOURCE_PATH && kinds.contains(JavaFileObject.Kind.SOURCE)) {
                for (JavaFileObject file : fileObjects.values()) {
                    if (file.getKind() == Kind.SOURCE && file.getName().startsWith(packageName))
                        files.add(file);
                }
            }
            for (JavaFileObject file : result) {
                files.add(file);
            }
            return files;
        }
    }

    /**
     * 自定义ClassLoader实现
     */
    final class ClassLoaderImpl extends ClassLoader {
        // 存放类名与对应的class文件抽象
        private final Map<String, JavaFileObject> classes = new HashMap<String, JavaFileObject>();

        ClassLoaderImpl(final ClassLoader parentClassLoader) {
            super(parentClassLoader);
        }

        /**
         * @return An collection of JavaFileObject instances for the classes in
         * the class loader.
         */
        Collection<JavaFileObject> files() {
            return Collections.unmodifiableCollection(classes.values());
        }

        @Override
        protected Class<?> findClass(final String qualifiedClassName) throws ClassNotFoundException {
            JavaFileObject file = classes.get(qualifiedClassName);
            if (file != null) {
                byte[] bytes = ((JavaFileObjectImpl) file).getByteCode();
                return defineClass(qualifiedClassName, bytes, 0, bytes.length);
            }
            // Workaround for "feature" in Java 6
            // see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6434149
            try {
                Class<?> c = Class.forName(qualifiedClassName);
                return c;
            } catch (ClassNotFoundException nf) {
                // Ignore and fall through
            }
            return super.findClass(qualifiedClassName);
        }

        /**
         * Add a class name/JavaFileObject mapping
         *
         * @param qualifiedClassName the name
         * @param javaFile           the file associated with the name
         */
        void add(final String qualifiedClassName, final JavaFileObject javaFile) {
            classes.put(qualifiedClassName, javaFile);
        }

        @Override
        protected synchronized Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
            return super.loadClass(name, resolve);
        }

        @Override
        public InputStream getResourceAsStream(final String name) {
            if (name.endsWith(".class")) {
                String qualifiedClassName = name.substring(0, name.length() - ".class".length()).replace('/', '.');
                JavaFileObjectImpl file = (JavaFileObjectImpl) classes.get(qualifiedClassName);
                if (file != null) {
                    return new ByteArrayInputStream(file.getByteCode());
                }
            }
            return super.getResourceAsStream(name);
        }
    }
}