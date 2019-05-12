package com.java.concurrency.singleton;

import com.java.concurrency.annotations.ThreadUnSafe;

@ThreadUnSafe
public class SingletonExample1 {
    //私有构造函数
    private SingletonExample1(){

    }
    //懒汉式，在第一次被调用时创建对象实例  饿汉式在类装载时创建对象实例，若初始化后未被真正调用会浪费资源
    //DCL的单例实现并不是线程安全的
    //1.为对象分配内存空间
    //2.instance指向内存空间的地址
    //3,初始化对象
    // 指令重排序 CPU与JVM  2,3步骤是没有关联的
    //因此实际 可能是 1->3->2
    //这里使用volatile作用是禁止指令重排序
    private  volatile static SingletonExample1 instance = null;
    public static SingletonExample1 getInstance(){
        if(instance == null){  //Thread-2 发现有对象了 直接返回
            synchronized (SingletonExample1.class){
                if(instance == null){
                    instance = new SingletonExample1(); //Thread-1 但是初始化工作还没做
                }
            }
        }
        return instance;
    }
}
