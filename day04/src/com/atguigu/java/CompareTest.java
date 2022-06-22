package com.atguigu.java;

import org.junit.Test;

import java.util.Arrays;

/**
 * 一、说明：Java中的对象正常情况下可进行的比较操作： == 或 != ，不能使用 > 或 < 。
 *          但在开发场景中，对对象进行排序操作是十分常见的，这时就需要比较对象的大小。
 *          如何实现？ 使用两个接口中的任意一个：Comparable 或 Comparator
 *
 * 二、Comparable接口的使用
 *
 * @author zhangweichen
 * @create 2022-06-22 16:36
 */
public class CompareTest {

    /**
     * Comparable接口使用举例：
     * 1.
     * 2.
     * 3. 重写compareTo()的规则：
     */
    @Test
    public void test1() {

        String[] arr = {"AA", "CC", "KK", "MM", "GG", "JJ", "DD"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
