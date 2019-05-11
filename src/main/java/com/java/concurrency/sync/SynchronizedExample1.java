package com.java.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {
    //修饰代码块
    public void  test1(){
        synchronized (this){
            for(int i = 0 ; i < 10 ; i++){
                log.info("test1-value：{}",i);
            }
        }
    }
    //修饰方法 作用于调用对象
    public synchronized void test2(){
        for(int i = 0 ; i < 10 ;i++){
            log.info("test2-value{}",i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example12 = new SynchronizedExample1();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() ->{
            example12.test1();
        });
        service.execute(()->{
            example1.test1();
        });
    }
}
