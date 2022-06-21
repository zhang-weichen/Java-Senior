package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;

/**
 *
 * 泛型的使用
 * 1. JDK 5.0新增的特性
 *
 *
 * @author zhangweichen
 * @create 2021-12-08-14:20
 */
public class Generic {

    // 在集合中不使用泛型的情况：
    @Test
    public void test1() {
        ArrayList list = new ArrayList();

        // 需求：存放学生的成绩
        list.add(98);
        list.add(76);
        list.add(45);
        list.add(88);
        list.add(66);

        // 问题一：类型不安全
//        list.add("Tom");

        for(Object score : list) {

            // 问题二：强转时，可能出现ClassCastException
            int stuScore = (Integer)score;

            System.out.println(stuScore);
        }
    }

    // 在集合中使用泛型的情况：
    @Test
    public void test2() {
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(98);
        list.add(76);
        list.add(45);
        list.add(88);
        list.add(66);

        // 编译时进行类型检查，保证数据安全
//        list.add("Tom");

        for(Integer score : list) {

            // 避免了强转操作
            int stuScore = score;

            System.out.println(stuScore);
        }
    }
}
