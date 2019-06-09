package com.hhm.basic.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * create by huanghaimin
 * 读写锁。
 * 读与读之间不加锁，读与写之间加锁
 */
public class Test06ReadWriteLock {
    private static Lock lock = new ReentrantLock();
    // 读写锁
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    // 读锁
    private static Lock readLock = reentrantReadWriteLock.readLock();
    // 写锁
    private static Lock writeLock = reentrantReadWriteLock.writeLock();
    // 共享变量
    private int value;

    /**
     * 读
     * @param lock
     * @return
     * @throws InterruptedException
     */
    public Object handleRead(Lock lock) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 写
     * @param lock
     * @param index
     * @throws InterruptedException
     */
    public void handleWrite(Lock lock, int index) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final Test06ReadWriteLock test06ReadWriteLock = new Test06ReadWriteLock();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    test06ReadWriteLock.handleRead(readLock); // 读锁
//                    test06ReadWriteLock.handleRead(lock); // 普通重入锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    test06ReadWriteLock.handleWrite(writeLock,new Random().nextInt()); // 写锁
//                    test06ReadWriteLock.handleWrite(lock,new Random().nextInt()); // 普通重入锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }
        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
    }
}
