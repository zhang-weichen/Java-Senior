package com.atguigu.java;

import org.junit.Test;

/**
 * 关于StringBuffer和StringBuilder的使用
 *
 * @author zhangweichen
 * @create 2022-05-09 23:47
 */
public class StringBufferBuilderTest {

    /*
    String、StringBuffer、StringBuilder三者的异同？
    String：不可变的字符序列；底层使用char[]存储
    StringBuffer：可变的字符序列；线程安全的，效率低；底层使用char[]存储
    StringBuilder：可变的字符序列；JDK5.0新增，线程不安全的，效率高；底层使用char[]存储

    源码分析：
    String str = new String();  // char[] value = new char[0];
    String str1 = new String("abc"); // char[] value = new char[3]{'a', 'b', 'c'};

    StringBuffer sb1 = new StringBuffer();  // char[] value = new char[16]; 底层创建了一个长度为16的数组
    System.out.println(sb2.length());  // 0
    sb1.append('a');  // value[0] = 'a';
    sb1.append('b');  // value[1] = 'b';

    StringBuffer sb2 = new StringBuffer("abc");  // char[] value = new char["abc".length() + 16];

    // 问题1 System.out.println(sb2.length());  // 3
    // 问题2 扩容问题：如果添加的字符数量超过了底层字符数组的容量，就需要对底层数组进行扩容。
             默认情况下，扩容为原来容量的2倍 + 2，同时将原数组的中元素赋值到新的数组中。

             指导意义：开发中，建议使用：StringBuffer(int capacity); 或 StringBuilder(int capacity)

     */
    @Test
    public void test1() {
        StringBuffer sb1 = new StringBuffer("abc");
        sb1.setCharAt(0,'m');
        System.out.println(sb1);  // mbc

        StringBuffer sb2 = new StringBuffer();
        System.out.println(sb2);  // 0
    }
}
