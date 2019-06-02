package com.hhm.basic.Thread;

/**
 * 等待其他线程结束。让其他线程先执行完
 */
public class ThreadJoin {
    public static volatile int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i = 0; i < 1000000; i++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();
        addThread.join(); //main线程等待addThread执行完毕
        //addThread.join(1); //让addThread先执行1毫秒
        System.out.println(i); //输出10000。
    }
}
