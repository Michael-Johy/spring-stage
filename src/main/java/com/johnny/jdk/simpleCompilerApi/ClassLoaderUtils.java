package com.johnny.jdk.simpleCompilerApi;

import java.io.File;
import java.net.URL;

/**
 * Description:
 * <p>
 * Author: yang_tao
 * Date  : 2017-08-11 13:40
 */
public class ClassLoaderUtils {

    public static String buildClassPath(URL[] urls) {
        StringBuffer buffer = new StringBuffer();
        for (URL url : urls) {
            buffer.append(new File(url.getPath()));
            buffer.append(System.getProperty("path.separator"));
        }
        String cp = buffer.toString();

        int toIndex = cp
                .lastIndexOf(System.getProperty("path.separator"));
        cp = cp.substring(0, toIndex);
        return cp;
    }

}
