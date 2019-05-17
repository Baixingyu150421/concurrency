package com.java.concurrency.concurrent;

import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class ConcurrentSkipListSetExample {
    private static int requestTotal = 5000;

    private static int concurrencyNum = 200;

    private static ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();

    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        final Semaphore semaphore = new Semaphore(concurrencyNum);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < requestTotal ; i++ ){
            final int num = i;
            service.execute(() ->{
                try {
                    semaphore.acquire();
                    ConcurrentSkipListSetExample.add(num);
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
        log.info("size:{}",set.size());
    }
    private static void add(int i){
        set.add(i);
    }
}
