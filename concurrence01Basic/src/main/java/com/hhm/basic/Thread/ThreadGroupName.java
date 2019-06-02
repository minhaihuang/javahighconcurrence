package com.hhm.basic.Thread;

/**
 * 线程组。
 * 若线程数量很多，且功能相似时，最好还是用线程组来管理
 */
public class ThreadGroupName implements Runnable{
    public static void main(String[] args) {
        // 创建线程组，名字要起的有意义
        ThreadGroup threadGroup = new ThreadGroup("printNameGroup");
        // 往线程组添加线程
        Thread t1 = new Thread(threadGroup,new ThreadGroupName(),"T1");
        Thread t2 = new Thread(threadGroup,new ThreadGroupName(),"T2");

        t1.start();
        t2.start();

        System.out.println(threadGroup.activeCount()); // 当前出当前工作的线程的数量，不一定准确
        threadGroup.list(); // 打印出线程组所有线程的信息

        // threadGroup.stop();// 停止线程组内的所有线程。不要用这个方法，会产生数据不一致的问题

    }
    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName()
                + "-" + Thread.currentThread().getName();
        while (true){
            System.out.println("I am " + groupAndName);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
