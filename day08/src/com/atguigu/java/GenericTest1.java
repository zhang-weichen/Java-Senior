package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 如何自定义泛型结构：泛型类、泛型接口；泛型方法。
 *
 * 1. 关于自定义泛型类、泛型接口：
 *
 * @author zhangweichen
 * @create 2022-07-11 10:01
 */
public class GenericTest1 {

    @Test
    public void test1() {

        // 如果定义了泛型类，实例化没有指明类的泛型，则认为此泛型类型为Object类型
        // 要求：如果定义的类是带泛型的，建议在实例化时指明类的泛型。
        Order order = new Order();
        order.setOrderName("orderA");
        order.setOrderId(001);
        order.setOrderT("order:A");

        // 建议：实例化时指明类的泛型
        Order<String> order1 = new Order<String>("orderB", 002, "order:b");
        order1.setOrderT("order:B");
        System.out.println(order1);
    }

    @Test
    public void test2() {
        SubOrder sub1 = new SubOrder();

        // 由于子类在继承带泛型的父类时，父类指明了泛型类型。则实例化子类对象时，在未新增泛型参数的情况下，无需再指明泛型。
        sub1.setOrderT(123);

        SubOrder1<String> sub2 = new SubOrder1<>();
        sub2.setOrderT("subOrder2...");
    }

    @Test
    public void test3() {

        ArrayList<String> list1 = null;
        ArrayList<Integer> list2 = null;

        // 泛型不同的同一个类的引用不能相互赋值
//        list1 = list2;
    }

    // 测试泛型方法
    @Test
    public void test4() {
        Order<String> order = new Order<>();
        Integer[] arr = new Integer[]{1, 2, 3};
        List<Integer> list = Order.copyFromArrayToList(arr);

        System.out.println(list);
    }
}