package com.johnny.jdk8.lambda;

import com.google.common.collect.Lists;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by johnny01.yang on 2016/7/18.
 */
public class LambdaUse {

    public static void main(String[] args) {
        LambdaUse use = new LambdaUse();
        use.test1();

    }

    /**
     * Method reference: The code diagram you want to transfer has been defined in other object
     * <p>
     * 对象::实例方法
     * 类::静态方法
     * 类::实例方法
     *
     * Constructor reference构造器引用
     */
    public void test1() {
        List<String> list = Lists.newArrayList("a", "b", "c");
        //实例方法引用 对象::实例方法
        list.forEach(System.out::println);

        String aa = "aa";
        //类：实例方法
        Collections.sort(list, String::compareToIgnoreCase);

        Button[] buttons = list.stream().toArray(Button[]::new);

    }

    /**
     * 默认方法：default
     */
    public void test2(){
        /**
         * 默认方法规则：
         * 1.选择父类中的方法。如果一个父类提供了具体的实现方法，那么接口中具有相同名称和参数的默认方法就会被忽略
         * 2.接口冲突。如果一个父接口提供了一个默认方法，而另一个接口也提供了一个具有相同名称和参数类型的方法(不管该方法是否是默认方法)
         *   那么你必须通过覆盖该方法来解决冲突
         *
         */
    }

    /**
     * 静态方法：接口中可以添加静态方法
     */


}
