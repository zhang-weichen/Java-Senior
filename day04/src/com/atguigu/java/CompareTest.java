package com.atguigu.java;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一、说明：Java中的对象正常情况下可进行的比较操作： == 或 != ，不能使用 > 或 < 。
 *          但在开发场景中，对对象进行排序操作是十分常见的，这时就需要比较对象的大小。
 *          如何实现？ 使用两个接口中的任意一个：Comparable 或 Comparator
 *
 * 二、Comparable接口的使用
 *     Comparable接口的方式一旦一定，保证Comparable接口实现类的对象在任何位置都可以比较大小。
 *     Comparator接口属于临时性的比较。
 *
 *
 * @author zhangweichen
 * @create 2022-06-22 16:36
 */
public class CompareTest {

    /**
     * Comparable接口使用举例：  自然排序
     * 1. String、包装类等实现了Comparable接口，重写了compareTo(obj)方法，给出了比较两个对象大小的方式。
     * 2. String、包装类重写compareTo()方法以后，进行了从小到大的排列
     * 3. 重写compareTo(obj)的规则：
     *     1) 如果当前对象this大于形参对象obj，则返回正整数，
     *     2) 如果当前对象this小于形参对象obj，则返回负整数，
     *     3) 如果当前对象this等于形参对象obj，则返回零。
     *
     * 4. 对于自定义类，如果需要排序，我们可以让自定义类实现Comparable接口，重写compareTo(obj)方法。
     *    在compareTo(obj)方法中指明如何排序
     *
     */
    @Test
    public void test1() {

        String[] arr = {"AA", "CC", "KK", "MM", "GG", "JJ", "DD"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test2() {

        Goods[] arr = new Goods[5];

        arr[0] = new Goods("lenovoMouse",34);
        arr[1] = new Goods("dellMouse",43);
        arr[2] = new Goods("xiaomiMouse",12);
        arr[3] = new Goods("huaweiMouse",65);
        arr[4] = new Goods("microsoftMouse",43);

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Comparator接口的使用：定制排序
     * 1.背景：
     *     当元素的类型没有实现java.lang.Comparable接口而又不方便修改代码，
     *     或者实现了java.lang.Comparable接口的排序规则不适合当前的操作，
     *     那么可以考虑使用 Comparator 的对象来排序
     *
     * 2.重写compare(Object o1, Object o2)方法，比较o1和o2的大小：
     *     如果方法返回正整数，则表示o1大于o2；
     *     如果返回0，表示相等；
     *     返回负整数，表示o1小于o2。
     */
    @Test
    public void test3() {

        String[] arr = {"AA", "CC", "KK", "MM", "GG", "JJ", "DD"};
        Arrays.sort(arr, new Comparator() {

            // 按照字符串从大到小的顺序排列
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof String && o2 instanceof String) {
                    String s1 = (String) o1;
                    String s2 = (String) o2;

                    return -s1.compareTo(s2);
                }
                    throw new RuntimeException("传入的数据类型不一致");
            }
        });

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test4() {

        Goods[] arr = new Goods[5];

        arr[0] = new Goods("lenovoMouse",34);
        arr[1] = new Goods("dellMouse",43);
        arr[2] = new Goods("xiaomiMouse",12);
        arr[3] = new Goods("huaweiMouse",65);
        arr[4] = new Goods("microsoftMouse",43);

        Arrays.sort(arr, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {

                if(o1 instanceof Goods && o2 instanceof Goods) {
                    Goods g1 = (Goods) o1;
                    Goods g2 = (Goods) o2;

                    return -Double.compare(g1.getPrice(), g2.getPrice());
                } else {
                    throw new RuntimeException("传入的数据类型不一致");
                }
            }
        });
        System.out.println(Arrays.toString(arr));
    }
}
