package com.csf.entity;

import com.google.common.base.MoreObjects;

/**
 * Created by johnson.yang on 2016/5/12.
 */
public class ReflectionTestEntity {

    private String name;
    private String age;

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getAge() {
        return age;
    }

    private void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return MoreObjects.toStringHelper(ReflectionTestEntity.class)
                .add("name", name).add("age", age).toString();
    }

}
