package com.hhm.basic.threadPool.mythreadpool;

import com.hhm.basic.threadPool.Test01ThreadPoolExecutor;

import java.util.concurrent.*;

/**
 * create by huanghaimin
 */
public class Test {
    public static void main(String[] args) {
        // 核心线程数
        int corePoolSize = 5;
        // 最大线程数
        int maximunmPoolSize = 10;
        // 线程空闲时间单位
        TimeUnit milliseconds = TimeUnit.MILLISECONDS;
        // 线程空闲时间
        int keepAliveTime = 1000;
        // 任务队列
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);//FIFO 有界队列
       // 线程工厂
        ThreadFactory factory = new MyThreadFactory(); // 自定义线程工厂
        // 拒绝策略

        RejectedExecutionHandler handler = new MyRejectExecutionHandler(); // 自定义拒绝策略

        ThreadPoolExecutor executor = new ExtendThreadPoolExecutor(
                corePoolSize,
                maximunmPoolSize,
                keepAliveTime,
                milliseconds,
                workQueue,
                factory,
                handler);


        for (int i = 0; i < 20; i++) {
            Task task = new Task(i);
            executor.submit(task);
        }
        executor.shutdown();
    }
}
