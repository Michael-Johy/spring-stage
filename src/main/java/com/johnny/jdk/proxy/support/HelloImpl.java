package com.johnny.jdk.proxy.support;

/**
 * Description:
 * <p>
 * Author: johnny
 * Date  : 2017-02-08 11:11
 */
public class HelloImpl implements IHello {
    @Override
    public void hello() {
        System.out.println("Hello Impl");
    }
}
