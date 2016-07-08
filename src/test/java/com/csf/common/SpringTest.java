package com.csf.common;

import com.johnny.spi.api.IOperation;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.adapter.DefaultAdvisorAdapterRegistry;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by johnny01.yang on 2016/6/15.
 */
public class SpringTest {

    @Test
    public void test1(){

        ProxyFactoryBean factoryBean = null;

        DefaultAdvisorAdapterRegistry registry = null;

        ServiceLoader serviceLoader = ServiceLoader.load(IOperation.class);
        System.out.println("aaa");
        Iterator<IOperation> iterator =  serviceLoader.iterator();
        while (iterator.hasNext()){
            IOperation operation = iterator.next();
            System.out.println(operation.operation(6, 3));
        }
    }
}
