package com.csf.service;

import com.johnny.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by johnson.yang on 2016/5/11.
 */
public class AccountServiceTest {

    public void setUp(){

    }

    @Test
    public void testFetchUserid(){
        String[] xmls = new String[]{"spring/spring-dao.xml", "spring/spring-service.xml", "spring/spring-web.xml"};

        ApplicationContext context = new ClassPathXmlApplicationContext(xmls);
        AccountService accountService = (AccountService) context.getBean("accountService");

        System.out.println(accountService.fetchUserId(null, ""));
    }

}
