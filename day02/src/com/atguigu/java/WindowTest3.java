package com.atguigu.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用继承Thread类的方式
 *
 * 使用同步代码方法解决继承Thread方式的线程安全问题
 *
 * 关于同步方法的总结：
 * 1. 同步方法仍涉及同步监视器，只是不需要显示声明
 * 2. 非静态的同步方法使用的同步监视器：this
 *    静态的同步方法使用的同步监视器：当前类本身，类名.class
 *
 * @author zhangweichen
 * @create 2022-04-24 23:04
 */
class Window3 extends Thread {

    // 设定为static类型，仍可能出现不同线程获取同一票号的问题，需通过线程同步解决
    private static int tickets = 100;


    @Override
    public void run() {

        while (true) {
            sellTickets();
        }
    }

    // 同步监视器：Window3.class
    private static synchronized void sellTickets() {

// 同步监视器：this，即t1, t2, t3，故此实现是错误的
//    private synchronized void sellTickets() {

        if (tickets > 0) {

            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + ": 售票，票号：" + tickets);
            tickets--;
        }
    }
}

public class WindowTest3 {
    public static void main(String[] args) {

        Window3 w1 = new Window3();
        Window3 w2 = new Window3();
        Window3 w3 = new Window3();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }
}
