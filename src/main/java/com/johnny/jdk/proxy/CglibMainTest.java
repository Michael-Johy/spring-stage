package com.johnny.jdk.proxy;


import com.johnny.jdk.proxy.cglib.Base;
import com.johnny.jdk.proxy.cglib.CglibInterceptor;
import net.sf.cglib.proxy.Enhancer;

/**
 * Description:
 * <p>
 * Author: johnny
 * Date  : 2017-02-08 16:47
 */
public class CglibMainTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Base.class);

        CglibInterceptor cglibInterceptor = new CglibInterceptor();
        //设置回调方法的参数为代理类对象，最后增强目标类调用的是代理类对象CglibProxy中的intercept方法
        enhancer.setCallback(cglibInterceptor);
        Base base = (Base) enhancer.create();
        System.out.println(base.print());
    }
}
