package com.hhm.basic.threadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by huanghaimin
 */
public class Test02NewCacheThreadPool implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        Test02NewCacheThreadPool task = new Test02NewCacheThreadPool();
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i  = 0; i < 5; i++){
            service.submit(task);
        }

    }
}
