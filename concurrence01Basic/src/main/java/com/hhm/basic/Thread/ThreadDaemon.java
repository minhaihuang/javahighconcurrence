package com.hhm.basic.Thread;

/**
 * 守护线程。
 * 例如垃圾回收线程，JIT线程都可以认为是守护线程。而用户线程可以认为是系统的工作线程。
 * 当用户线程完成之后，守护线程随之结束。当整个应用内只有守护线程时，整个虚拟机自然退出。
 */
public class ThreadDaemon {
    public static class DaemonThread extends Thread{
        @Override
        public void run() {
            while (true) {
                System.out.println("daemon Thread is active");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonThread();
        t.setDaemon(true); // 设置为守护线程。必须在start之前设置
        t.start();

        Thread.sleep(2000); // 主线程休眠两秒后整个程序结束
    }
}
