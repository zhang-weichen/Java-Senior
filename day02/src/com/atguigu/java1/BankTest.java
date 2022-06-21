package com.atguigu.java1;

/**
 * 使用同步机制将单例模式中的懒汉式改写为线程安全的
 *
 * @author zhangweichen
 * @create 2022-05-15 1:58
 */
public class BankTest {
}

class Bank {

    private Bank() {}

    private static Bank instance = null;

    public static Bank getInstance() {
        // 方式一：效率稍差
//        synchronized (Bank.class) {
//            if (instance == null) {
//                instance = new Bank();
//            }
//            return instance;
//        }

        // 方式二：双重检查锁，在instance实例化后，其它进程直接跳过同步代码块，提高效率
        if (instance == null) {
            synchronized (Bank.class) {
                // 第二次检查不能省略，因为可能有多个线程通过第一次if判断
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}
