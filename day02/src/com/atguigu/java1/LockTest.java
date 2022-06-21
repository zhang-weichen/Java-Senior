package com.atguigu.java1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题的方式三：Lock锁  -- JDK5.0新增
 *
 * 1. 面试题：synchronized 与 Lock的异同？
 *   相同：二者都可以解决线程安全问题
 *   不同：synchronized机制在执行完相应的同步代码以后，自动释放同步监视器
 *          Lock需要手动启动同步（lock()）和手动结束同步（unlock()）
 *
 * 2.优先使用顺序：
 *   Lock  同步代码块（已经进入了方法体，分配了相应资源）  同步方法（在方法体之外）
 *
 *
 *  面试题：如何解决线程安全问题？有几种方式
 * @author zhangweichen
 * @create 2022-05-29 22:48
 */
class Window implements Runnable {

    private int tickets = 100;

    // 1. 实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {

        while (true) {
            try {
                // 2. 调用锁定方法：lock()，类似synchronized(obj)获取同步监视器
                lock.lock();

                if (tickets > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + ": 售票，票号：" + tickets);
                    tickets--;
                } else {
                    break;
                }
            } finally {

                // 3. 调用解锁方法：unlock()
                lock.unlock();
            }
        }
    }
}

public class LockTest {
    public static void main(String[] args) {

        Window window = new Window();

        Thread t1 = new Thread(window);
        Thread t2 = new Thread(window);
        Thread t3 = new Thread(window);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
