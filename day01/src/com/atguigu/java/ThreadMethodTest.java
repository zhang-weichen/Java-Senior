package com.atguigu.java;

/**
 * 1. start():启动当前线程；调用当前线程的run()
 * 2. run(): 通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在此方法中
 * 3. currentThread(): 静态方法，返回执行当前代码的线程
 * 4. getName(): 获取当前线程的名字
 * 5. setName(): 设置当前线程的名字
 *
 * 6. yield(): 主动释放当前cpu的执行权
 * 7. join(): 在线程a中调用线程b的join(),此时线程a就进入阻塞状态，直到线程b完全执行完以后，线程a才结束阻塞状态。
 *
 * 8. stop(): 已过时。当执行此方法时，强制结束当前线程。
 * 9. sleep(long millitime): 让当前线程“睡眠”指定的millitime毫秒。在指定的millitime毫秒时间内，当前线程是阻塞状态。
 * 10. isAlive(): 判断当前线程是否存活，返回一个boolean值，true和false分别代表该线程存活和消亡
 *
 *  线程的优先级：
 *  1.
 *  MAX_PRIORITY：10
 *  MIN _PRIORITY：1
 *  NORM_PRIORITY：5  --> 默认优先级
 *
 *  2.如何获取和设置当前线程的优先级：
 *  getPriority(): 获取线程的优先级
 *  setPriority(int p): 设置线程的优先级
 *
 *  说明：高优先级的线程要抢占低优先级线程cpu的执行权。但是只是从概率上讲，高优先级的线程高概率被执行。
 *  并不意味着只有当高优先级的线程执行完以后，低优先级的线程才执行。
 *
 * @author zhangweichen
 * @create 2022-04-21 23:54
 */

class HelloThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {

                // 无法使用throws抛出异常，因为父类中run()未抛出异常
                try {
                    // 线程阻塞100ms
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread name: " + getName() + ", priority: " + getPriority() + ", value: " + i);
            }

            // 主动释放cpu执行权，但不表示一定暂停执行，有可能下次调度的仍是此线程
            if (i % 20 == 0) {
                Thread.yield();
            }
        }
    }

    public HelloThread(String name) {
        super(name);
    }
}

public class ThreadMethodTest {
    public static void main(String[] args) {

        // 使用构造器命名
        HelloThread h1 = new HelloThread("Thread-hello");

        // 使用setName()为h1线程命名
//        h1.setName("Thread-hello");

        // 设置h1线程的优先级
        h1.setPriority(Thread.MAX_PRIORITY);

        h1.start();

        // 给主线程命名
        Thread.currentThread().setName("Thread-main");

        // 设置主线程的优先级
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println("thead name: " + Thread.currentThread().getName() + ", priority: " + Thread.currentThread().getPriority() + ", value: " + i);
            }

//            if (i % 20 == 0) {
//                yield();
//            }

//            if (i == 20) {
//                try {
//                    // 此线程进入阻塞状态，直至线程h1执行完毕
//                    h1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

        }

        // 判断线程是否存活
        System.out.println(h1.isAlive());  // false
        System.out.println(Thread.currentThread().isAlive());  // true
    }
}
