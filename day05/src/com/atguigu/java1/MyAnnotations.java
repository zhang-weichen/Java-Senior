package com.atguigu.java1;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @author zhangweichen
 * @create 2022-06-24 9:31
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})  // 指明此注解可修饰的结构
public @interface MyAnnotations {

    MyAnnotation[] value();
}
