package com.johnny.jdk8.lambda;

import java.util.Comparator;

/**
 * java.util.function
 *
 * Lambda 表达式是一个带有参数的代码块,也是可以传递的代码块
 * <p>
 * Lambda 表达式格式： 参数、箭头、以及一个表达式
 * 自由变量概念：不是参数并且没有在代码中定义的变量
 *
 * 含有自由变量的代码块被称之为"闭包"， lambda就是闭包，被捕获的值必须是任何有效的final不可变的
 * <p>
 * Created by johnny01.yang on 2016/7/18.
 */
public class LambdaDesc {

    /**
     * The following are Use Cases
     */

    /**
     * @1 when you need to execute the code diagram in future time(Java ： it's not easy to transfer code diagram )
     *
     * @2 readable and simplicity
     */
    public void desc01() {
        //Runnable  //Comparator  //Callback  setOnDoubleClick
        Comparator<String> comp = (first, second) ->
                Integer.compare(first.length(), second.length());

    }


}
