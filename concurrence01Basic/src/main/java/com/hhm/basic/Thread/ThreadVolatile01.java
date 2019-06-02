package com.hhm.basic.Thread;

/**
 * Volatile：易变的，不稳定的。当用volatile去声明一个变量时，虚拟机会对该变量做一些谨慎操作，
 * 以确保当该便利的值发生改变时，其他线程都可以见到。
 * 注意：Volatile不能代替锁。
 */
public class ThreadVolatile01 {
    static volatile int i = 0;
    /**
     * volatile不能代替锁的例子
     */
    public static class VolatileCanNotReplaceSyncExample extends Thread{
        @Override
        public void run() {
            for (int k = 0; k <10000; k++){
                i++;
            }
        }
    }

    /**
     * 测试volatile不能代替锁的例子
     * @throws InterruptedException
     */
    public static void testVolatileCanNotReplaceSyncExample() throws InterruptedException{
        Thread[] threads = new Thread[10];
        for(int i = 0; i < threads.length; i++){
            threads[i] = new VolatileCanNotReplaceSyncExample();
            threads[i].start();
        }
        for(int i = 0 ; i < threads.length; i++){
            threads[i].join(); // 等待该线程执行完
        }
        System.out.println(i); //输出的结果总是小于10*10000
    }

    public static void main(String[] args) throws InterruptedException {
        testVolatileCanNotReplaceSyncExample();
    }

}
