package com.java.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample2 {
    /**
     * 作用于整个类
     */
    public static void test1(int j){
        synchronized (SynchronizedExample2.class){
            for (int i = 0 ; i < 10 ; i++ ){
                log.info("test1-j{}-value{}j",j,i);
            }
        }
    }
    /**
     * 作用于静态方法
     */
    public synchronized static void  test2(int i){
        for(int j = 0 ; j <10; j++){
            log.info("test2-i{}-value{}",j,i);
        }
    }
    public static void main(String[] args) {
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            test1(1);
        });
        executorService.execute(()->{
            test2(2);
        });
    }
}
