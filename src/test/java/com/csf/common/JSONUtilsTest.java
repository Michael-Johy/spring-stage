package com.csf.common;

import com.johnny.common.utils.JSONUtil;
import com.johnny.entity.Person;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by johnson.yang on 2016/5/11.
 */
public class JSONUtilsTest {

    @Test
    public void testComplexMapKey() {
        Map<Person, String> map = new LinkedHashMap<>();
        map.put(new Person("yt", 1), "yangtao");
        map.put(new Person("yp", 2), "yangpeng");
        System.out.println(JSONUtil.toJson(map, Map.class));

        System.out.println(JSONUtil.toJson(new Person("yt", 1), Person.class));
        System.out.println(JSONUtil.toJson(new Person("yt", 1)));

        System.out.println(JSONUtil.toJson(new Student("yt", 1, new Date())));
        String str = JSONUtil.toJson(new Student("yt", 1, new Date()));
        Student student = JSONUtil.fromJson(str, Student.class);
        System.out.println(student);

    }
}
