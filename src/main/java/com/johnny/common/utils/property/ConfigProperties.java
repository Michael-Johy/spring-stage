package com.johnny.common.utils.property;

import com.johnny.common.constant.Constants;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.Vector;

/**
 * A ConfigPropertis holds as many as property files and corresponding line-oriented key-value pair in Memory.
 * <p>
 * <p>
 * Created by johnny01.yang on 2016/7/8.
 */
public class ConfigProperties {

    private static final Logger log = Logger.getLogger(ConfigProperties.class);

    private static Properties properties = new Properties();

    private static Vector<String> containedPropFile = new Vector<>();

    public ConfigProperties(String propertyFile) {
        if (!containedPropFile.contains(propertyFile)) {
            containedPropFile.add(propertyFile);
            properties.putAll(new PropertyLoader(propertyFile));

            log.debug("imported " + propertyFile + "configuration records");
        }
    }

    public ConfigProperties(String[] propertyFiles) {
        for (String propertyFile : propertyFiles) {
            if (!containedPropFile.contains(propertyFile)) {
                containedPropFile.add(propertyFile);
                properties.putAll(new PropertyLoader(propertyFile));
            }
        }
        log.debug("imported " + Joiner.on(Constants.COMMA).skipNulls().join(propertyFiles) + "configuration records");
    }

    /**
     * clear all property files and properties
     */
    public void clear() {
        containedPropFile.clear();
        properties.clear();
    }

    /**
     * This getProperty method delegate to {@link Properties getProperty(String)}
     *
     * @param key key to map
     * @return null if not exists key, or value mapped from key
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value;
    }

    /**
     * This getIntProperty method delagate to {@link ConfigProperties getProperty} and  Integer.value(String)
     *
     * @param key key to map
     * @return null if not exists key, or value mapped from key
     */
    public Integer getIntProperty(String key) {
        String value = getProperty(key);
        if (StringUtils.isBlank(value))
            return null;
        Integer rtn;
        try {
            rtn = Integer.valueOf(value);
        } catch (NumberFormatException e) {
            log.error("Parse " + value + " to Integer error");
            return null;
        }
        return rtn;
    }

    public Integer getIntProperty(String key, Integer defaultValue) {
        Integer value = getIntProperty(key);
        if (null == value) {
            return defaultValue;
        }
        return value;
    }

    /**
     * This getBoolProperty method delegate to {@link ConfigProperties getProperty} and Boolean.value(String)
     *
     * @param key key to map
     * @return null if not exists key, or value mapped from key
     */
    public Boolean getBoolProperty(String key) {
        String value = getProperty(key);
        if (StringUtils.isBlank(value))
            return null;
        Boolean rtn;
        try {
            rtn = Boolean.valueOf(value);
        } catch (Exception e) {
            log.error("Parse " + value + " to Boolean error");
            return null;
        }
        return rtn;
    }

    public Boolean getBoolProperty(String key, Boolean defaultValue) {
        Boolean value = getBoolProperty(key);
        if (null == value) {
            return defaultValue;
        }
        return value;
    }


}
