package com.johnny.entity.myswagger;

import com.google.common.base.MoreObjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by johnny01.yang on 2016/7/21.
 */
@XmlRootElement(name = "Swagger")
public class Swagger {

    public Swagger() {
    }

    public Swagger(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String name;
    public Integer age;

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Swagger.class)
                .add("name", name).add("age", age).toString();
    }
}
