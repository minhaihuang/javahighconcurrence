package com.hhm.basic.Thread;

/**
 * 正确的挂起与继续执行线程。使用wait和notify机制
 */
public class ThreadSuspendAndResumeGood {
    final static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        ChangeObjThread t1 = new ChangeObjThread();
        ReadObjThread t2 = new ReadObjThread();
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t1.suspendMe();
        System.out.println("suspend t1 2 sec");
        Thread.sleep(2000);
        System.out.println("resume t1");
        t1.resumeMe();
    }

    public static class ChangeObjThread extends Thread{
        // 是否挂起
        volatile boolean isSuspendMe = false;

        /**
         * 挂起线程
         */
        public void suspendMe(){
            isSuspendMe = true;
        }

        /**
         * 继续执行线程
         */
        public void resumeMe(){
            isSuspendMe = false;
            synchronized (this){
                notify();
            }
        }

        @Override
        public void run() {
            while (true){
                synchronized (this){
                    while (isSuspendMe){ //挂起状态
                        try {
                            wait(); //线程等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (obj){
                    System.out.println("in changeObjThread");
                }
                Thread.yield();
            }
        }
    }


    public static class ReadObjThread extends Thread{
        @Override
        public void run() {
            while (true) {
                synchronized (obj) {
                    System.out.println("in readObjThread");
                    Thread.yield();
                }
            }
        }
    }
}
