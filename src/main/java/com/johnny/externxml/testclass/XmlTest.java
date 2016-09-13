package com.johnny.externxml.testclass;

import java.text.SimpleDateFormat;

/**
 * Description:
 * <p>
 * Author: johnny
 * Date  : 2016-11-29 20:33
 */
public class XmlTest {

    private String name;
    private Integer age;
    private SimpleDateFormat sdf;

    public XmlTest() {
    }

    public XmlTest(String name, Integer age, SimpleDateFormat sdf) {
        this.name = name;
        this.age = age;
        this.sdf = sdf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }
}
