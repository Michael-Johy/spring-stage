package com.johnny.externxml.parser;

import com.johnny.externxml.testclass.XmlTest;
import org.apache.cxf.helpers.DOMUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Description:
 * <p>
 * Author: johnny
 * Date  : 2016-11-29 20:44
 */
public class XmltestBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {


    @Override
    protected Class<?> getBeanClass(Element element) {
        return XmlTest.class;
    }

//    @Override
//    protected void mapAttribute(BeanDefinitionBuilder bean, Element e, String name, String val) {
//        System.out.println("value:" + val);
//        if ("age".equals(name)) {
//            bean.addPropertyValue("age", val);
//        } else if ("name".equals(name)) {
//            bean.addPropertyValue("name", val);
//        }
//    }

    @Override
    protected void doParse(Element element, ParserContext ctx, BeanDefinitionBuilder bean) {
//        super.doParse(element, ctx, bean);
        String name = element.getAttribute("name");
        System.out.println("name:" + name);
        String age = element.getAttribute("age");
        System.out.println("age:" + age);
//        List<?> objects = ctx.getDelegate().parseListElement(element, bean.getBeanDefinition());
        bean.addPropertyValue("name", name);
        bean.addPropertyValue("age", age);
//        bean.addPropertyValue("sdf", objects);
//        bean.addPropertyReference("sdf", "sdf");
        parseChildElement(element, ctx, bean);
    }

    protected void parseChildElement(Element element, ParserContext ctx, BeanDefinitionBuilder bean) {
        Element el = DOMUtils.getFirstElement(element);
        while (el != null) {
            String name = el.getLocalName();
            mapElement(ctx, bean, el, name);
            el = DOMUtils.getNextElement(el);
        }
    }

    protected void mapElement(ParserContext ctx, BeanDefinitionBuilder bean, Element el, String name) {
        System.out.println("MapElement:" + name);
        if ("sdf".equals(name)) {
//            List<?> objects = ctx.getDelegate().parseListElement(el, bean.getBeanDefinition());
//            Object object = ctx.getDelegate().parseIdRefElement(el);
            Object object = ctx.getDelegate().parsePropertyValue(el, bean.getBeanDefinition(), name);
            bean.addPropertyValue("sdf", object);
        }
    }


}
