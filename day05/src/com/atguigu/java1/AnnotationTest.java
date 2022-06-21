package com.atguigu.java1;

import java.util.ArrayList;
import java.util.Date;

/**
 * 1. 理解Annotation:
 * ① jdk 5.0 新增的功能
 *
 * ② Annotation 其实就是代码里的特殊标记, 这些标记可以在编译, 类加载, 运行时被读取, 并执行相应的处理。
 *   通过使用 Annotation,程序员可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。
 *
 * ③ 在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。
 *   在JavaEE/Android中注解占据了更重要的角色，例如用来配置应用程序的任何切面，代替JavaEE旧版中所遗留的繁冗代码和XML配置等。
 *
 * 2. Annocation的使用示例
 *  示例一：生成文档相关的注解
 *  示例二：在编译时进行格式检查(JDK内置的三个基本注解)
 *      @Override: 限定重写父类方法, 该注解只能用于方法
 *      @Deprecated: 用于表示所修饰的元素(类, 方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择
 *      @SuppressWarnings: 抑制编译器警告
 *
 *  示例三：跟踪代码依赖性，实现替代配置文件功能
 *
 * @author zhangweichen
 * @create 2022-04-27 23:09
 */
public class AnnotationTest {
    public static void main(String[] args) {

        Person stu = new Student();
        stu.walk();

        // Date类中的此构造器有@Deprecated注解，表示此构造方法已过时，不推荐使用，但并不是不能使用
        Date date = new Date(2022, 4, 27);
        System.out.println(date);

        // IDEA中未使用的变量会以灰色显示，相当于一类较弱的警告
        int num = 10;

        // 添加包含"unused"参数的@SuppressWarnings注解后，抑制编译器对此的警告
        @SuppressWarnings("unused")
        int num1 = 10;

        // 抑制编译器对"变量未使用"和"未使用泛型"的警告
        @SuppressWarnings({"unused", "rawtypes"})
        ArrayList list = new ArrayList();
    }
}

class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk() {
        System.out.println("person walk");
    }

    public void eat() {
        System.out.println("person eat");
    }
}

interface info {
    void show();
}

class Student extends Person implements info {

    // @Override注解对方法重写和实现进行校验，此处方法名与接口中须实现的方法不一致，会生成error
//    @Override
//    public void wolk() {
//        System.out.println("student walk");
//    }

    @Override
    public void walk() {
        System.out.println("student walk");
    }

    @Override
    public void show() {
    }


}