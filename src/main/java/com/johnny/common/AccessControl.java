package com.johnny.common;

import java.lang.annotation.*;

/**
 * customize a common annotation
 * <p>
 * Created by johnson.yang on 2016/5/10.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface AccessControl {

    boolean verifyToken() default false;

    String iName() default "";

}
