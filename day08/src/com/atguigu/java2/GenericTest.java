package com.atguigu.java2;

import org.junit.Test;

import java.util.*;

/**
 * 1. 泛型在继承方面的体现
 *
 * 2. 通配符的使用
 *
 * @author zhangweichen
 * @create 2022-07-11 14:33
 */
public class GenericTest {

    /**
     * 泛型在继承方面的体现
     *
     * 类 A是类 B的父类，但是 G<A>和 G<B>二者不具备子父类关系，二者是并列关系。
     */
    @Test
    public void test1() {
        Object obj = null;
        String str = null;
        obj = str;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;

        // 编译不通过
//        Date date = new Date();
//        str = date;

        // 此时list1和list2的类型不具有子父类关系
        // 编译不通过
        List<Object> list1 = null;
        List<String> list2 = null;

//        list1 = list2;
        /*
        反证法：
        假设"list1 = list2;"通过编译
        执行"list.add(123);"会导致混入非String类型的数据
        */

        show(list1);
//        show(list2);
        show1(list2);
    }

    // 仅能用于List<Object>类型对象的显示，无法用于List<String>类型对象的显示，缺乏通用性 --> 引入通配符 '?'
    public void show(List<Object> list){
        System.out.println(list);
    }

    public void show1(List<String> list){
        System.out.println(list);
    }

    /**
     * 补充：类 A是类 B的父类，A<G>是 B<G>的父类
     */
    @Test
    public void test2() {

        List<String> list1 = null;
        AbstractList<String> list2 = null;
        ArrayList<String> list3 = null;

        list1 = list2;
        list1 = list3;
        list2 = list3;

        List<String> list4 = new ArrayList<>();
    }

    /**
     * 通配符的使用
     * 通配符：?
     *
     * 类 A是类 B的父类，G<A>和 G<B>是没有关系的，二者共同的父类是：G<?>
     */
    @Test
    public void test3() {
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;

        // 编译通过
        list = list1;
        list = list2;
//        print(list1);
//        print(list2);

        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");

        list = list3;
        // 添加（写）：List<?>不能向其内部添加数据，除了添加null。
//        list = list.add("DD");
        list.add(null);

        // 获取（读）：List<?>允许读取数据，读取的数据类型为Object。
        Object o = list.get(0);
        System.out.println(o);
    }

    public void print(List<?> list) {
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    /**
     * 有限制条件的通配符的使用。
     *     ? extends A:
     *         G<? extends A> 可以作为 G<A>和 G<B>的父类，其中 B是 A的子类
     *
     *     ? super A:
     *         G<? super A> 可以作为 G<A>和 G<B>的父类，其中 B是 A的父类
     */
    @Test
    public void test4() {

        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        List<Student> list3 = new ArrayList<Student>();
        List<Person> list4 = new ArrayList<Person>();
        List<Object> list5 = new ArrayList<Object>();

        list1 = list3;
        list1 = list4;
//        list1 = list5;

//        list2 = list3;
        list2 = list4;
        list2 = list5;

        // 读取数据：
        list1 = list4;
        Person p1 = list1.get(0);
        // <? extend Person>可以使用Person或Person的父类接收返回值
//        Student s = list1.get(0);

        list2 = list4;
        Object object = list2.get(0);
        // <? super Person>只能使用Object类型接受父类
//        Person p2 = list2.get(0);

        // 写入数据：
        // <? extend Person>不允许写入非null以外的数据，因为有可能写入非?及其子类的对象
//        list1.add(new Student());
        list1.add(null);

        // <? super Person>允许写入Person及其子类的对象
        list2.add(new Person());
        list2.add(new Student());
    }
}
