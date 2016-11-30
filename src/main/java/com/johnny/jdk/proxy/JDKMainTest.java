package com.johnny.jdk.proxy;

import com.johnny.jdk.proxy.support.HelloImpl;
import com.johnny.jdk.proxy.support.IHello;

import java.lang.reflect.Proxy;

/**
 * Description:main test class to test JDK proxy
 * <p>
 * Author: johnny
 * Date  : 2017-02-08 11:13
 */
public class JDKMainTest {

    public static void main(String[] args) {
        IHello iHello = new HelloImpl();
        MyInvocationHandler myHandler = new MyInvocationHandler(iHello);

        IHello iHelloProxy = (IHello) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                                                             new Class[]{IHello.class}, myHandler);

        iHelloProxy.hello();
    }

}
