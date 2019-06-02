package com.hhm.basic.Thread;

/**
 * 线程挂起和继续执行
 * suspend（已不推荐使用）：线程挂起，不会释放资源
 * resume(已不推荐使用)：线程继续执行。
 * 若resume在suspend之前执行，则将会导致线程永远挂起，资源永远无法被释放，是同jstack命令看时，线程的状态还是runnable。
 */
public class ThreadSuspendAndResumeBad {
    final static Object obj = new Object();

    public static void main(String[] args) throws Exception{
        ChangeObjThread t1 = new ChangeObjThread("t1");
        ChangeObjThread t2 = new ChangeObjThread("t2");
        t1.start();
        Thread.sleep(1000);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();

        /**
         可能会得到此结果，但是程序不会退出。证明线程还在挂起。
         in t1
         in t2

         */
    }
    /**
     * 错误的线程挂起
     */
    public static class ChangeObjThread extends Thread{
        public ChangeObjThread(String name){
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (obj){
                System.out.println("in "+getName());
                Thread.currentThread().suspend(); //挂起当前线程
            }
        }
    }

}
