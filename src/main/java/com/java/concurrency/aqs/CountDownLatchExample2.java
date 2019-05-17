package com.java.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {
    public static int threadCount = 200;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < threadCount ; i++){
            final int num = i;
            service.execute(() ->{
                try {
                    CountDownLatchExample2.test(num);
                }catch (Exception e){
                    log.error("exception",e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            //被阻塞的线程只等待指定的实现，但是不会丢弃还未执行的线程  设置等待时间
            countDownLatch.await(1, TimeUnit.MILLISECONDS);
            log.info("is finish");
            service.shutdown();
        }catch (Exception e){
            log.error("exception",e);
        }
    }
    public static void test(int num)throws Exception{
        Thread.sleep(20);
        log.info("threadNum{}",num);
    }
}
