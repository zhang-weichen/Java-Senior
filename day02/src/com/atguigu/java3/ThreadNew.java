package com.atguigu.java3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式三：实现 Callable接口。 --- JDK 5.0新增
 *
 * 如何理解实现Callable接口的方式创建多线程比实现 Runnable接口创建多线程方式强大？
 * 1. call()可以有返回值的。
 * 2. call()可以抛出异常，被外部操作捕获，获取异常的信息
 * 3. Callable支持泛型
 *
 * @author zhangweichen
 * @create 2022-07-05 11:08
 */

// 1. 创建一个Callable接口的实现类
class NumThread implements Callable {

    // 2. 实现call()，将此线程需要执行操作声明在call()中。
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

public class ThreadNew {
    public static void main(String[] args) {

        // 3. 创建Callable接口实现类的对象
        NumThread numThread = new NumThread();

        // 4. 将Callable接口实现类的对象作为参数传入FutureTask构造器中，创建FutureTask对象
        FutureTask futureTask = new FutureTask(numThread);

        // 5. 将FutureTask对象作为参数传入Thread构造器中，创建Thread对象并调用start()方法启动线程
        new Thread(futureTask).start();

        try {
            // 6. 获取Callable中call()的返回值
            // get()的返回值即作为FutureTask构造器参数的Callable实现类中重写的call()的返回值。
            Object sum = futureTask.get();
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
