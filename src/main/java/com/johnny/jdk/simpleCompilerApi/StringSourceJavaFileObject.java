package com.johnny.jdk.simpleCompilerApi;

import org.apache.commons.lang3.StringUtils;

import javax.tools.SimpleJavaFileObject;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.URI;

/**
 * Description:
 * <p>
 * Author: yang_tao
 * Date  : 2017-08-11 10:32
 */
public class StringSourceJavaFileObject extends SimpleJavaFileObject {

    private String code;

    /**
     * Constructs StringSourceJavaFileObject
     *
     * @param fullName the name for the compilation unit represented by this file object eg:com.ctrip.risk.Test
     * @param code     the source code for the compilation unit represented by this file object
     */
    public StringSourceJavaFileObject(@NotNull String fullName, @NotNull String code) {
        super(URI.create("file:///" + StringUtils.replace(fullName, ".", "/") + Kind.SOURCE.name()),
                Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return null;
    }
}
