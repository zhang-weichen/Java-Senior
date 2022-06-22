package com.atguigu.java2;

/**
 * 线程通信的例子：使用两个线程打印 1-100。线程1、线程2 交替打印
 *
 * 线程通信涉及的三个方法：
 * wait()：一旦执行此方法，当前线程就进入阻塞状态，并释放同步监视器。
 * notify()：一旦执行此方法，就会唤醒被wait的一个线程。如果有多个线程被wait，就唤醒优先级最高的那个。
 * notifyAll()：一旦执行此方法，就会唤醒所有被wait的方法。
 *
 * 说明：
 * 1. wait()、notify()、notifyAll()三个方法必须在同步代码块或同步方法中使用。
 * 2. wait()、notify()、notifyAll()三个方法的调用者必须是同步代码块或同步方法中的同步监视器。
 *    否则会出现IllegalMonitorStateException。
 * 3. wait()、notify()、notifyAll()三个方法定义在java.lang.Object类中。
 *
 * 面试题：sleep() 和 wait() 的异同？
 * 1. 相同点：一旦执行方法，都可以使当前线程进入阻塞状态。
 * 2. 不同点：  1) 两个方法声明位置不同：sleep()声明在Thread类中，wait()声明在Object类中
 *             2) 调用要求不同：sleep()可以在任何需要的场景下调用。 wait()必须使用在同步代码块或同步方法中。
 *             3) 关于是否释放同步监视器：如果两个方法都使用在同步代码块或同步方法中，sleep()不会释放锁，wait()会释放锁。
 *
 * @author zhangweichen
 * @create 2022-06-22 10:00
 */
class Number implements Runnable {

    private int number = 1;
//    private Object obj = new Object();  // 定义一个obj充当同步监视器

    @Override
    public void run() {

        while (true) {

//            synchronized (obj) {  // 报错，同步监视器与notify()和wait()的调用者不一致
            synchronized (this) {

                this.notify();

                if (number <= 100) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
                        // 使得调用如下wait()方法的线程进入阻塞状态
                        // 调用wait()方法的线程会释放锁
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    break;
                }
            }
        }
    }
}

public class CommunicationTest {
    public static void main(String[] args) {

        Number number = new Number();

        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程1");
        t2.setName("线程2");

        t1.start();
        t2.start();
    }
}

