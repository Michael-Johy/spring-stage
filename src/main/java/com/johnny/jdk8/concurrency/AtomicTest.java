package com.johnny.jdk8.concurrency;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by johnny01.yang on 2016/7/19.
 */
public class AtomicTest {

    public static void testAtomic(){
        AtomicLong atomicLong = new AtomicLong(1);
        atomicLong.compareAndSet(1, 2);
        atomicLong.incrementAndGet();
    }
}
