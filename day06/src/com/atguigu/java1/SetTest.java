package com.atguigu.java1;

import com.atguigu.java.Person;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * 1. Set接口的框架：
 *
 * |----Collection接口：单列集合，用来存储一个一个的对象
 *      |----Set接口：存储无序的、不可重复的数据  --> “集合”
 *           |----HashSet：作为Set接口的主要实现类；线程不安全的；可以存储null值
 *                |----LinkedHashSet：作为 HashSet的子类；遍历其内部数据时，可以按照添加的顺序遍历
 *                                      对于频繁的遍历操作，LinkedHashSet效率高于 HashSet.
 *           |----TreeSet：可以按照添加对象的指定属性，进行排序。
 *
 *
 *  1. Set接口中没有额外定义新的方法，使用的都是Collection中声明过的方法。
 *
 *  2. 要求：向Set（主要指：HashSet、LinkedHashSet）中添加的数据，其所在的类一定要重写 hashCode()和 equals()
 *     要求：重写的 hashCode()和 equals()尽可能保持一致性：相等的对象必须具有相等的散列码
 *      重写两个方法的小技巧：对象中用作 equals()方法比较的 Field，都应该用来计算 hashCode值。
 *
 * @author zhangweichen
 * @create 2022-07-01 13:44
 *
 */
public class SetTest {

    /**
     * 一、Set：存储无序的、不可重复的数据
     *     以HashSet为例说明：
     *     1. 无序性：不等于随机性。存储的数据在底层数组中并非按照数组索引的顺序添加，而是由数据的哈希值决定。
     *
     *     2. 不可重复性：保证添加的元素调用 equals()进行判断时，不能返回 true。即：相同的元素只能添加一个。
     *
     * 二、添加元素的过程：
     *     以HashSet为例：
     *        我们向 HashSet中添加元素 a,首先调用元素 a所在类的 hashCode()方法，计算元素 a的哈希值，此哈希值通过某种算法
     *        计算出元素 a在 HashSet底层数组中的存放位置（即索引位置），判断数组此位置是否已经存放了元素：
     *           如果此位置没有存放元素，则元素 a添加成功。 --> 情况 1
     *           如果此位置已存放其他元素 b（或以链表形式存在的多个元素），则比较元素 a与元素 b的 hash值：
     *              如果 hash值不相同，则元素 a添加成功。 --> 情况 2
     *              如果 hash值相同，进而需要调用元素 a所在类的 equals()方法，与该位置上的所有元素进行比较判断：
     *                 equals()返回 true，元素 a添加失败
     *                 equals()均返回 false，则元素 a添加成功。 --> 情况 3
     *
     *     对于添加成功的情况 2和情况 3而言：元素 a与已经存在指定索引位置上数据以链表的方式存储。
     *     JDK 7 :元素 a放到数组中，指向原来的元素。
     *     JDK 8 :原来的元素在数组中，指向元素 a
     *     总结：七上八下
     *
     *     HashSet底层：数组 + 链表
     */

    @Test
    public void test1(){

        Set set = new HashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new User("Tom", 12));
        set.add(new User("Tom", 12));
        set.add(129);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * LinkedHashSet的使用
     * LinkedHashSet作为 HashSet的子类，在添加数据的同时，每个数据还维护了两个引用，记录此数据前一个数据和后一个数据。
     * 优点：对于频繁的遍历操作，LinkedHashSet效率高于 HashSet
     */
    @Test
    public void test2() {
        Set set = new LinkedHashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add("CC");
        set.add(new User("Tom",12));
        set.add(new User("Tom",12));
        set.add(129);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
