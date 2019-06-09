package com.hhm.basic.designModel;

/**
 * create by huanghaimin
 * 最简单的单例模式。
 * 这个单例模式的性能是非常好的，因为没有锁，也不会有并发问题。
 * 但是我们无法单例对象什么时候被创建调用。
 * 如果这个小小的问题，这个单例模式就已经够用了。容易实现，代码易读，且性能优越。
 */
public class Test01EasySingleton {
    private static Test01EasySingleton instance = new Test01EasySingleton();
    private Test01EasySingleton(){

    }

    private static Test01EasySingleton getInstance() {
        return instance;
    }
}
