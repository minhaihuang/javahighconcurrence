package com.hhm.basic.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * create by huanghaimin
 * 中断响应。
 * 对应synchronized来说，线程要么获得锁继续执行代码，要么继续等待锁。
 * 重入锁则提供了另外一种可能，即可取消获得锁的请求。这样对避免死锁是有一定帮助的。
 *
 * 下面的例子会产生死锁，但是得益于锁中断，解决了这个问题
 */
public class Test02LockInterruptibly implements Runnable{
    // 声明两个可重入锁
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    // 用来表示是操作哪个锁
    private int lockNum;

    public Test02LockInterruptibly(Integer lockNum){
        this.lockNum = lockNum;
    }

    @Override
    public void run() {
        try {
            if(lockNum == 1){
                // 申请锁1
                lock1.lockInterruptibly(); // 该方法可以中断响应锁的动作。即在申请锁的过程中，可以中断响应。
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                // 申请锁2
                lock2.lockInterruptibly();
            } else {
                // 申请锁2
                lock2.lockInterruptibly(); // 该方法可以中断响应锁的动作。即在申请锁的过程中，可以中断响应。
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                // 申请锁1
                lock1.lockInterruptibly();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()){ //若锁1被当前线程占用，释放锁1
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()){ //若锁1被当前线程占用，释放锁1
                lock2.unlock();
            }

            System.out.println(Thread.currentThread().getName()+"，线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test02LockInterruptibly r1 = new Test02LockInterruptibly(1);
        Test02LockInterruptibly r2 = new Test02LockInterruptibly(2);
        Thread t1 = new Thread(r1,"t1");
        Thread t2 = new Thread(r2,"t2");
        // 由于t1先占住了lock1，再占住了lock2；t2先占住了lock2，再请求lock1。很容易形成t1和t2之间互相等待。造成死锁
        t1.start();t2.start();
        Thread.sleep(1000);
        t2.interrupt(); //  中断t2线程。两个线程都会退出，但是只有t1会完成工作
    }
}
