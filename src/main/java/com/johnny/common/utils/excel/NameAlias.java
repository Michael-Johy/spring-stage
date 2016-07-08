package com.johnny.common.utils.excel;

import java.lang.annotation.*;

/**
 * This annotation is used to annotate filed with mutable alias and conversion betweent desc and field name
 * <p>
 * Created by johnny01.yang on 2016/6/17.
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.FIELD)
public @interface NameAlias {
    String value() default "";
}
