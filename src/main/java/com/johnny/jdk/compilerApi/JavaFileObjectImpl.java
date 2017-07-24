package com.johnny.jdk.compilerApi;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Description:  OK
 * <p>
 * Author: yang_tao
 * Date  : 2017-07-18 14:30
 */
public class JavaFileObjectImpl extends SimpleJavaFileObject {

    private static final String JAVA_EXTENSION = ".java";

    //if kind = CLASS, this stores byte code from openOutputstream
    private ByteArrayOutputStream byteCode;

    //if kind = .java, this stores source code
    private final String sourceCode;


    public JavaFileObjectImpl(String baseName, String sourceCode) {
        super(JavaFileObjectImpl.parseURI(baseName + JAVA_EXTENSION), Kind.SOURCE);
        this.sourceCode = sourceCode;
    }

    public JavaFileObjectImpl(String baseName, Kind kind) {
        super(JavaFileObjectImpl.parseURI(baseName), kind);
        sourceCode = null;
    }

    static URI parseURI(String name) {
        try {
            return new URI(name);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回Java源码文件内容
     *
     * @param ignoreEncodingErrors ignore encoding error
     * @return sourceFile content if .java
     * @throws IOException
     */
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        if (sourceCode == null) {
            throw new UnsupportedOperationException("getCharContent");
        }
        return sourceCode;
    }

    /**
     * return an input stream for reading the byte code
     * @return byte code as inputStream
     * @throws IOException
     */
    @Override
    public InputStream openInputStream() throws IOException {
        return new ByteArrayInputStream(getByteCode());
    }

    /**
     * return an output stream for writing the byte code
     * @return byte code as outputStream
     * @throws IOException
     */
    @Override
    public OutputStream openOutputStream() throws IOException {
        byteCode = new ByteArrayOutputStream();
        return byteCode;
    }



    public byte[] getByteCode() {
        return byteCode.toByteArray();
    }


}
