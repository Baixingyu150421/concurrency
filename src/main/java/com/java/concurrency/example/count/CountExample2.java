package com.java.concurrency.example.count;

import com.java.concurrency.annotations.ThreadSafe;
import com.java.concurrency.annotations.ThreadUnSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用Atomic类中的AtomicInteger 保证原子性
 */
@Slf4j
@ThreadSafe
public class CountExample2 {

    private static int requestTotal = 5000;

    private static int concurrencyNum = 200;

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        final Semaphore semaphore = new Semaphore(concurrencyNum);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < requestTotal ; i++ ){
            service.execute(() ->{
                try {
                    semaphore.acquire();
                    add();
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
        log.info("count:{}",count.get());
    }
    private static void add(){
        count.getAndIncrement();
    }
}
