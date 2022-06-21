package com.atguigu.java;

/**
 * 使用同步方法解决实现Runnable接口的线程安全问题
 *
 * @author zhangweichen
 * @create 2022-05-15 1:16
 */

class Window2 implements Runnable {

    // 不需再设定为static类型，因为只创建一个Window1对象，所有线程操作的均为此对象中的值
    private int tickets = 100;

    // 作为锁使用的对象，必须具有唯一性
    Object obj = new Object();

   /* private synchronized boolean sellTickets() {

        if (tickets > 0) {

            // 加入sleep()后，出现大量重票、错票问题，这就属于线程安全问题
            // 因为多个线程同时执行run()，对tickets的值进行修改
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + ": 售票，票号：" + tickets);
            tickets--;
            return true;
        } else {
            System.out.println("票已售空");
            return false;
        }
    }*/

    // 同步监视器：this
    private synchronized void sellTickets() {

        if (tickets > 0) {

            // 加入sleep()后，出现大量重票、错票问题，这就属于线程安全问题
            // 因为多个线程同时执行run()，对tickets的值进行修改
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + ": 售票，票号：" + tickets);
            tickets--;
        }
    }

    @Override
    public void run() {

        while (true) {

            sellTickets();
        }

        // 解决跳出循环的问题
//        while (sellTickets());
    }
}


public class WindowTest2 {
    public static void main(String[] args) {

        Window2 w = new Window2();

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