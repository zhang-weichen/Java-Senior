package com.atguigu.java;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 一、Map的实现类的结构：
 * |----Map:双列数据，存储 key-value 对的数据 --> 类似于函数：y = f(x)
 *      |----HashMap：作为 Map的主要实现类；线程不安全的，效率高；存储 null的 key和 value
 *           |----LinkedHashMap：保证可以按照元素的添加顺序对 map中的元素进行遍历。
 *                      原因：在原有的 HashMap底层结构基础上维护了一对指针，分别指向前一个和后一个元素。
 *                      对于频繁的遍历操作，此类执行效率高于HashMap。
 *      |----TreeMap：使用有序的数据结构对 key-value元素进行存储，实现排序遍历。通常根据 key进行自然排序或定制排序
 *                    底层使用红黑树
 *      |----Hashtable：作为古老的实现类；线程安全的，效率低；不能存储 null的 key和 value
 *           |----Properties：常用来处理配置文件。key和 value都是 String类型
 *
 *
 *      HashMap的底层：数组 + 链表（JDK 7及之前）
 *                    数组 + 链表 + 红黑树（JDK 8）
 *
 * @author zhangweichen
 * @create 2022-07-01 14:59
 */
public class MapTest {

    @Test
    public void test1() {
        Map hashMap = new HashMap();
        Map hashtable = new Hashtable();

        hashMap.put(null, null);
//        hashtable.put(null, null);  // NullPointerException
    }
}
