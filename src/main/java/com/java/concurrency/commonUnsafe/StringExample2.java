package com.java.concurrency.commonUnsafe;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@Slf4j
@ThreadSafe
public class StringExample2 {
    private static int RequestCount = 5000;
    private static int concurrency = 200;
    private static StringBuffer stringBuffer = new StringBuffer();


    public static void main(String[] args)throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(concurrency);
        CountDownLatch countDownLatch = new CountDownLatch(RequestCount);
        for(int i = 0 ; i <RequestCount ;i++){
            executorService.execute(()->{
                try{
                    semaphore.acquire();
                    StringExample2.update();
                    semaphore.release();
                    countDownLatch.countDown();
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        log.info("size：{}",stringBuffer.length());
    }
    public static void update(){
        stringBuffer.append(1);
    }
}
