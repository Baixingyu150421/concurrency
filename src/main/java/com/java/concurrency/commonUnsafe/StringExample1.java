package com.java.concurrency.commonUnsafe;

import com.java.concurrency.annotations.ThreadUnSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@Slf4j
@ThreadUnSafe
public class StringExample1 {
    private static int RequestCount = 5000;
    private static int concurrencyCount = 200;
    private static StringBuilder stringBuilder = new StringBuilder();
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(RequestCount);
        Semaphore semaphore = new Semaphore(concurrencyCount);
        for(int i = 0 ; i < RequestCount ; i++ ){
            executorService.execute(()->{
                try{
                    semaphore.acquire();
                    StringExample1.update();
                    semaphore.release();
                    countDownLatch.countDown();
                }catch (Exception e){
                }
            });
        }
        countDownLatch.await();
        log.info("size:{}",stringBuilder.length());
    }
    public static void update(){
        stringBuilder.append(1);
    }
}
