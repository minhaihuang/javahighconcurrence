package com.hhm.basic.threadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by huanghaimin
 */
public class Test01ThreadPoolExecutor {
    public static void main(String[] args) {
        // 核心线程数
        int corePoolSize = 5;
        // 最大线程数
        int maximunmPoolSize = 10;
        // 线程空闲时间
        int keepAliveTime = 1000;
        // 线程空闲时间单位
        TimeUnit milliseconds = TimeUnit.MILLISECONDS;
        // 任务队列
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);//FIFO 有界队列
        // BlockingQueue<Runnable> workQueue = new SynchronousQueue<>(); // 只有当线程池无界或者可以拒绝任务时，该队列才有效
        // BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(10);//FIFO 若不设置边界值，最大的任务数为int的最大值，极易耗尽内存。
        // BlockingQueue<Runnable> workQueue = new PriorityBlockingQueue<>();// 根据线程优先级控制任务的执行顺序。需要任务实现Compar接口
        // 线程工厂
        // ThreadFactory factory = Executors.defaultThreadFactory();
        ThreadFactory factory = new MyThreadFactory(); // 自定义线程工厂
        // 拒绝策略
        // RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();// 中止策略。当队列满时拒绝执行任务，直接抛出异常
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();// 调用者运行策略，不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者。从而减低新任务的流量。服务器在高负载下可平缓性能降级。
        // RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();// 抛出策略，当丢列满时，悄悄抛弃最新任务
        // RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();// 抛出策略，当丢列满时，悄悄抛弃下一个即将执行的任务，执行新任务
        // RejectedExecutionHandler handler = new MyRejectExecutionHandler(); // 自定义拒绝策略

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
        corePoolSize,
        maximunmPoolSize,
        keepAliveTime,
        milliseconds,
        workQueue,
        factory,
        handler);

        Task task = new Task();
        for (int i = 0; i < 20; i++) {
            executor.submit(task);
            System.out.println(executor.getActiveCount());
        }
    }

    private static class Task implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getThreadGroup().getName());
        }
    }

     /**
     * 定义自己的线程工厂
     */
     private static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

         MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = new ThreadGroup("hhm-thread-group");
            namePrefix = "hhm-"+"pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    /**
     * 自定义拒绝策略
     */
    private static class MyRejectExecutionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("队列满了，线程池也满了，抛弃任务"+r.toString()+",线程池为："+ executor.toString());
        }
    }
}
