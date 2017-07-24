package com.johnny.jdk.compilerApi;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 * <p>
 * Author: yang_tao
 * Date  : 2017-07-18 15:02
 */
public class FileManageImpl extends ForwardingJavaFileManager<JavaFileManager> {

    private final ClassLoadImpl classLoader;

    // internal map of filename URIs to JavaFileObject
    private final Map<URI, JavaFileObject> fileObjectMap = new HashMap<>();

    public FileManageImpl(JavaFileManager fileManager, ClassLoadImpl classLoader) {
        super(fileManager);
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    @Override
    public FileObject getFileForInput(Location location, String packageName, String relativeName) throws IOException {
        FileObject fileObject = fileObjectMap.get(uri(location, packageName, relativeName));
        if (fileObject != null){
            return fileObject;
        }
        return super.getFileForInput(location, packageName, relativeName);
    }

    public void putFileForInput(StandardLocation location, String packageName, String relativeName, JavaFileObject fileObject){
        fileObjectMap.put(uri(location, packageName, relativeName), fileObject);
    }

    private URI uri(Location location, String packageName, String relativeName) {
        return JavaFileObjectImpl.parseURI(location.getName() + "/" + packageName + "/" + relativeName);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, Kind kind, FileObject sibling) throws IOException {
        JavaFileObject file = new JavaFileObjectImpl(className, kind);
        classLoader.add(className, file);
        return file;
    }

    @Override
    public ClassLoader getClassLoader(Location location) {
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
            for (JavaFileObject file : fileObjectMap.values()) {
                if (file.getKind() == Kind.CLASS && file.getName().startsWith(packageName))
                    files.add(file);
            }
            files.addAll(classLoader.files());
        } else if (location == StandardLocation.SOURCE_PATH && kinds.contains(JavaFileObject.Kind.SOURCE)) {
            for (JavaFileObject file : fileObjectMap.values()) {
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
