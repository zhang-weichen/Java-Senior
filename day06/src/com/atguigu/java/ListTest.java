package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 1. List接口框架
 *   |----Collection接口：单列集合，用来存储一个一个的对象
 *        |----List接口：存储有序的、可重复的数据。  --> “动态”数组,替换原有的数组
 *             |----ArrayList：作为List接口的主要实现类；线程不安全的，效率高；底层使用Object[] elementData存储
 *             |----LinkedList：对于频繁的插入、删除操作，使用此类效率比ArrayList高；底层使用双向链表存储
 *             |----Vector：作为List接口的古老实现类；线程安全的，效率低；底层使用Object[] elementData存储
 *
 *
 * 2. ArrayList的源码分析：
 *
 *   2.1 JDK 7情况下
 *     ArrayList list = new ArrayList();  // 底层创建了长度是 10的 Object[]数组 elementData
 *     list.add(123);  // elementData[0] = new Integer(123);
 *     ...
 *     list.add(11);  // 如果此次的添加导致底层 elementData数组容量不够，则扩容。
 *     默认情况下，扩容为原来的容量的 1.5倍，同时需要将原有数组中的数据复制到新的数组中。
 *
 *     结论：建议开发中使用带参的构造器：ArrayList list = new ArrayList(int capacity)
 *
 *   2.2 JDK 8中 ArrayList的变化：
 *     ArrayList list = new ArrayList();  // 底层 Object[] elementData初始化为{}，并没有创建长度为 10的数组
 *
 *     list.add(123);  // 第一次调用 add()时，底层才创建了长度 10的数组，并将数据 123添加到 elementData[0]
 *     ...
 *     后续的添加和扩容操作与 JDK 7无异。
 *
 *   2.3 小结：
 *     JDK 7中的 ArrayList对象的创建类似于单例的饿汉式，而 JDK 8中的 ArrayList对象的创建类似于单例的懒汉式，延迟了数组的创建，节省内存。
 *
 * 3. LinkedList的源码分析：
 *      LinkedList list = new LinkedList();   // 内部声明了 Node类型的 first和 last属性，默认值为 null
 *      list.add(123);  // 将 123封装到 Node中，创建了 Node对象。
 *
 *      其中，Node定义为：
 *      private static class Node<E> {
 *          E item;
 *          Node<E> next;
 *          Node<E> prev;
 *
 *          Node(Node<E> prev, E element, Node<E> next) {
 *          this.item = element;
 *          this.next = next;
 *          this.prev = prev;
 *          }
 *      }
 *      体现了 LinkedList的双向链表结构
 *
 * 4. Vector的源码分析：JDK 7和 JDK 8中通过 Vector()构造器创建对象时，底层都创建了长度为 10的数组。
 *    在扩容方面，默认扩容为原来的数组长度的 2倍。
 *
 *
 *  面试题：ArrayList、LinkedList、Vector三者的异同
 *
 *  同：三个类都是实现了 List接口，存储数据的特点相同：存储有序的、可重复的数据
 *  不同：见上
 *
 * 5. List接口中的常用方法
 *
 * @author zhangweichen
 * @create 2022-06-28 14:20
 */
public class ListTest {

    /**
     * void add(int index, Object elem)：在 index位置插入 ele元素
     * boolean addAll(int index, Collection elems)：从 index位置开始将 elems中的所有元素添加进来
     * Object get(int index)：获取指定 index位置的元素
     * int indexOf(Object obj)：返回 obj在集合中首次出现的位置
     * int lastIndexOf(Object obj)：返回 obj在当前集合中末次出现的位置
     * Object remove(int index)：移除指定 index位置的元素，并返回此元素
     * Object set(int index, Object elem)：设置指定 index位置的元素为 ele
     * List subList(int fromIndex, int toIndex)：返回从 fromIndex到 toIndex位置的子集合
     *
     * 总结：常用方法
     * 增：add(Object obj)
     * 删：remove(int index) / remove(Object obj)
     * 改：set(int index, Object elem)
     * 查：get(int index)
     * 插：add(int index, Object elem)
     * 长度：size()
     *
     * 遍历的方法：
     *  1) Iterator迭代器方式
     *  2) 增强 for循环
     *  3) 普通的循环
     */
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom",12));
        list.add(456);

        System.out.println(list);

        // void add(int index, Object elem)：在index位置插入elem元素。
        list.add(1,"BB");
        System.out.println(list);

        // boolean addAll(int index, Collection elems)：从index位置开始将elems中的所有元素添加进来。
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
//        list.add(list1);  // 将list1作为一个对象存入list。
        System.out.println(list.size());  // 9

        //Object get(int index)：获取指定index位置的元素。
        System.out.println(list.get(0));
    }


    @Test
    public void test2(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom",12));
        list.add(456);

        // int indexOf(Object obj)：返回obj在集合中首次出现的位置。如果不存在，返回-1。
        int index = list.indexOf(4567);
        System.out.println(index);

        //int lastIndexOf(Object obj)：返回obj在当前集合中末次出现的位置。如果不存在，返回-1。
        System.out.println(list.lastIndexOf(456));

        //Object remove(int index)：移除指定index位置的元素，并返回此元素。
        Object obj = list.remove(0);
        System.out.println(obj);
        System.out.println(list);

        //Object set(int index, Object elem)：设置指定index位置的元素为elem。
        list.set(1, "CC");
        System.out.println(list);

        //List subList(int fromIndex, int toIndex)：返回从fromIndex到toIndex位置的左闭右开区间的子集合。
        List subList = list.subList(1, 3);
        System.out.println(subList);
        System.out.println(list);
    }

    @Test
    public void test3(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");

        //方式一：Iterator迭代器方式
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("***************");

        //方式二：增强for循环
        for(Object obj : list){
            System.out.println(obj);
        }

        System.out.println("***************");

        //方式三：普通for循环
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i));
        }
    }
}
