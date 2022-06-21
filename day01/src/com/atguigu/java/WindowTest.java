package com.atguigu.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用继承Thread类的方式
 *
 * 存在线程安全问题，待解决
 *
 * @author zhangweichen
 * @create 2022-04-24 23:04
 */
class Window extends Thread {

    // 设定为static类型，仍可能出现不同线程获取同一票号的问题，需通过线程同步解决
    private static int tickets = 100;

    @Override
    public void run() {

        while (true) {
            if (tickets > 0) {
                System.out.println(getName() + ": 售票，票号：" + tickets);
                tickets--;
            } else {
                System.out.println("票已售空");
                break;
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
