package com.atguigu.exer;

/**
 * 练习：创建两个分线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
 *
 * @author zhangweichen
 * @create 2022-04-17 14:19
 */
class MyThread1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

class MyThread2 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " +i);
            }
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {

//        MyThread1 t1 = new MyThread1();
//        MyThread2 t2 = new MyThread2();
//
//        t1.start();
//        t2.start();

        // 创建的线程对象只使用了一次，考虑使用匿名方式进行
        // 创建匿名对象的方式
//        new MyThread1().start();
//        new MyThread2().start();

        // 创建Thread类的匿名子类的方式
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                    }
                }
            }
        }.start();
    }
}
