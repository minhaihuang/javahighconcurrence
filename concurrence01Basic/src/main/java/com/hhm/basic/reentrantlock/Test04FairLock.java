package com.hhm.basic.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * create by huanghaimin
 * 公平锁。
 * 当多个线程等待同一个锁时，不是先到先得而是系统随机从等待队列中随机挑选一个线程赋予锁。因此不能保证公平性。
 * 用synchronized关键字，则获取锁的方式是非公平的。
 * 使用可重入锁，可允许对获取锁的公平性进行现在。
 * 重入锁有一个构造函数：
 * public ReentrantLock(boolean fair)。fair为true时，该锁是公平的。
 *
 * 注意：公平锁虽然看起来很优美，但是这必须要求系统维护一个有序队列，因此公平锁的实现成本比较高，性能也相对低下，
 * 因此，默认情况下，锁是非公平的。如果没有特别的要求，也不需要使用公平锁
 *
 */
public class Test04FairLock implements Runnable{
    // 产生一个公平锁
    public static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println(Thread.currentThread().getName()+" get lock");
            } finally {
                if (fairLock.isHeldByCurrentThread()) {
                    fairLock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Test04FairLock test04FairLock = new Test04FairLock();
        Thread t1 = new Thread(test04FairLock,"t1");
        Thread t2 = new Thread(test04FairLock,"t2");
        Thread t3 = new Thread(test04FairLock,"t3");
        Thread t4 = new Thread(test04FairLock,"t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        /**
         t1 get lock
         t4 get lock
         t3 get lock
         t2 get lock
         t1 get lock
         t4 get lock
         t3 get lock
         t2 get lock
         t1 get lock
         t4 get lock
         t3 get lock
         t2 get lock
         */
    }
}
