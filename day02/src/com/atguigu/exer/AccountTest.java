package com.atguigu.exer;

/**
 * 银行有一个账户。
 *  有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
 *
 *  分析：
 *  1. 是否是多线程问题？ 是，两个储户线程
 *  2. 是否有共享数据？ 有，账户（或账户余额）
 *  3. 是否有线程安全问题？ 有
 *  4. 需要考虑如何解决线程安全问题？ 同步机制：有三种方式。
 *
 * @author zhangweichen
 * @create 2022-06-05 22:50
 */

class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amt) {

        if (amt > 0) {
            balance += amt;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "存钱成功，当前余额：" + balance);
        }
    }
}

class Customer extends Thread {

    private Account acct;

    public Customer(Account acct) {
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }
    }
}

public class AccountTest {
    public static void main(String[] args) {

        Account acct = new Account();

        Customer cust1 = new Customer(acct);
        Customer cust2 = new Customer(acct);

        cust1.setName("储户1");
        cust2.setName("储户2");

        cust1.start();
        cust2.start();
    }
}

