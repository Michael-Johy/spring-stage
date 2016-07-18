package com.johnny.jdk8.stream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

/**
 * Optional<T>对象或者是对一个T类型对象的封装，或者表示不是任何对象。
 *
 * Created by johnny01.yang on 2016/7/18.
 */
public class OptionalUse {

    /**
     *
     */
    public static void testOptional(){
        List<String> list = Lists.newArrayList("a", "b", "c");
        Optional<String> optional = list.stream().filter(a -> a.startsWith("d")).findFirst();
        if (optional.isPresent()){
            System.out.println(optional);
        }
        optional.ifPresent(System.out::println);
        String value = optional.orElse("aa");
        optional.orElseGet(()-> System.getProperty("aa"));

        Optional.empty();
        Optional.of("a");
        Optional.ofNullable("aa");
    }
}
