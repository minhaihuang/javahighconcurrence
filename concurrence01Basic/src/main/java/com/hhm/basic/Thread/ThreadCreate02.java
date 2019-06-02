package com.hhm.basic.Thread;

/**
 * 线程创建的第二种方式，通过实现Runnable接口。由于考虑java是单继承的，所以建议使用此种方式。
 */
public class ThreadCreate02 implements Runnable{
    @Override
    public void run() {
        System.out.println("hello, i am runnable");
    }

    public static void main(String[] args) {
        ThreadCreate02 threadCreate02 = new ThreadCreate02();
        Thread t = new Thread(threadCreate02);
        t.start();
//        t.run(); 不要直接调用run方法，这样只是普通的调用，不是启动一个线程
    }
}
