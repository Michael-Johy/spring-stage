package com.johnny.ftl;

import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 * Author: johnny01.yang
 * Date  : 2016-08-31 19:29
 */
public class ConfigurationUtils {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationUtils.class);

    public static Configuration getConfigurationInstance(){
        return InstanceHolder.configuration;
    }

    private static final class InstanceHolder {
        static final Configuration configuration;
        static {
            configuration = new Configuration(Configuration.VERSION_2_3_23);
//            try {
//                configuration.setDirectoryForTemplateLoading(new File("D:\\templates"));
//            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
//            }

            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setLogTemplateExceptions(false);
        }
    }

    public static Map<String, Object> getDataModel(){
        Map<String, Object> map = new HashMap<>();
        map.put("user", "johnny01.yang");
        Map<String, Object> productMap = new HashMap();
        productMap.put("url", "http:\\\\www.baidu.com");
        productMap.put("name", "green mouse");
        map.put("latestProduct", productMap);
        return map;
    }

    public static Map<String, Object> getData1Model(){
        Map<String, Object> map = new HashMap<>();
        map.put("user", "johnny03.yang");
        Map<String, Object> productMap = new HashMap();
        productMap.put("url", "http:\\\\www.b222aidu.com");
        productMap.put("name", "green mo22use");
        map.put("latestProduct", productMap);
        return map;
    }

    public static Template getTemplate(String tplName) throws IOException{
        return getConfigurationInstance().getTemplate(tplName);
    }

    public static void main(String[] args)throws IOException, TemplateException{
        Writer out = new OutputStreamWriter(System.out);
        Template template = getTemplate("/test.ftl2");
        template.process(getDataModel(), out);
        template.process(getData1Model(), out);
    }


}
