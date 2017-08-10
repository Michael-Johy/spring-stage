package com.johnny.jdk.delay;

import java.util.concurrent.DelayQueue;

/**
 * Description:
 * <p>
 * Author: yang_tao
 * Date  : 2017-07-25 15:22
 */
public class DelayTest {

    public static void main(String[] args){
        DelayQueue queue = new DelayQueue();
        queue.add(new MyDelayed());
        queue.poll();
    }
}
