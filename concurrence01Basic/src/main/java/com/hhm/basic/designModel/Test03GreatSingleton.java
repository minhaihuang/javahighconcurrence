package com.hhm.basic.designModel;

/**
 * create by huanghaimin
 * 一个优秀的单例模式，既能控制创建的时间，也不会有并发问题，性能也可以得到保障
 */
public class Test03GreatSingleton {
    private Test03GreatSingleton () {

    }

    private static class SingletonHolder{
        private static Test03GreatSingleton instance = new Test03GreatSingleton();
    }

    public static Test03GreatSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
