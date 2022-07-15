package com.atguigu.java;

import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author zhangweichen
 * @create 2021-12-08-9:42
 */
public class ClassLoaderTest {

    @Test
    public void test1() {

        // 对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);  // AppClassLoader

        // 调用系统类加载器的getparent()方法：获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);  // ExtClassLoader

        // 调用扩展类加载器的getparent()方法：无法获取引导类加载器
        // 引导类加载器主要负责加载Java的核心类库，无法加载自定义类
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);  // null

        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println(classLoader3);  // null
    }

    /**
     * Properties：读取配置文件
     */
    @Test
    public void test2() throws Exception {

        Properties pros = new Properties();

        // 读取配置文件的方式一：使用文件输入流
        // 此时配置文件的默认位置：当前的module下
//        FileInputStream fis = new FileInputStream("jdbc.properties");
//        pros.load(fis);

        // 读取配置文件的方式二：使用类加载器的getResourceAsStream()方法
        // 此时配置文件的默认位置：当前module的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream in = classLoader.getResourceAsStream("jdbc1.properties");
        pros.load(in);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user = " + user + ", password = " + password);
    }
}
