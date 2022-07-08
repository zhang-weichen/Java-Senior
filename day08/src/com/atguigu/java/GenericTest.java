package com.atguigu.java;

import org.junit.Test;

import java.util.*;

/**
 *
 * 泛型的使用
 * 1. JDK 5.0新增的特性
 *
 * 2. 在集合中使用泛型：
 *    总结：
 *    1) 集合接口或集合类在 JDK 5.0时都修改为带泛型的结构。
 *    2) 在实例化集合类时，可以指明具体的泛型类型。
 *    3) 类或接口在引入泛型后，内部结构（比如：方法、构造器、属性等）使用到类的泛型的位置，都指定为实例化的泛型类型。
 *       比如：add(E e) --> 实例化以后：add(Integer e)
 *    4) 注意点：泛型的类型必须是类，不能是基本数据类型。需要用到基本数据类型的位置，使用包装类代替。
 *    5) 如果实例化时没有指明泛型的类型，默认类型为 java.lang.Object类型。
 *
 *
 * @author zhangweichen
 * @create 2021-12-08-14:20
 */
public class GenericTest {

    // 集合中不使用泛型的情况：
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
            int stuScore = (Integer) score;

            System.out.println(stuScore);
        }

    }

    // 集合中使用泛型的情况：以ArrayList为例
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

        // 方式一
        for (Integer score : list) {

            // 避免了强转操作，不会出现ClassCastException
            int stuScore = score;

            System.out.println(stuScore);
        }

        // 方式二
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int stuScore = iterator.next();
            System.out.println(stuScore);
        }
    }

    // 集合中使用泛型的情况：以HashMap为例
    @Test
    public void test3() {
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("Tom", 23);
        map.put("Jerry", 12);
        map.put("Bill", 32);

        // 泛型的嵌套
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String key = entry.getKey();
            int value = entry.getValue();
            System.out.println("name:" + key + ", age:" + value);
        }
    }
}
