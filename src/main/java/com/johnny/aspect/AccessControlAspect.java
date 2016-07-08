package com.johnny.aspect;

import com.johnny.common.AccessControl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

import javax.servlet.http.HttpServletRequest;

/**
 * define a aspect to intercept request
 * <p>
 * Created by johnson.yang on 2016/5/10.
 */
@Aspect
public class AccessControlAspect {

    public Object process(ProceedingJoinPoint pjp, AccessControl access, HttpServletRequest request) {
        boolean verifytoken = access.verifyToken();
        String iName = access.iName();
        System.out.println("Vefifytoken:" + verifytoken + ";iName:" + iName);
        try {
            pjp.proceed();
        } catch (Throwable throwable) {

        }
        return null;
    }
}
