package com.johnny.jdk.compilerApi;

import javax.tools.JavaFileObject;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 * Author: yang_tao
 * Date  : 2017-07-18 15:03
 */
public class ClassLoadImpl extends ClassLoader {

    // 存放类名与对应的class文件抽象
    private final Map<String, JavaFileObject> classes = new HashMap<>();

    public ClassLoadImpl(ClassLoader parentClassLoader) {
        super(parentClassLoader);
    }

    /**
     * return an collection of JavaFileObject instances for the classes in the class loader
     * @return
     */
    public Collection<JavaFileObject> files(){
        return Collections.unmodifiableCollection(classes.values());
    }

    /**
     * @param name qualifiedClassName
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        JavaFileObject fileObject = classes.get(name);
        if (fileObject != null) {
            byte[] bytes = ((JavaFileObjectImpl) fileObject).getByteCode();
            return defineClass(name, bytes, 0, bytes.length);
        }
        try {
            Class<?> c = Class.forName(name);
            return c;
        } catch (ClassNotFoundException e) {
            //ignore and fall through
        }
        return super.findClass(name);
    }

    /**
     * Add a class name/JavaFileObject mapping
     *
     * @param name           qualifiedClassName
     * @param javaFileObject the file associated with the name
     */
    public void add(String name, JavaFileObject javaFileObject) {
        classes.put(name, javaFileObject);
    }

    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        if (name.endsWith(".class")) {
            String qualifiedClassName = name.substring(0, name.length() - ".class".length()).replace('/', '.');
            JavaFileObjectImpl fileObject = (JavaFileObjectImpl) classes.get(qualifiedClassName);
            if (fileObject != null) {
                return new ByteArrayInputStream(fileObject.getByteCode());
            }
        }
        return super.getResourceAsStream(name);
    }
}
