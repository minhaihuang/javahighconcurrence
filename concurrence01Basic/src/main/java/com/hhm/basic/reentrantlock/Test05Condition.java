package com.hhm.basic.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by huanghaimin
 * 重入锁的好搭档 Conditiont条件。
 * Condition与重入锁是相关联的，通过Lock接口的newCondition()方法可以生成一个与当前重入锁绑定的Condition实例。利用它，可以让线程在合适的时间等待，或者在某一个特定的时刻得到通知，继续执行。
 *
 * Condition接口提供的基本方法如下：
 *
 * void await() throws InterruptedException;
 * void awaitUninterruptibly();
 * long awaitNanos(long nanosTimeout) throws InterruptedException;
 * boolean await(long time, TimeUnit unit) throws InterruptedException;
 * boolean awaitUnitl(Date deadline) throws InterruptedException;
 * void signal();
 * void signalAll();
 * 以上方法含义如下：
 * await()方法会使当前线程等待，同时释放当前锁，当其他线程中使用signal()或者signalAll()方法时，线程会重新获得锁继续执行。或者当线程被中断时，也能跳出等待；
 * awaitUninterruptible()方法与await()方法基本相同，但是它并不会在等待过程中响应中断；
 * singal()方法用于唤醒一个在等待中的线程。相对的singalAll()方法会唤醒所有在等待中的线程；
 */
public class Test05Condition implements Runnable{
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    @Override
    public void run() {
        try {
            lock.lock();
            condition.await(); // t1等待。让出锁
            System.out.println("Thread is going on");
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test05Condition test05Condition = new Test05Condition();
        Thread t = new Thread(test05Condition);
        t.start();
        Thread.sleep(5000);

        // 通知t1继续执行
        lock.lock();
        condition.signal(); //随机唤醒等待在condition队列上面的线程
        lock.unlock(); //释放锁，不然t1无法执行
    }
}
