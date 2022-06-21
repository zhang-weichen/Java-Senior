package com.atguigu.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用实现Runnable接口的方式
 *
 * 存在线程安全问题，待解决
 *
 * @author zhangweichen
 * @create 2022-04-24 23:04
 */
class Window1 implements Runnable {

    // 不需再设定为static类型，因为只创建一个Window1对象，所有线程操作的均为此对象中的值
    private int tickets = 100;

    @Override
    public void run() {

        while (true) {
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + ": 售票，票号：" + tickets);
                tickets--;
            } else {
                System.out.println("票已售空");
                break;
            }
        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {

        Window1 w = new Window1();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
