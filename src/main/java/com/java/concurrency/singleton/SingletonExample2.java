package com.java.concurrency.singleton;

public class SingletonExample2 {
    /**
     * 饿汉式
     */
    private SingletonExample2(){

    }
    // 创建单例时要注意静态域的顺序
    private static SingletonExample2 instance = null;
    static {
        instance = new SingletonExample2();
    }
    public static SingletonExample2 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        SingletonExample2 instance = SingletonExample2.getInstance();
        SingletonExample2 instance1 = SingletonExample2.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());
    }
}
