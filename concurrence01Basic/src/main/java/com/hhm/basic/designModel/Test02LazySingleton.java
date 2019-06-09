package com.hhm.basic.designModel;

/**
 * create by huanghaimin
 * 懒加载机制的单例模式。可精准控制instance的创建时间。
 * 但是为了防止高并发，必须使用了synchronized进行方法同步，若是在高并发环境下，性能可能不好
 */
public class Test02LazySingleton {
    private static Test02LazySingleton instance = null;
    private Test02LazySingleton () {

    }

    public static synchronized Test02LazySingleton getInstance() {
        if (instance == null){
            instance = new Test02LazySingleton();
        }
        return instance;
    }
}
