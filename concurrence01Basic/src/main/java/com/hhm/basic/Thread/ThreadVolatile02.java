package com.hhm.basic.Thread;

/**
 * volatile还能保证数据的可见性和有序性
 */
public class ThreadVolatile02 {
    // public static boolean ready; //普通的声明变量。在main线程修改变量值。线程ReadThread不一定能看到这个修改
    // private static int number; //普通的声明变量。在main线程修改变量值。线程ReadThread不一定能看到这个修改

    public static volatile boolean ready; //声明为弱变量。在main线程修改变量值。线程ReadThread可以看到这个修改
    private static volatile int number; //声明为弱变量。在main线程修改变量值。线程ReadThread可以看到这个修改

    public static class ReadThread extends Thread{
        @Override
        public void run() {
            while (!ready){}
            System.out.println(number); // 准备好了才会打印数据
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadThread().start();
        Thread.sleep(1000);
        number = 1; //修改变量
        ready = true;
    }
}
