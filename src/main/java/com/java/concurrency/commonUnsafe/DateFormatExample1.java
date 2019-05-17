package com.java.concurrency.commonUnsafe;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@Slf4j
public class DateFormatExample1 {

    private static int RequestCount = 5000;
    private static int concurrencyCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(RequestCount);
        Semaphore semaphore = new Semaphore(concurrencyCount);
        for(int i = 0 ; i < RequestCount ; i++ ){
            executorService.execute(()->{
                try{
                    semaphore.acquire();
                    DateFormatExample1.update();
                    semaphore.release();
                    countDownLatch.countDown();
                }catch (Exception e){
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }
    public static void update(){
        //堆栈封闭保证线程安全
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = "20180202";
        try{
            simpleDateFormat.parse(date);
        }catch (Exception e){
            log.warn("exception",e);
        }
    }
}
