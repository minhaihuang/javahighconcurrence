package com.hhm.basic.designModel;

/**
 * create by huanghaimin
 * 不变模式。
 * 核心思想：一个对象一旦被创建，则它的内部状态永远不会被改变。
 * 不变模式天生对多线程就是友好的，不需要对不变对象做同步控制。
 *
 * 八字基本数据类型与String都使用了不变模式。使用了不变模式后，
 * 所有实例的方法均不需要进行同步操作，保证了它们在多线程环境下的性能。
 *
 * 不变模式的实习原理：
 * 1，去除所有的setter方法以及所有修改自身属性的方法。
 * 2，将所有数学设置为私有，并用final标记，确定其不可修改。
 * 3，确保没有字类可以重载修改它的行为。
 * 4，有一个可以创建完整对象的构建函数。
 */
public final class Test04NoChangeModel { // 3，确保没有字类可以重载修改它的行为。
    // 2，将所有数学设置为私有，并用final标记，确定其不可修改。
    private final String name;
    private final Integer age;

    // 4，有一个可以创建完整对象的构建函数
    public Test04NoChangeModel(String name, Integer age){
        super();
        this.name = name;
        this.age = age;
    }

    // 1，去除所有的setter方法以及所有修改自身属性的方法。
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
