package com.hhm.basic.Thread;

/**
 * 线程间的同步。
 * synchronized的作用是实现线程间的同步，它的工作是对同步的代码加锁，使得每一次，只能有一个线程进入同步块。
 *
 * synchronized的用法
 * 1，指定加锁对象：对给定的对象加锁，进入同步的代码前要获得给定的对象的锁。
 * 2，直接作用于实例方法：相当于对当前实例加锁，进入同步代码前要获得当前实例的锁。
 * 3，直接作用于静态方法：相当于对当前类加锁，进入同步代码前要获得当前类的锁。
 */
public class ThreadSynchronized {
    public static class TestSynchronizedObj implements Runnable{
        static TestSynchronizedObj instance = new TestSynchronizedObj();
        static volatile int i = 0;
        public static void increase(){
            for(int j = 0; j < 10000; j++){
                synchronized (instance) {
                    i++;
                }
            }

        }
        @Override
        public void run() {
            increase();
        }

        public static void main(String[] args) throws InterruptedException {

            Thread t1 = new Thread(instance);
            Thread t2 = new Thread(instance);
            t1.start();t2.start();
            t1.join();t2.join();
            System.out.println(i);
        }
    }

    /**
     * 测试使用Volatile声明变量 仍然出错的例子
     * @author Administrator
     *
     */
    public static class TestSynchronizedInstanceMethod implements Runnable{
        static TestSynchronizedInstanceMethod instance = new TestSynchronizedInstanceMethod();
        static volatile int i = 0;
        public synchronized void  increase(){
            for(int j = 0; j < 10000; j++){
                i++;
            }

        }
        @Override
        public void run() {
            increase();
        }
    }
}
