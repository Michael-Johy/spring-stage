package com.csf.common;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by johnny01.yang on 2016/7/13.
 */
public class TreeMapTest {

    @Test
    public void test1(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("a", "1");
        map.put("c", "2");
        map.put("b", "3");
        map.entrySet().stream().forEach(a -> System.out.println("key:" + a.getKey() + " , Value:" + a.getValue()));

        TreeMap<String, String> treeMap = new TreeMap<>(map);
        treeMap.entrySet().stream().forEach(a ->{
            System.out.println("key:" + a.getKey() + ", Value:" + a.getValue());
        });
    }

    @Test
    public void test2(){
        StringBuilder s = new StringBuilder();
        s.append("aaa");
        s.append("&");
        s.delete(s.lastIndexOf("&"), s.length());
        System.out.println(s.toString());
    }
}
