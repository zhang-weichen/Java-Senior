package com.atguigu.java;

/**
 * 多线程的创建：方式一：继承于Thread类
 * 1. 创建一个继承于Thread的子类
 * 2. 重写Thread类的run() --> 将此线程执行的操作声明在run()中
 * 3. 创建Thread类的子类的对象
 * 4. 通过此对象调用start()
 *
 * 例子：遍历100以内的偶数
 *
 * @author zhangweichen
 * @create 2022-04-17 13:40
 */

// 1. 创建一个继承于Thread的子类
class MyThread extends Thread {

    // 2. 重写Thread类的run()，内容为需要实现的操作
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                // Thread.currentThread().getName()获取执行当前代码的线程名
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {

        // 3. 创建Thread类的子类的对象
        MyThread t1 = new MyThread();

        // 4. 通过此对象调用start()：① 启动当前线程； ② 调用当前线程的run()
        t1.start();  // t1在调用start()后独立于main线程运行

        // 问题一：不能通过直接调用run()的方式启动线程
//        t1.run();  // 仅仅是调用t1中的run()，并没有启动一个新的线程，仍在main线程中执行

        // 问题二：再启动一个线程，遍历100以内的偶数
        // 不可以让已经调用的start()的线程再次调用start()，会报IllegalThreadStateException异常
//        t1.start();  // IllegalThreadStateException
        // 需要重新创建一个线程对象，调用start()
        MyThread t2 = new MyThread();
        t2.start();

//        System.out.println("hello");  // hello在遍历结果前输出

        // 如下操作仍然在main线程中执行
        for (int i = 0; i < 100; i++) {  // 与t1并行执行，具体输出结果与cpu调度策略有关
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
