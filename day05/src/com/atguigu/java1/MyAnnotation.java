package com.atguigu.java1;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @author zhangweichen
 * @create 2022-06-23 16:14
 */
@Inherited  // 此注解修饰的父类在被子类继承时，此注解会传递给子类
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)  // 此注解的生命周期持续到运行时，即可通过反射获取此注解
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER, TYPE_USE})  // 指明此注解可修饰的结构
public @interface MyAnnotation {

    String value() default "hello";
}
