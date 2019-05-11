package com.java.concurrency.example.count.atomic;

import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 保证一段代码只执行一次
 */
@ThreadSafe
@Slf4j
public class AtomicExample6 {
    private static AtomicBoolean aBoolean = new AtomicBoolean(false);

    private static int requestTotal = 5000;

    private static int concurrencyNum = 200;

    private static int count = 0;

    public static void main(String[] args) throws Exception{
        final CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        final Semaphore semaphore = new Semaphore(concurrencyNum);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < requestTotal ; i++ ){
            service.execute(() ->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                    //每次计数减1
                    countDownLatch.countDown();
                } catch (Exception e){
                    log.warn("exception:",e);
                }
            });
        }
        countDownLatch.await();
        service.shutdown();
        log.info("count:{}",count);
    }

    public static void test(){
        if(aBoolean.compareAndSet(false,true)){
            log.info("update boolean");
            count++;
        }
    }
}
