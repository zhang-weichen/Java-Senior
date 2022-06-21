package com.atguigu.java;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {

    // 反射之前，对于Person类的操作
    @Test
    public void test1() {

        // 1. 创建Person类的对象
        Person p1 = new Person("Tom", 12);

        // 2. 通过对象，调用其内部属性和方法
        p1.age = 10;
        System.out.println(p1.toString());

        p1.show();
    }

        // 在Person类的外部，不可通过Person类的对象调用其私有结构。
        // 如：name、showNation()以及私有的构造器

    // 反射之后，对于Person的操作
    @Test
    public void test2() throws Exception {
        Class cls =  Person.class;  // Person.class可以看作Person类本身。个人理解，也可以看作运行时的字节码文件Person.class

        // 1. 通过反射，创建Person类的对象
        Constructor cons = cls.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("Tom", 12);
        Person p = (Person)obj;
        System.out.println(p);

        // 2. 通过反射，调用对象指定的属性、方法
        // 调用属性
        Field age =  cls.getDeclaredField("age");
        age.set(p, 10);
        System.out.println(p.toString());

        // 调用方法
        Method show = cls.getDeclaredMethod("show");
        show.invoke(p);

        System.out.println("****************************");

        // 3. 通过反射，可以调用Person类的私有结构。如：私有的属性、构造器、方法
        // 调用私有的构造器，创建Person类的对象
        Constructor cons1 = cls.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p1 = (Person) cons1.newInstance("Jerry");
        System.out.println(p1.toString());

        // 调用私有的属性
        Field name = cls.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1, "Jack");
        System.out.println(p1);

        // 调用私有的方法
        Method showNation = cls.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p1, "China");  // 相当于p1.showNation("中国")
        System.out.println(nation);
    }

    // 疑问1：通过直接new的方式和反射的都可以调用公共结构，开发中如何选择？
    // 建议：使用直接new的方式
    // 何时使用反射的方式。反射的特征：动态性
    // 例：后端服务器程序运行时，接受来自前端不同类型的请求，根据请求的类型的不同，动态创建对应的对象

    // 疑问2：反射机制与面向对象中的封装性是否矛盾？如何看待两个技术
    // 不矛盾。

    /*
    关于java.lang.Class类的理解
    1. 类的加载过程：
    源代码(.java)经javac.exe命令编译后，生成一个或多个字节码文件(.class)，
    然后使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中。
    此过程称为类的加载。
    加载到内存中的类，称为运行时类，此运行时类，就作为Class类的一个实例。

    2. Class的实例对应着一个运行时类

    3. 加载到内存中的运行时类，会缓存一定时间。在此时间内，可以通过不同方式获取此运行时类。
     */

    // 万事万物皆对象？对象.xxx，File，URL，反射，前端，数据库操作

    // 获取Class实例的四种方式（前三种需掌握）
    @Test
    public void test3() throws ClassNotFoundException {
        // 方式一：调用运行时类的属性：.class
        Class cls1 = Person.class;
        System.out.println(cls1);  // class com.atguigu.java.Person

        // 方式二：通过运行时类的对象，调用getClass()
        Person p1 = new Person();
        Class cls2 = p1.getClass();
        System.out.println(cls1);  // class com.atguigu.java.Person

        // 方式三：调用Class的静态方法：forName(String classPath)
        Class cls3 = Class.forName("com.atguigu.java.Person");
        System.out.println(cls3);  // class com.atguigu.java.Person

        System.out.println(cls1 == cls2);  // true
        System.out.println(cls1 == cls3);  // true

        // 方式四：使用类的加载器：ClassLoader（了解）
        ClassLoader classloader = ReflectionTest.class.getClassLoader();
        Class cls4 = classloader.loadClass("com.atguigu.java.Person");
        System.out.println(cls1 == cls4);  // ture

    }

    @Test
    public void test4() {
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        // 只要元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }
}
