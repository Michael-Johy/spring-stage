package com.johnny.externxml.handler;


import com.johnny.externxml.parser.SimpleDateFormatBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Description:
 * <p>
 * Author: johnny
 * Date  : 2016-11-29 19:50
 */
public class MynsNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("dateformat", new SimpleDateFormatBeanDefinitionParser());
    }
}
