package com.hhm.basic.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by huanghaimin
 * Executor->ExecutorService->Executors->ThreadPoolExecutor
 * 线程池的简单示例
 */
public class Test01ThreadPoolDemo {
    public static class MyTask implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+": thread id:"+ Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        // 创建具有5个线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        /**
         * 返回一个可根据实际情况调整线程数量的线程池。若当前的线程数量不足，会创建新的线程去执行任务。
         * 执行任务完成后，新的线程重新回收到线程池，所以线程池内线程的数量会越来越多
         */
        //ExecutorService executorService = Executors.newCachedThreadPool();

        // 向线程池提交10个任务。
        for (int i = 0; i < 10; i++) {
            executorService.submit(task); // 线程池提交任务
        }

        // 线程执行任务完成后并不会真正关闭，而是还回线程池
    }
}
