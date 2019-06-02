package com.hhm.basic.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by huanghaimin
 * 锁申请等待限时。
 * 除了等待外部通知，从而中断锁之外。
 * 还可以用限定等待锁的时间。当到了指定的时间仍未获取到锁，则该线程自动放弃获取锁
 *
 * 重入锁的tryLock(long timeout, TimeUnit unit)可指定获取锁的时间。
 * tryLock()也可以不带参数。这样的话，当前线程会尝试获取锁，若锁没有被占用，则返回true。若
 * 已被占用，则不会等待，立即返回false。这种模式不会引起线程等待，因此也不会产生死锁。
 */
public class Test03TryLock implements Runnable{
    // 声明重入锁
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) { //限定等待锁的时间。这里是等待5秒
           // if (lock.tryLock()){ //不指定等待锁的时间，立即返回结果
                System.out.println(Thread.currentThread().getName()+" get lock success");
                Thread.sleep(6000);
            }else {
                System.out.println(Thread.currentThread().getName()+" get lock failed");
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            if(lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Test03TryLock test03TryLock = new Test03TryLock();
        Thread t1 = new Thread(test03TryLock,"t1");
        Thread t2 = new Thread(test03TryLock,"t2");
        t1.start();
        t2.start();
    }
}
