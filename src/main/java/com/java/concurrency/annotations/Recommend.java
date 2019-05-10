package com.java.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 推荐使用
 */
//注解作用的对象
@Target(ElementType.TYPE)
//注解作用的范围
@Retention(RetentionPolicy.SOURCE)
public @interface Recommend {
    String value() default "";
}
