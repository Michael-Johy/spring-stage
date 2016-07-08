package com.johnny.common.utils.property;

import com.johnny.common.constant.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by johnny01.yang on 2016/7/8.
 */
public class ConfigManager {

    private static final PropertyLoader properLocation = new PropertyLoader("/common-property.properties");

    private static final String PROP_LIST_KEY = "property.list";

    private static ConfigProperties configProperties = null;

    public static ConfigProperties getConfigProperties() {
        if (null == configProperties) {
            String propertyList = properLocation.getProperty(PROP_LIST_KEY);
            if (StringUtils.isNotBlank(propertyList)) {
                String[] files = propertyList.split(Constants.COMMA);
                configProperties = new ConfigProperties(files);
            }
        }
        return configProperties;
    }

    public static String getProperty(String key) {
        return getConfigProperties().getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return getConfigProperties().getProperty(key, defaultValue);
    }

    public static Integer getIntProperty(String key) {
        return getConfigProperties().getIntProperty(key);
    }

    public static Integer getIntProperty(String key, Integer defaultValue) {
        return getConfigProperties().getIntProperty(key, defaultValue);
    }

    public static Boolean getBoolProperty(String key) {
        return getConfigProperties().getBoolProperty(key);
    }

    public static Boolean getBoolProperty(String key, Boolean defaultValue) {
        return getConfigProperties().getBoolProperty(key, defaultValue);
    }

    public static void main(String[] args){
        System.out.println(ConfigManager.getIntProperty("mysql.jdbc.timeout"));
    }
}
