package com.java.concurrency.commonUnsafe;

import com.java.concurrency.annotations.ThreadUnSafe;
import lombok.extern.slf4j.Slf4j;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@Slf4j
@ThreadUnSafe
public class HashSetExample {
    private static int requestTotal = 5000;

    private static int concurrencyNum = 200;

    private static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        final Semaphore semaphore = new Semaphore(concurrencyNum);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < requestTotal ; i++ ){
            final int num = i;
            service.execute(() ->{
                try {
                    semaphore.acquire();
                    HashSetExample.add(num);
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
