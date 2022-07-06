package com.atguigu.java;

import org.junit.Test;

import java.util.*;

/**
 * @author zhangweichen
 * @create 2022-07-06 16:01
 */
public class TreeMapTest {

    // 向TreeMap中添加key-value，要求key必须是同一个类的对象
    // 按照key进行排序：自然排序、定制排序

    // 自然排序
    @Test
    public void test1() {
        TreeMap map = new TreeMap();

        User u1 = new User("Jerry", 12);
        User u2 = new User("Tom", 8);
        User u3 = new User("Kate", 18);
        User u4 = new User("Mike", 15);
        User u5 = new User("Bill", 22);

        map.put(u1, 23);
        map.put(u2, 62);
        map.put(u3, 73);
        map.put(u4, 54);
        map.put(u5, 43);

        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey() + " --> " + entry.getValue());
        }
    }
    // 定制排序
    @Test
    public void test2() {
        TreeMap map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }
                throw new RuntimeException("传入的数据类型不匹配！");
            }
        });


        User u1 = new User("Jerry", 12);
        User u2 = new User("Tom", 8);
        User u3 = new User("Kate", 18);
        User u4 = new User("Mike", 15);
        User u5 = new User("Bill", 22);

        map.put(u1, 23);
        map.put(u2, 62);
        map.put(u3, 73);
        map.put(u4, 54);
        map.put(u5, 43);

        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry obj = (Map.Entry) iterator.next();
            System.out.println(obj.getKey() + " --> " + obj.getValue());
        }
    }
}
