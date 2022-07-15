package com.atguigu.java;

import org.junit.Test;

import java.util.Random;

/**
 * 通过反射创建对应的运行时类的对象
 * 
 * @author zhangweichen
 * @create 2021-12-08-10:52
 */

public class NewInstanceTest {

    /**
     * newInstance()：调用此方法，创建对应运行时类的对象。内部调用了运行时类的空参构造器
     *
     * 使此方法正常创建运行时类的对象，要求：
     * 1. 运行时类必须提供空参的构造器
     * 2. 空参的构造器的访问权限足够。通常设置为 public
     *
     * 在 javabean中要求提供一个 public的空参构造器。原因：
     * 1. 便于通过反射，创建运行时类的对象
     * 2. 便于继承此类的子类实例化时，可以正常使用 super()调用父类构造器
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void test1() throws InstantiationException, IllegalAccessException {
        
        Class<Person> cls = Person.class;

        Object obj = cls.newInstance();
        System.out.println(obj);
    }

    // 反射的动态性
    @Test
    public void test2() {

        int num = new Random().nextInt(3);  // 0, 1, 2
        String classPath = "";
        switch (num){
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.sql.Date";
                break;
            case 2:
                classPath = "com.atguigu.java.Person";
                break;
        }

        try {
            Object obj = getInstance(classPath);
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建一个指定类的对象。
     * classPath：指定类的全类名
     */
    public Object getInstance(String classPath) throws Exception {
        Class cls = Class.forName(classPath);
        return  cls.newInstance();
    }
}
