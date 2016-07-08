package com.csf.common;

import java.util.Date;

/**
 * Created by johnson.yang on 2016/5/11.
 */
public class Student {

    private String name;
    private Integer age;
    private Date birth;

    public Student(String name, Integer age, Date birth){
        this.name = name;
        this.age = age;
        this.birth = birth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString(){
        return "com.csf.common.Student:[Name:" + name +", Age:" + age + ", Birth:" + birth + "]";
    }
}
