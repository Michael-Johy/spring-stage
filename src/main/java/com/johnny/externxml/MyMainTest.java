package com.johnny.externxml;

import com.johnny.externxml.testclass.XmlTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;

/**
 * Description:
 * <p>
 * Author: johnny
 * Date  : 2016-11-29 20:19
 */
public class MyMainTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring/spring-externxml.xml");
        SimpleDateFormat sdf = (SimpleDateFormat) context.getBean("dateFormat");
        System.out.println(sdf);
        XmlTest xmlTest = (XmlTest) context.getBean("aa");
        System.out.println(xmlTest.getName());
        System.out.println(xmlTest.getAge());
        System.out.println(xmlTest.getSdf());
    }
}
