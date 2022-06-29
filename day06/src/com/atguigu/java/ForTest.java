package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * JDK 5.0 新增了foreach循环，用于遍历集合、数组
 *
 * @author zhangweichen
 * @create 2022-06-29 16:18
 */
public class ForTest {

    // 测试foreach遍历集合
    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Java"));
        coll.add(false);
        coll.add(new Person("Tom", 23));

        // for(集合元素的类型 局部变量 : 集合对象)
        // 内部仍然调用了迭代器。
        for (Object obj : coll) {
            System.out.println(obj);
        }
    }

    // 测试foreach遍历数组
    @Test
    public void test2() {

        int[] arr = new int[] {1, 2, 3, 4, 5, 6};

        // for(数组元素的类型 局部变量 : 集合对象)
        for (int i : arr) {
            System.out.println(i);
        }
    }

    // 练习题
    @Test
    public void test3(){

        String[] arr = new String[]{"MM","MM","MM"};

        //方式一：普通for循环进行赋值操作
//        for(int i = 0; i < arr.length; i++){
//            arr[i] = "GG";
//        }

        //方式二：增强for循环
        for(String s : arr){
            s = "GG";
        }

        for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}
