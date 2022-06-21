package com.atguigu.java;

/**
 * 例子：创建三个窗口卖票，总票数为100张。使用实现Runnable接口的方式
 *
 * 1. 问题：卖票过程中，出现了重票、错票 -->
 * 2. 问题出现的原因：某个线程执行取票操作时，在尚未完成操作时其它线程参与进来，也进行取票操作
 * 3. 如何解决：当一个线程操作共享数据时，其它线程不能参与进来。直到该线程操作完成，其它线程才可以开始操作共享数据。
 *             即使该线程出现了阻塞，也不能改变这种情况。
 * 4. 在Java中，通过同步机制解决线程安全问题。
 *      方式一：同步代码块
 *       synchronized(同步监视器) {
 *          // 需要被同步的代码
 *       }
 *       说明：
 *            1. 操作共享数据的代码，即为需要被同步的代码。 --> 精确同步代码块的范围，不能多也不能少
 *            2. 共享数据：多个线程共同操作的变量。如本问题中的tickets
 *            3. 同步监视器，俗称：锁。任何一个类的对象，都可以充当锁。
 *                  要求：多个线程必须公用同一把锁。
 *
 *            补充：在实现Runnable接口创建多线程的方式中，可以考虑使用this充当同步监视器
 *
 *      方式二：同步方法
 *          如果操作共享数据的代码完整地声明在一个方法中，不妨将此方法声明为同步的。
 *
 * 5. 同步的方式，解决了线程的安全问题。 --好处
 *    某一时间只能由一个线程操作同步代码，其它线程等待，相当于是一个单线程的过程，效率低。 --局限
 *
 * @author zhangweichen
 * @create 2022-04-24 23:04
 */
class Window1 implements Runnable {

    // 不需再设定为static类型，因为只创建一个Window1对象，所有线程操作的均为此对象中的值
    private int tickets = 100;

    // 作为锁使用的对象，必须具有唯一性
    Object obj = new Object();

    @Override
    public void run() {

        // 把while判断也放入同步代码块中是错误的，丢失了多线程的特性
        while (true) {

//            synchronized (obj) {
            // 使用实现Runnable接口方式创建多线程可以使用当前对象this作为同步监视器，因为此时的this具有唯一性：唯一的window1对象w
            synchronized (this) {
                if (tickets > 0) {

                    // 加入sleep()后，出现大量重票、错票问题，这就属于线程安全问题
                    // 因为多个线程同时执行run()的，对tickets的值进行修改的概率大大增加
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + ": 售票，票号：" + tickets);
                    tickets--;
                } else {
                    System.out.println("票已售空");
                    break;
                }
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
