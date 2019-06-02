package com.hhm.basic.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * create by huanghaimin
 *
 * 重入锁。
 * 重入锁可以起到跟synchronized同样的作用。
 * 与synchronized相比，重入锁更加灵活，加锁与释放锁的过程都可以由开发人员控制。
 * 为什么叫重入锁：那是因为这种锁是可以反复进入的，反复获取的。
 * 记住，加锁多少次，就要释放锁多少次。释放次数多于加锁次数，会抛java.lang.IllegalMonitorStateException。
 * 释放次数过少，其他线程会无法获得锁
 *
 * 以下是重入锁一个最基本的例子
 */
public class Test01ReenterLockBasic implements Runnable{
    // 声明重入锁
    private static ReentrantLock lock = new ReentrantLock();
    // 静态资源
    private static int i = 0;
    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            // 加锁。保护临界区资源
            lock.lock();
            //lock.lock(); // 可重复获取同一把锁
            try {
                i++;
            } finally {
                lock.unlock(); // 释放锁。否则其他线程无法获取资源
                lock.unlock(); // 加锁多少次，就要释放多少次;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Test01ReenterLockBasic());
        Thread t2 = new Thread(new Test01ReenterLockBasic());
        t1.start();
        t2.start();
        t1.join(); // main等待t1执行完
        t2.join(); // main等待t2执行完
        System.out.println(i); // 20000
    }
}

