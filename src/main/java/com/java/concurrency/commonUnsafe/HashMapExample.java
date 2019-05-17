package com.java.concurrency.commonUnsafe;

import com.java.concurrency.annotations.ThreadUnSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@Slf4j
@ThreadUnSafe
public class HashMapExample {
    private static int requestTotal = 5000;

    private static int concurrencyNum = 200;

    private static Map<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        final Semaphore semaphore = new Semaphore(concurrencyNum);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < requestTotal ; i++ ){
            final int num = i;
            service.execute(() ->{
                try {
                    semaphore.acquire();
                    HashMapExample.add(num);
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
        log.info("size:{}",map.size());
    }
    private static void add(int i){
        map.put(i,i);
    }
}
