package com.atguigu.java2;

/**
 * 一、集合框架的概述
 *
 * 1.集合、数组都是对多个数据进行存储操作的结构，简称Java容器。
 *   说明：此时的存储，主要指的是内存层面的存储，不涉及到持久化的存储（.txt,.jpg,.avi，数据库中）
 *
 * 2.1 数组在存储多个数据方面的特点：
 *     -> 一旦初始化以后，其长度就确定了。
 *     -> 数组一旦定义完成，其元素的类型也就确定了，只能操作指定类型的数据。
 *        比如：String[] arr;int[] arr1;Object[] arr2;
 *
 * 2.2 数组在存储多个数据方面的缺点：
 *     -> 一旦初始化以后，其长度就不可修改。
 *     -> 数组中提供的方法非常有限，对于添加、删除、插入数据等操作，非常不便，同时效率不高。
 *     -> 对于获取数组中实际元素的个数的需求，数组没有直接的属性或方法可用
 *     -> 数组存储数据的特点：有序、可重复。对于无序、不可重复的需求，无法满足。
 *
 *  二、集合框架
 *       |----Collection接口：单列集合，用来存储一个一个的对象
 *           |----List接口：存储有序的、可重复的数据。  -> "动态"数组
 *               |----ArrayList、LinkedList、Vector
 *
 *           |----Set接口：存储无序的、不可重复的数据   -> "集合"
 *               |----HashSet、LinkedHashSet、TreeSet
 *
 *       |----Map接口：双列集合，用来存储一对(key - value)一对的数据   -> 函数：y = f(x)
 *               |----HashMap、LinkedHashMap、TreeMap、HashTable、Properties
 *
 *
 * 三、Collection接口中的方法的使用
 *
 * @author zhangweichen
 * @create 2022-05-18 23:36
 */
public class CollectionTest {
}
