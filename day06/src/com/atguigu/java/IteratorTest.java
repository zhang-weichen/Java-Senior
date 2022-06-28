package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author zhangweichen
 * @create 2022-06-28 17:05
 */
public class IteratorTest {

    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Java"));
        coll.add(false);
        coll.add(new Person("Tom", 23));

        Iterator iterator = coll.iterator();

        // Iterator的遍历：
        // 方式一：调用next()方法直至发生异常，开发中不使用
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        // 异常：NoSuchElementException
//        System.out.println(iterator.next());

        // 方式二：不推荐
        for (int i = 0; i < coll.size(); i++) {
            System.out.println(iterator.next());
        }

        // 方式三：推荐
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
