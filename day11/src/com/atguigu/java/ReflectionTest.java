package com.atguigu.java;

/**
 * 疑问 1：通过直接 new的方式和反射的都可以调用公共结构，开发中如何选择？
 * 建议：使用直接 new的方式
 * 何时使用反射的方式。反射的特征：动态性
 * 例：后端服务器程序运行时，接受来自前端不同类型的请求，根据请求的类型的不同，动态创建对应的对象（Servlet对象）
 *
 * 疑问 2：反射机制与面向对象中的封装性是否矛盾？如何看待两个技术
 * 不矛盾。
 *
 * 关于 java.lang.Class类的理解
 * 1. 类的加载过程：
 *    源代码(.java)经 javac.exe命令编译后，生成一个或多个字节码文件（.class），
 *    然后使用 java.exe命令对某个字节码文件进行解释运行，相当于将某个字节码文件加载到内存中。
 *    此过程称为类的加载。
 *    加载到内存中的类，称为运行时类，此运行时类，就作为 Class类的一个实例。
 *
 * 2. Class的实例对应着一个运行时类
 *
 * 3. 加载到内存中的运行时类，会缓存一定时间。在此时间内，可以通过不同方式获取此运行时类。
 *
 * 万事万物皆对象？ 对象.xxx，File，URL，反射，前端，数据库操作
 *
 * @author zhangweichen
 * @created 2022-07-15 16:10
 */


import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {

    /**
     * 反射之前，对于 Person类的操作
     *
     * 在 Person类的外部，不可通过 Person类的对象调用其私有结构。
     *  如：name、showNation()以及私有的构造器
     */
    @Test
    public void test1() {

        // 1. 创建Person类的对象
        Person p1 = new Person("Tom", 12);

        // 2. 通过对象，调用其内部属性和方法
        p1.age = 10;
        System.out.println(p1.toString());

        p1.show();
    }

    /**
     * 使用反射后，对于 Person的操作
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Class cls =  Person.class;  // Person.class可以看作Person类本身。个人理解，也可以看作运行时的字节码文件Person.class

        // 1. 通过反射，创建Person类的对象
        // 1.1 使用getConstructor()获取构造器对象，不同的形参对应不同的构造器对象
        Constructor cons = cls.getConstructor(String.class, int.class);

        // 1.2 使用构造器对象newInstance()创建对象
        Object obj = cons.newInstance("Tom", 12);

        Person p = (Person)obj;
        System.out.println(p);

        // 2. 通过反射，调用对象指定的属性、方法
        // 2.1 通过Class对象的getDeclaredField()获取属性对象
        Field age =  cls.getDeclaredField("age");
        // 通过属性对象的set()设置属性
        age.set(p, 10);
        System.out.println(p);

        // 2.2 通过Class对象的getDeclaredMethod()获取方法对象
        Method show = cls.getDeclaredMethod("show");
        // 通过方法对象的invoke()调用方法
        show.invoke(p);

        System.out.println("****************************");

        // 3. 通过反射，可以调用Person类的私有结构。如：私有的属性、构造器、方法
        // 调用私有的构造器，创建Person类的对象
        Constructor cons1 = cls.getDeclaredConstructor(String.class);
        // 设置private结构可在外部调用
        cons1.setAccessible(true);
        Person p1 = (Person) cons1.newInstance("Jerry");
        System.out.println(p1);

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


    /**
     * 获取Class实例的四种方式（前三种需掌握）
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void test3() throws ClassNotFoundException {
        // 方式一：调用运行时类的属性：.class
        Class<Person> cls1 = Person.class;
        System.out.println(cls1);  // class com.atguigu.java.Person

        // 方式二：通过运行时类的对象，调用getClass()
        Person p1 = new Person();
        Class cls2 = p1.getClass();
        System.out.println(cls2);  // class com.atguigu.java.Person

        // 方式三：调用Class的静态方法：forName(String classPath)，最常用
        Class cls3 = Class.forName("com.atguigu.java.Person");
//        Class cls3 = Class.forName("java.lang.String");
        System.out.println(cls3);  // class com.atguigu.java.Person

        // 某个类的运行时类的对象唯一
        System.out.println(cls1 == cls2);  // true
        System.out.println(cls1 == cls3);  // true

        // 方式四：使用类的加载器：ClassLoader（了解）
        ClassLoader classloader = ReflectionTest.class.getClassLoader();
        Class cls4 = classloader.loadClass("com.atguigu.java.Person");
        System.out.println(cls1 == cls4);  // ture

    }

    /**
     * Class结构可以是哪些实例
     */
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
        // 只要数组的元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }
}
