package com.johnny.externxml.handler;

import com.johnny.externxml.parser.XmltestBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Description:
 * <p>
 * Author: johnny
 * Date  : 2016-11-29 20:43
 */
public class XmltestNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("pp", new XmltestBeanDefinitionParser());
    }
}
