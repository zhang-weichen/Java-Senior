package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhangweichen
 * @create 2022-07-10 23:52
 */
public class Order<T> {

    String orderName;
    int orderId;

    // 类的内部结构可以使用类的泛型
    T orderT;

    public Order() {
        // 在泛型类中定义一个泛型数组
        // 错误的写法，编译不通过：
//        T[] arr = new T[10];

        // 正确的写法：
        T[] arr = (T[])new Object[10];
    }

    public Order(String oderName, int orderId, T orderT) {
        this.orderName = oderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // 如下包含泛型类型T的getter、setter方法都不是泛型方法
    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }

    // 静态方法中不能使用类的泛型。
    // 原因：在实例化时，类的泛型才会被指定，而静态方法的加载早于类的实例化，故无法使用泛型。
//    public static void show(T orderT){
//        System.out.println(orderT);
//    }

    // 不能在catch的类型中使用泛型。
    // catch中的类型必须是异常类型
//    public void show(){
//        // 编译不通过
//        try{
//
//        }catch(T t){
//
//        }
//    }

    // 泛型方法：在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系。
    // 即某方法是否为泛型方法与其所属类是否为泛型类没有关系。
    // 泛型方法可以声明为静态的。因为方法的泛型参数是在调用方法时确定的，而不是在实例化类时确定的。
    public static <E> List<E> copyFromArrayToList(E[] arr) {
        ArrayList<E> list = new ArrayList<>();

        for (E e : arr) {
            list.add(e);
        }
        return list;
    }
}
