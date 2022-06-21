package com.atguigu.java;

/**
 * 创建多线程的方式二：实现Runnable接口
 * 1. 创建一个实现了Runnable接口的类
 * 2. 实现类去实现Runnable中的抽象方法：run()
 * 3. 创建实现类的对象
 * 4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
 * 5. 通过Thread类的对象调用start()
 *
 *
 *  比较创建线程的两种方式。
 *  开发中优先选择实现Runnable接口的方式
 *  原因：1. 实现的方式没有类的单继承性的局限性
 *       2. 实现的方式更适合来处理多个线程有共享数据的情况。
 *
 * 联系：public class Thread implements Runnable
 *      @Override
 *      public void run() {
 *         if (target != null) {
 *             target.run();
 *      }
 * 相同点：两种方式都需要重写run()，将线程要执行的逻辑声明在run()中。
 *
 * @author zhangweichen
 * @create 2022-04-24 23:22
 */
// 1. 创建一个实现了Runnable接口的类
class MyThread1 implements Runnable {

    // 2. 实现类实现Runnable接口中的抽象方法：run()
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

public class ThreadTest1 {
    public static void main(String[] args) {

        // 3. 创建实现类的对象
        MyThread1 m1 = new MyThread1();

        // 4. 将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
        // Thread类具有这样一个属性：private Runnable target;
        // 和这样一个构造器： public Thread(Runnable target) { init(null, target, "Thread-" + nextThreadNum(), 0);}
        // 当使用一个实现了Runnable接口的对象作为构造器参数时，target将指向该对象，start()在target != null的情况下执行target的run()方法
        Thread t1 =  new Thread(m1);
        t1.setName("线程1");

        // 5. 通过Thread类的对象调用start()：① 启动线程 ② 调用当前线程的run() --> 调用了Runnable类型的target的run()
        t1.start();

        // 创建新的线程无需再创建实现Runnable接口的对象，创建Thread类的对象即可
        Thread t2 = new Thread(m1);
        t2.start();
    }
}
