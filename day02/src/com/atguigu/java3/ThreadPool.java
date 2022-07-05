package com.atguigu.java3;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 创建线程的方式四：使用线程池
 *
 * 优势：
 * 1. 提高响应速度（减少了创建新线程的时间）
 * 2. 降低资源消耗（重复利用线程池中线程，不需要每次都创建新线程）
 * 3. 便于线程管理
 *      corePoolSize：核心池的大小
 *      maximumPoolSize：最大线程数
 *      keepAliveTime：线程没有任务时最多保持多长时间后会终止
 *
 *  面试题：创建多线程有几种方式？ 四种！
 *
 * @author zhangweichen
 * @create 2022-07-05 16:27
 */

class NumberThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class NumberThread1 implements Runnable {

    // 2. 实现run()，将此线程需要执行操作声明在run()中。
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
class NumberThread2 implements Callable {

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
        return sum;
    }
}

class NumberThread3 implements Callable {

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
        return sum;
    }
}

public class ThreadPool {
    public static void main(String[] args) {

        // 1. 创建指定线程数量的线程池
//        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        // 设置线程池属性
        System.out.println(service.getClass());  // class java.util.concurrent.ThreadPoolExecutor
        service.setCorePoolSize(15);
        service.setKeepAliveTime(100, SECONDS);

        // 2. 执行指定的线程操作。
        // 2.1 调用executor()，需提供Runnable接口实现类的对象
        service.execute(new NumberThread());
        service.execute(new NumberThread1());

        // 2.2 调用submit()，需提供Callable接口实现类的对象
//        System.out.println((Integer)service.submit(new NumberThread2()));
        System.out.println(service.submit(new NumberThread3()));

        // 3. 关闭连接池
        service.shutdown();
    }
}
