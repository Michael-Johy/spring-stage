package com.johnny.jdk8.stream;

import com.google.common.collect.Lists;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * 使用Stream时，通过三个阶段来建立一个流水线
 * 1.创建一个Stream
 * 2.在一个或多个步骤中，指定将初始Stream转换为另一个Stream的中间操作
 * 3.使用一个终止操作来产生一个结果。该操作会强制它之前的延迟操作立即执行。
 *
 *
 * Created by johnny01.yang on 2016/7/18.
 */
public class StreamUse {
    /**
     * 迭代器意味着特定的遍历策略，禁止了高效的并发执行: Stream 解决了做什么而不是怎么做
     *
     * 从迭代器到Stream操作：
     * 迭代器和Stream区别:
     * 1.Stream 自己不会存储元素，元素可能被存储在底层的集合中，或者根据需要产生出来
     * 2.Stream 操作符不会改变源对象。相反，它们会返回一个持有结果的新Stream
     * 3.Stream 操作符可能是延迟执行的。这意味着它们会等到需要结果的时候才执行
     *
     */
    public static void iterator(){

    }

    /**
     * 数组、集合、生成器或者迭代器生成Stream
     */
    public static void createStream(){
        /**
         * 1.通过在Collection 集合中添加的Stream方法来创建Stream
         */
        Collection<String> coll = Lists.newArrayList("a", "b");
        coll.stream();
        coll.parallelStream();

        /**
         * 2.数组。调用Stream.of()
         */
        String[] strs = new String[]{"a", "b"};
        Stream.of(strs);
        Arrays.stream(strs);
    }

    /**
     * transform : filter , Map  , flatMap
     */
    public static void transformStream(){
    }

    /**
     * Stream.limit()  Stream.skip()  Stream.concat()  Stream.peek(): 每次获取一个元素时，都会调用一个函数
     */
    public static void subStream(){
    }

    /**
     *  4.Aggregation 聚合方法(聚合方法都是终止操作)
     *  eg: count max min findFirst findAny andMatch allMatch noneMatch
     */
    public static void aggregation(){
    }

    /**
     *  5.Optional 使用见OptionalUse
     */

    /**
     *  6.聚合操作reduce
     */
    public static void aggregate(){

    }

    /**
     *  7.收集操作collect
     */
    public static void collect(){

    }

    /**
     *  8. Collectors 分组groupBy   分片partitioningBy
     */

    /**
     *  9. 函数式接口
     */


}
