package com.atguigu.exer;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 创建该类的 5个对象，并把这些对象放入 TreeSet集合中（下一章：TreeSet 需使用泛型来定义）
 * 分别按以下两种方式对集合中的元素进行排序，并遍历输出：
 *
 * 1) 使 Employee实现 Comparable接口，并按 name排序
 * 2) 创建 TreeSet时传入 Comparator对象，按生日日期的先后排序。
 *
 * @author zhangweichen
 * @create 2022-07-08 15:05
 */
public class EmployeeTest {

    // 1) 使用自然排序
    @Test
    public void test1() {
        TreeSet<Employee> set = new TreeSet<>();

        Employee e1 = new Employee("Adam", 55, new MyDate(1965,5,4));
        Employee e2 = new Employee("Tom", 43, new MyDate(1987,5,4));
        Employee e3 = new Employee("Jerry", 44, new MyDate(1987,5,9));
        Employee e4 = new Employee("Bob", 51,new MyDate(1954,8,12));
        Employee e5 = new Employee("Kitty", 21, new MyDate(1978,12,4));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator<Employee> iterator = set.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            System.out.println(employee);
        }
    }

    // 按生日排序
    @Test
    public void test2() {
        TreeSet<Employee> set = new TreeSet(new Comparator<Employee>() {
            // 使用泛型后的写法
            @Override
            public int compare(Employee e1, Employee e2) {
                MyDate date1 = e1.getBirthday();
                MyDate date2 = e2.getBirthday();
                return date1.compareTo(date2);
            }
        });

        Employee e1 = new Employee("Adam", 55, new MyDate(1965,5,4));
        Employee e2 = new Employee("Tom", 43, new MyDate(1987,5,4));
        Employee e3 = new Employee("Jerry", 44, new MyDate(1987,5,9));
        Employee e4 = new Employee("Bob", 51,new MyDate(1954,8,12));
        Employee e5 = new Employee("Kitty", 21, new MyDate(1978,12,4));

        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        for (Employee emp : set) {
            System.out.println(emp);
        }
    }
}
