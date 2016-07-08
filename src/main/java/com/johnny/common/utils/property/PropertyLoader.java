package com.johnny.common.utils.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A PropertyLoader is associated with a property file defined as a class resource, and loads the
 * data from the resource when initiated
 * <p>
 * Created by johnny01.yang on 2016/7/8.
 */
public class PropertyLoader extends Properties {

    private String resourceName;

    public PropertyLoader(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * Reads a property list (key and value pairs) from the input byte stream.
     * The input stream is in a simple line-oriented format as specified in {@link #load(InputStream) load(InputStream)}
     * and is assumed to use the ISO-8859-1 charactor encoding; that is each byte is one Latinal character
     * <p>
     * If you would like to load more than one properties file, make sure keys
     * specified in different files differs by using name space
     */
    private void load() {
        try (InputStream is = PropertyLoader.class.getResourceAsStream(resourceName)) {
            if (is == null) {
                return;
            }
            this.load(is);
        } catch (IOException e) {
            throw new RuntimeException("failed to load configuration data from file : " + resourceName);
        }

    }
}
