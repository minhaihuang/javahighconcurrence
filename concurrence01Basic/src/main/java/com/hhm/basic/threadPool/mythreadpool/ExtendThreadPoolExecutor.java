package com.hhm.basic.threadPool.mythreadpool;

import java.util.concurrent.*;

/**
 * 扩展线程工厂
 * create by huanghaimin
 */
public class ExtendThreadPoolExecutor extends ThreadPoolExecutor {
    // 线程执行开始时间
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    /**
     * 线程调用前，若抛出了异常，则后续的任务将不会再执行，且afterExecute也不会再执行
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        startTime.set(System.currentTimeMillis());
        System.out.println(t.getName()+"准备执行"+"任务为："+r.toString());
        super.beforeExecute(t, r);
    }

    /**
     * 线程调用后。无论线程是正常执行完，还是在执行过程中抛出了异常，该方法都会被执行
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        long endTime = System.currentTimeMillis();
        long taskTime = endTime - startTime.get();
        System.out.println(Thread.currentThread().getName()+","+"任务"+r.toString()+"执行完毕,"+"耗时："+taskTime);
        if(t != null){
            System.out.println("发生异常");
            System.out.println(t.getMessage());
        }
        super.afterExecute(r, t);
    }

    /**
     * 线程池关闭后。可以用来发送通知，记录日志或者收集finalize统计信息等
     */
    @Override
    protected void terminated() {
        System.out.println("线程池关闭");
        super.terminated();
    }

    public ExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }
}
