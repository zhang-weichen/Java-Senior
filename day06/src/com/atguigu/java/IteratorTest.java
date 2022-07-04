package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合内部的遍历操作：使用迭代器 Iterator接口
 * 1. 内部的方法：hasNext() 和 next()
 *
 * 2. 集合对象每次调用 iterator()方法都得到一个全新的迭代器对象，默认游标都在集合的第一个元素之前。
 *
 * 3.内部定义了 remove(),可以在遍历的时候，删除集合中的元素。此方法不同于集合直接调用 remove()
 *
 * 注意：
 * 1. Iterator是迭代器，并不属于容器。
 * 2. Iterator中的 remove()会作用到原集合上，但与集合中的 remove()的不同。
 *
 * @author zhangweichen
 * @create 2022-06-28 17:05
 */
public class IteratorTest {

    // 测试使用迭代器遍历集合
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
        // hasNext()：判断是否还有下一个元素
        while (iterator.hasNext()) {
            // next()：① 指针下移 ② 将下移以后集合位置上的元素返回
            System.out.println(iterator.next());
        }
    }

    // 使用迭代器时常见的错误
    @Test
    public void test2() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Java"));
        coll.add(false);
        coll.add(new Person("Tom", 23));

        // 错误方式一：调用next()方法进行判断，next()会使指针下移
//        Iterator iterator = coll.iterator();
//        while (iterator.next() != null) {
//            System.out.println(iterator.next());
//        }

        // 错误方式二：集合每次调用iterator()方法都会返回一个新的迭代器对象 ，默认游标都在集合第一个元素之前
//        while (coll.iterator().hasNext()) {
//            System.out.println(coll.iterator().next());
//        }
    }

    // 测试Iterator中的remove()
    // 初次调用next()前或上次调用next()方法后已调用了remove()，调用remove()都会发生IllegalStateException
    @Test
    public void test3() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Java"));
        coll.add(false);
        coll.add(new Person("Tom", 23));

        // 删除集合中的"Java"
        Iterator iterator = coll.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            // 在未调用next()前不可调用remove()
//            iterator.remove();
            if ("Java".equals(obj)) {
                iterator.remove();
                // 不可连续调用remove()
//                iterator.remove();
            }
        }

        // 遍历集合
        iterator = coll.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
