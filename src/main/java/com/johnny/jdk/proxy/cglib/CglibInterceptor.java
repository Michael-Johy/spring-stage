package com.johnny.jdk.proxy.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Description:
 * <p>
 * Author: johnny
 * Date  : 2017-02-08 16:30
 */
public class CglibInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //BeforeAdviseInterceptor
        System.out.println("before");
        Object result = methodProxy.invokeSuper(o, objects);
        //AfterAdviseInterceptor
        System.out.println("after");
        return result;
    }
}
