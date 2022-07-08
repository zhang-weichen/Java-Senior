package com.atguigu.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author zhangweichen
 * @create 2022-07-08 10:47
 */
public class CollectionExer {

    /**
     * 使用Collection中定义的默认方法 forEach()遍历集合
     */
    @Test
    public void test() {

        Collection list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add(789);

        list.forEach(System.out::println);
    }

    // 练习：在List内去除重复数字值，要求尽量简单
    public static List duplicateList(List list) {
        HashSet set = new HashSet();
        set.addAll(list);
        return new ArrayList(set);
    }
    @Test
    public void test2(){
        List list = new ArrayList();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(2));
        list.add(new Integer(4));
        list.add(new Integer(4));
        List list2 = duplicateList(list);
        for (Object obj : list2) {
            System.out.println(obj);
        }
    }

    @Test
    public void test3() {
        HashSet set = new HashSet();
        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");

        set.add(p1);
        set.add(p2);
        System.out.println(set);

        p1.setName("CC");
        // p1在set中的存储位置由添加时的值决定，其后对p1内容的修改其值不会引起p1存储位置的变化
        // 因为p1的内容被修改了，所以调用remove()时计算得到的hash值也发生了变化，无法由新的hash值计算得到p1的正确存储位置，删除失败
        set.remove(p1);
        System.out.println(set);  // [Person{id=1002, name='BB'}, Person{id=1001, name='CC'}]

        // 原因同上
        set.add(new Person(1001, "CC"));  // [Person{id=1002, name='BB'}, Person{id=1001, name='CC'}, Person{id=1001, name='CC'}]
        System.out.println(set);

        // 与p1存储在同一位置，但因为内容不一致，成功添加
        set.add(new Person(1001, "AA"));  // [Person{id=1002, name='BB'}, Person{id=1001, name='CC'}, Person{id=1001, name='CC'}, Person{id=1001, name='AA'}]
        System.out.println(set);
    }
}
