package com.java.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class CountDownLatchExample1 {

    public static int threadCount = 200;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < threadCount ; i++){
            final int num = i;
            service.execute(() ->{
                try {
                    test(num);
                }catch (Exception e){
                    log.error("exception",e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
            log.info("finish");
            service.shutdown();
        }catch (Exception e){
            log.error("exception",e);
        }
    }
    public static void test(int num)throws Exception{
        Thread.sleep(100);
        log.info("threadNum{}",num);
        Thread.sleep(100);
    }
}
