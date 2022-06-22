package com.atguigu.java;

import org.junit.Test;

/**
 * String的使用
 *
 * @author zhangweichen
 * @create 2022-05-01 12:59
 */
public class StringTest {

    /*
     * String:字符串，使用一对""引起来表示。
     * 1. String类被声明为final类型，不可被继承
     * 2. String实现了Serializable接口：表示字符串是支持序列化的。
     *          实现了Comparable接口：表示String是可以比较大小的
     * 3. String内部定义了"final char[] value;"用于存储字符串数据
     * 4. String：表示不可变的字符序列。简称：不可变性。
     *      体现：1. 当对字符串重新赋值时，需要重写指定内存区域赋值，不能修改原有的value数组进行赋值（value是final类型的）。
     *           2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能扩展原有的value数组进行赋值。
     *           3. 当调用String的replace()方法修改指定字符或字符串时，也需要重新指定内存区域赋值，不能修改原有的value数组进行赋值。
     * 5. 通过字面量的方式（区别于new）给一个字符串赋值，此时的字符串值声明在字符串常量池中。
     * 6. 字符串常量池中是不会存储相同内容的字符串的。
     */
    @Test
    public void test1() {

        String s1 = "abc";  // 字面量的定义方式
        String s2 = "abc";

        System.out.println(s1 == s2);  // 使用"=="比较两个对象，比较的是地址值，true

        // 当对字符串重新赋值时，需要重写指定内存区域赋值，不能修改原有的value数组进行赋值
        s1 = "hello";
        System.out.println(s1 == s2);  // false

        System.out.println("********************");

        String s3 = s1 + "world";
        // 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能扩展原有的value数组进行赋值
        System.out.println(s1);  // hello
        System.out.println(s3);  // helloworld
        System.out.println(s1 == s3);  // false

        System.out.println("********************");
        // replace()方法修改指定字符或字符串时，也需要重新指定内存区域赋值，不能修改原有的value数组进行赋值。
        String s4 = s2.replace('c', 'm');
        System.out.println(s2);  // abc
        System.out.println(s4);  // abm
        System.out.println(s2 == s4);  // false
    }

    /*
    String的实例化方式
    方式一：通过字面量定义的方式
    方式二：通过new + 构造器的方式

    面试题：String s = new String("abc");方式创建对象，在内存中创建了几个对象？
            两个：一个是堆空间中new的结构，另一个是char[]对应的常量池中的数据："abc"
     */
    @Test
    public void test2() {

        // 通过字面量的定义的方式：s1和s2的值均为"javaEE"存储在方法区的字符串常量池中的地址
        String s1 = "javaEE";
        String s2 = "javaEE";

        // 通过new + 构造器的方式：s3和s4的值，是字符串对象存储于堆空间中的地址值，而此对象最终又指向方法区的字符串常量池中的"javaEE"。
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");

        System.out.println(s1 == s2);  // true
        System.out.println(s3 == s4);  // false
        System.out.println(s1 == s3);  // false

        System.out.println("********************");
        Person p1 = new Person("Tom", 12);
        Person p2 = new Person("Tom", 12);

        System.out.println(p1.name.equals(p2.name));  // true
        System.out.println(p1.name == p2.name);  // true

        p1.name = "Jerry";
        System.out.println(p2.name);  // Tom

    }

}
