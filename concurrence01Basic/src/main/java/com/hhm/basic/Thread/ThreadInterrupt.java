package com.hhm.basic.Thread;

/**
 * 中断线程
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        /**错误的中断线程**/
//        ThreadInterruptWrong threadInterruptWrong = new ThreadInterruptWrong();
//        threadInterruptWrong.start();
//        Thread.sleep(1000);
//        threadInterruptWrong.interrupt(); //中断线程，但是不会被真正中断，因为没有中断处理逻辑
        /**正确的中断线程1，增加了中断处理逻辑，但是线程内没有wait和sleep方法**/
//        ThreadInterruptRight1 right1 = new ThreadInterruptRight1();
//        right1.start();
//        Thread.sleep(100);
//        right1.interrupt();

        /**正确的中断线程1，增加了中断处理逻辑，但是线程内没有wait和sleep方法**/
        ThreadInterruptRight2 right2 = new ThreadInterruptRight2();
        right2.start();
        Thread.sleep(100);
        right2.interrupt(); //设置中断位，通知线程可以中断了
    }

    /**
     * 正确的中断线程，增加中断处理逻辑
     */
    public static class ThreadInterruptRight2 extends Thread{
        @Override
        public void run() {
            while (true){
                if(Thread.currentThread().isInterrupted()){ //判断是否被中断了
                    System.out.println("i was been interrupted");
                    break;
                }else {
                    System.out.println("i am running");
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("thread interrupt when sleep");
                    // 设置中断状态。为了数据的完整性和一致性，不直接退出，再次中断自己，这样在33行的中断检查中，才能发现自己被中断了。
                    Thread.currentThread().interrupt();
                }
                Thread.yield();
            }
        }
    }

    /**
     * 正确的中断线程，增加中断处理逻辑
     */
    public static class ThreadInterruptRight1 extends Thread{
        @Override
        public void run() {
            while (true){
                if(Thread.currentThread().isInterrupted()){ //判断是否被中断了
                    System.out.println("i was been interrupted");
                    break;
                }else {
                    System.out.println("i am running");
                }
                Thread.yield();
            }
        }
    }

    /**
     * 错误的中断线程
     */
    public static class ThreadInterruptWrong extends Thread{
        @Override
        public void run() {
            while (true){
                System.out.println("i am running");
            }
        }
    }
}
