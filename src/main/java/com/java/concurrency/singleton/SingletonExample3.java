package com.java.concurrency.singleton;

import com.java.concurrency.annotations.Recommend;
import com.java.concurrency.annotations.ThreadSafe;

@ThreadSafe
@Recommend
public class SingletonExample3 {
    //私有构造函数
    private SingletonExample3(){

    }
    private static SingletonExample3 getInstance(){
        return SingleTon.INSTANCE.getInstance();
    }

    /**
     * 通过枚举的方式构造单例模式 最安全的
     */
    private enum SingleTon{
        INSTANCE;
        private SingletonExample3 instance;
        //JVM会保证这个构造方法只会被调用一次，且是在这个类创建之前完成初始化的
        SingleTon(){
            instance = new SingletonExample3();
        }
        public SingletonExample3 getInstance(){
            return instance;
        }
    }

    public static void main(String[] args) {
        SingletonExample3 instance1 = SingletonExample3.getInstance();
        SingletonExample3 instance2 = SingletonExample3.getInstance();
        System.out.println(instance1.hashCode() + ":" + instance2.hashCode());
    }
}
