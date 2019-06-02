package com.hhm.basic.Thread;

/**
 * 线程优先级。
 * 在Thread中，定义了一些线程优先级:
 * public final static int MIN_PRIORITY = 1;
 *  public final static int NORM_PRIORITY = 5;
 *  public final static int MAX_PRIORITY = 10;
 *  其中数字越大，优先级越高。
 *  一个线程的优先级越高，越容易抢夺到资源。当然这只是一个概率的问题。运气不好，也有可能抢不到资源
 *
 */
public class ThreadPriority {
    public static void main(String[] args) {
        Thread high = new HighPriorityThread();
        Thread low = new LowPriorityThread();
        high.setPriority(Thread.MAX_PRIORITY); // 最高优先级
        low.setPriority(Thread.MIN_PRIORITY); // 最低优
        // 先级
        high.start();
        low.start();

        // 一般都是高优先级的先完成任务
    }

    /**
     * 高优先级线程
     */
    public static class HighPriorityThread extends Thread{
        static int i = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (ThreadPriority.class){
                    i ++ ;
                    if( i > 100000){
                        System.out.println("high priority thread finish task");
                        break;
                    }
                }
            }
        }
    }

    /**
     * 低优先级线程
     */
    public static class LowPriorityThread extends Thread{
        static int i = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (ThreadPriority.class){
                    i ++ ;
                    if( i > 100000){
                        System.out.println("low priority thread finish task");
                        break;
                    }
                }
            }
        }
    }

}
