package com.hhm.basic.threadPool.mythreadpool;

/**
 * create by huanghaimin
 */
public class Task implements Runnable{
    private int i;
    public Task(int i){
        this.i = i;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int k = 1/0;      
        System.out.println(Thread.currentThread().getName()+",task is" + i);
    }

    public int getI() {
        return i;
    }
}
