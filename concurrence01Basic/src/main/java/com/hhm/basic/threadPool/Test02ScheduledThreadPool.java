package com.hhm.basic.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * create by huanghaimin
 * 任务调度线程池。
 * 若任务出现了问题，则后续的任务的调度都会被中断。因此要处理好异常。
 */
public class Test02ScheduledThreadPool {
    public static void main(String[] args) {
        // 创建任务调度线程池
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        // 2秒执行一次任务。若任务的执行时间超过两秒，则以任务的时间为周期
        ses.scheduleAtFixedRate(new Runnable() { // 执行周期。initialDelay+？*period。initialDelay ：任务开始时间/延时时间
//        ses.scheduleWithFixedDelay(new Runnable() { // 执行周期。initialDelay+？*(任务执行时间+period)
            @Override
            public void run() {
                try {
                    Thread.sleep(2000); // 若任务的执行时间超过调度时间，则以任务的时间为周期
                    System.out.println(System.currentTimeMillis()/1000);
//                    int i = 1/0; // 不处理好异常，则后续任务都会被中断
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },0,2, TimeUnit.SECONDS);
    }
}
