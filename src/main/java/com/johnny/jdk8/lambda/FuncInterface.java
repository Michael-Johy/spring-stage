package com.johnny.jdk8.lambda;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Lambda expression can interconversion with any FunctionInterface
 * <p>
 * Created by johnny01.yang on 2016/7/18.
 */
public class FuncInterface {

    public static void main(String[] arsg) {

    }

    /**
     * For interface who only contains an abstract method, you can create object of the interface type by Lambda
     */
    public static void test1() {
        List<String> list = Lists.newArrayList("a", "b", "c");
        //Collections.sort(List, Comparator<String>)
        Collections.sort(list, (String first, String second) -> Integer.compare(first.length(), second.length()));
    }

    /**
     * why choose Comparator not BiFunction?
     * 1.when you use lambda , you need to keep the purpose in mind, then choose a FunctionInterface
     */
    public static void test2() {
        Comparator<String> comp = (String first, String second) -> Integer.compare(first.length(), second.length());

        BiFunction<String, String, Integer> biFunction = (String first, String second) -> Integer.compare(first.length(), second.length());
    }

    /**
     * define a FunctionalInterface
     */
    @FunctionalInterface
    interface AA {
        void aa();

        default void bb() {

        }
    }
}
