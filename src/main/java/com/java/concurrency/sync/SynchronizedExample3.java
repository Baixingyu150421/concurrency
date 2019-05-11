package com.java.concurrency.sync;

import com.java.concurrency.annotations.ThreadUnSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * add静态方法加同步锁 synchronized
 */
@Slf4j
@ThreadUnSafe
public class SynchronizedExample3 {

    private static int requestTotal = 5000;

    private static int concurrencyNum = 200;

    private static int count = 0;

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
        log.info("count:{}",count);
    }
    private  synchronized static void add(){
        count ++;
    }
}
