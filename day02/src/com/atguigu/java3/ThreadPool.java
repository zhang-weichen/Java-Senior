package com.atguigu.java3;

import java.util.concurrent.*;

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
                sum += i;
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
                sum += i;
            }
        }
        return sum;
    }
}

public class ThreadPool {
    public static void main(String[] args) {

        // 1. 创建指定线程数量的线程池
//        ExecutorService service = Executors.newFixedThreadPool(10);
        // ThreadPoolExecutor是 ExecutorService接口的实现类，若要对线程池的属性进行配置，则需转换为实现类的对象
        ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        // 设置线程池属性
        System.out.println(service.getClass());  // class java.util.concurrent.ThreadPoolExecutor
        service.setCorePoolSize(15);
        service.setKeepAliveTime(100, SECONDS);

        // 2. 执行指定的线程操作。
        // 2.1 调用executor()，需提供 Runnable接口实现类的对象
//        service.execute(new NumberThread());
//        service.execute(new NumberThread1());

        // 2.2 调用 submit()，需提供 Callable接口实现类的对象，即 FutureTask对象
        FutureTask futureTask = new FutureTask(new NumberThread2());
        FutureTask futureTask1 = new FutureTask(new NumberThread3());
        service.submit(futureTask);
        service.submit(futureTask1);

        Object sum = null;
        Object sum1 = null;
        try {
            sum = futureTask.get();
            sum1 = futureTask1.get();
            System.out.println("sum = " + sum);
            System.out.println("sum1 = " + sum1);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // 3. 关闭连接池
        service.shutdown();
    }
}
