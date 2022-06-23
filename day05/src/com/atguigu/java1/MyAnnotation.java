package com.atguigu.java1;

/**
 * @author zhangweichen
 * @create 2022-06-23 16:14
 */
public @interface MyAnnotation {

    String value() default "hello";
}
