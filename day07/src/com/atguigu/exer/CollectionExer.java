package com.atguigu.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

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
}
