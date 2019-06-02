package com.hhm.basic.Thread;

/**
 * 线程等待和唤醒。
 * wait和notify都是定义在object中
 * wait：当前线程停止工作，等待其他线程唤醒
 * notify：随机唤醒一个等待的线程。
 * notifyALl：唤醒所有等待的线程。
 * 注意：wait不是可以随意调用的，必须包含在对应的synchronized语句中。无论是wait还是notify都应先获得对应的监视器。
 * wait方法会释放锁。sleep不会释放锁
 */
public class ThreadWaitAndNotify {
    // 监视器（锁）
    final static Object obj = new Object();

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.start();
        t2.start();
    }

    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (obj){
                System.out.println(System.currentTimeMillis()+",T1 start");
                try {
                    System.out.println("T1 wait for object");
                    obj.wait(); // 当前线程等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+",T1 end");
            }
        }
    }

    public static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (obj){
                System.out.println("T2 start,notify one Thread");
                obj.notify(); // 当前线程等待
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T2 end");
            }
        }
    }
}
