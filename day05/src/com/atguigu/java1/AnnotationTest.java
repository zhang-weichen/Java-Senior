package com.atguigu.java1;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;

/**
 * 1. 理解Annotation:
 * ① jdk 5.0 新增的功能
 *
 * ② Annotation 其实就是代码里的特殊标记, 这些标记可以在编译、类加载,、运行时被读取, 并执行相应的处理。
 *   通过使用 Annotation,程序员可以在不改变原有逻辑的情况下, 在源文件中嵌入一些补充信息。
 *
 * ③ 在JavaSE中，注解的使用目的比较简单，例如标记过时的功能，忽略警告等。
 *   在JavaEE、Android中注解占据了更重要的角色，例如用来配置应用程序的任何切面，代替JavaEE旧版中所遗留的繁冗代码和XML配置等。
 *
 * 2. Annotation的使用示例
 *  示例一：生成文档相关的注解
 *  示例二：在编译时进行格式检查（JDK内置的三个基本注解）
 *      @Override: 限定重写父类方法, 该注解只能用于方法
 *      @Deprecated: 用于表示所修饰的元素（类、方法）已过时。通常是因为所修饰的结构危险或存在更好的选择
 *      @SuppressWarnings: 抑制编译器警告
 *
 *  示例三：跟踪代码依赖性，实现替代配置文件功能
 *
 * 3. 如何自定义注解：参照 @SuppressWarnings 定义
 *     ① 注解声明为：@interface
 *     ② 内部定义成员，通常使用value表示
 *     ③ 可以指定成员的默认值，使用default定义
 *     ④ 如果自定义注解没有成员，表明是一个标识作用。
 *
 *    如果注解有成员，在使用注解时，需要指明成员的值。
 *    自定义注解必须配上注解的信息处理流程（使用反射）才有意义。
 *    自定义注解通过都会指明两个元注解：Retention、Target
 *
 * 4. jdk 提供的4种元注解
 *    元注解：对现有的注解进行解释说明的注解
 *      Retention：指定所修饰的 Annotation 的生命周期：
 *         SOURCE
 *         CLASS（默认行为）
 *         RUNTIME 只有声明为 RUNTIME生命周期的注解，才能通过反射获取。
 *
 *      Target:用于指定被修饰的 Annotation 能用于修饰哪些程序元素
 *
 *      出现的频率较低的2种元注解：
 *      Documented：表示所修饰的注解在被javadoc解析时保留下来。默认情况下javadoc是不包括注解信息的。
 *      Inherited：被它修饰的 Annotation 将具有继承性。
 *
 * 5.通过反射获取注解信息 ---到反射内容时系统讲解
 *
 * 6. jdk 8 中注解的新特性：可重复注解、类型注解
 *
 *   6.1 可重复注解：
 *     ① 在 MyAnnotation上声明 @Repeatable，成员值为 MyAnnotations.class
 *     ② MyAnnotation的 Target和 Retention等元注解与 MyAnnotations相同。
 *
 *   6.2 类型注解： ElementType.TYPE_PARAMETER 表示该注解能写在类型变量的声明语句中（如：泛型声明）。
 * ElementType.TYPE_USE 表示该注解能写在使用类型的任何语句中。
 *
 *
 * @author zhangweichen
 * @create 2022-04-27 23:09
 */
public class AnnotationTest {
    public static void main(String[] args) {

        Person stu = new Student();
        stu.walk();

        // Date类中的此构造器有@Deprecated注解，表示此构造方法已过时，不推荐使用，但并不是不能使用
        Date date = new Date(2022, 4, 27);
        System.out.println(date);

        // IDEA中未使用的变量会以灰色显示，相当于一类较弱的警告
        int num = 10;

        // 添加包含"unused"参数的@SuppressWarnings注解后，抑制编译器对此的警告
        @SuppressWarnings("unused")
        int num1 = 10;

        // 抑制编译器对"变量未使用"和"未使用泛型"的警告
        @SuppressWarnings({"unused", "rawtypes"})
        ArrayList list = new ArrayList();
    }

    @Test
    public void testGetAnnotation() {
        Class studentClass = Student.class;
        Annotation[] annotations = studentClass.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);  // @com.atguigu.java1.MyAnnotation(value=world)，说明子类Student继承了父类Person的MyAnnotation注解
        }
    }
}

@MyAnnotation("world")
class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk() {
        System.out.println("person walk");
    }

    public void eat() {
        System.out.println("person eat");
    }
}

interface info {
    void show();
}

class Student extends Person implements info {

    // @Override注解对方法重写和实现进行校验，此处方法名与接口中须实现的方法名不一致时，会生成error
//    @Override
//    public void wolk() {
//        System.out.println("student walk");
//    }

    @Override
    public void walk() {
        System.out.println("student walk");
    }

    @Override
    public void show() {
    }
}