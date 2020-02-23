package com.hhm.basic.Thread;

/**
 * 死锁
 * create by huanghaimin
 */
public class ThreadDeadLockDemo {

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        new Thread(new MyThread(lock1,lock2),"myThread1").start();
        new Thread(new MyThread(lock2,lock1),"myThread1").start();
    }
}

class MyThread implements Runnable{
    private Object lock1;
    private Object lock2;
    public MyThread(Object lock1, Object lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        // 锁住了锁1
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " 获得 lock1");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 锁住了锁2
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " 获得 lock2");
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
