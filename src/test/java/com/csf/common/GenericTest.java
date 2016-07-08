package com.csf.common;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by johnny01.yang on 2016/6/23.
 */
public class GenericTest<T> {

    private Class clazz;

    @Test
    public void test1(){
        GenericTest<String> test = new GenericTest<>();
        Type a = test.getClass().getGenericSuperclass();
        System.out.println("a:" + a);
        if (a instanceof ParameterizedType){
            Type actual = ((ParameterizedType)a).getActualTypeArguments()[0];
            System.out.println("actual:" + actual);
        }else{
            System.out.println("88");
        }
    }

}
