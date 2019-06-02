package com.hhm.basic.Thread;

/**
 * 创建线程的第一种方式，通过继承Thread
 */
public class CreateThread01 extends Thread{
    @Override
    public void run() {
        System.out.println("thread");
    }

    public static void main(String[] args) {
        // new CreateThread01().run();// 不要直接调用run方法，否则只是普通的调用方法，不会创建线程
        new CreateThread01().start();
    }
}
