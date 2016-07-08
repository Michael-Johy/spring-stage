package com.johnny.entity;

import com.google.common.base.MoreObjects;

/**
 * Created by johnson.yang on 2016/5/11.
 */
public class Person {

    private String name;
    private Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Person.class)
                .add("name", name).add("age", age).toString();
    }
}
