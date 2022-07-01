package com.atguigu.java1;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;


/**
 * @author zhangweichen
 * @create 2022-07-01 13:45
 */
public class TreeSetTest {

    /**
     * 1. 向TreeSet中添加数据，要求是同一类的对象。
     * 2. 两种排序方式：自然排序（实现Comparable接口） 定制排序（实现Comparator接口）。
     *
     * 3. 自然排序中，比较两个对象是否相同的标准：compareTo()返回0，不再是equals()。
     * 4. 定制排序中，比较两个对象是否相同的标准：compare()返回0，不再是equals()。
     */
    @Test
    public void test1(){

        TreeSet set = new TreeSet();

        // 异常：不能添加不同类的对象
//        set.add(123);
//        set.add(456);
//        set.add("AA");
//        set.add(new User("Tom", 12));

        // 例1：以升序输出
//        set.add(128);
//        set.add(8);
//        set.add(64);
//        set.add(64);
//        set.add(-16);
//        set.add(32);

        // 例2：以升序输出
        set.add(new User("Jerry", 12));
        set.add(new User("Tom", 8));
        set.add(new User("Jack", 18));
        set.add(new User("Jack", 38));
        set.add(new User("Mike", 15));
        set.add(new User("Jim", 22));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2(){

        Comparator comparator = new Comparator() {
            // 按照年龄升序排列
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }
                throw new RuntimeException("数据类型不匹配");
            }
        };

        TreeSet set = new TreeSet(comparator);  // 定制排序

        set.add(new User("Jerry", 12));
        set.add(new User("Tom", 8));
        set.add(new User("Jack", 18));
        set.add(new User("Jack", 38));
        set.add(new User("Mary", 38));  // 年龄相同，先到先存
        set.add(new User("Mike", 15));
        set.add(new User("Jim", 22));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
