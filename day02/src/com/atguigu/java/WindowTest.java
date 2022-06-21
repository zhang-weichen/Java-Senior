package com.atguigu.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用继承Thread类的方式
 *
 * 使用同步代码块的方式解决继承Thread方式的线程安全问题
 *
 * 说明：在继承Thread类创建多线程的方式中，慎用this充当同步监视器，可以考虑使用当前类充当同步监视器。
 *
 * @author zhangweichen
 * @create 2022-04-24 23:04
 */
class Window extends Thread {

    // 设定为static类型，仍可能出现不同线程获取同一票号的问题，需通过线程同步解决
    private static int tickets = 100;

    // 充当同步监视器的对象需设置为static类型，以确保多个线程共用同一把锁
    private static Object obj = new Object();

    @Override
    public void run() {

        // 错误的方式：使用继承Thread类方式创建线程不能使用this作为同步监视器，因为this不具有唯一性，分别表示不同的线程实例
//        synchronized (this) {

        // 正确的方式：反射的方式，类也是对象。Class cls = Window.class; Window.class只会加载一次，也具有唯一性
//        synchronized (Window.class) {
        synchronized (obj) {
            while (true) {
                if (tickets > 0) {

                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(getName() + ": 售票，票号：" + tickets);
                    tickets--;
                } else {
                    System.out.println("票已售空");
                    break;
                }
            }
        }
    }
}

public class WindowTest {
    public static void main(String[] args) {

        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }
}
