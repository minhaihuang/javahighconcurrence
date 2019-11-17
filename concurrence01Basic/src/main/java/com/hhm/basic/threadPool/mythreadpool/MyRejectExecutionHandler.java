package com.hhm.basic.threadPool.mythreadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * create by huanghaimin
 */
public class MyRejectExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("队列满了，线程池也满了，抛弃任务"+r.toString()+",线程池为："+ executor.toString());
    }
}
