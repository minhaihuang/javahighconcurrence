package com.hhm.basic.Thread;

/**
 * 线程谦让。当前线程让出cpu。供其他线程争夺，同时当前线程也会继续争夺资源。
 * 若一个线程不那么重要，或者优先级很低，且害怕它占用太多cpu，可以在适当的时候使用Thread.yield()
 */
public class ThreadYield {
    public static volatile int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i = 0; i < 100; i++){
                System.out.println("i="+i);
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread addThread = new AddThread();
        addThread.start();

        for(int j = 0; j < 100; j++){
            System.out.println("j="+j);
        }
    }
}
