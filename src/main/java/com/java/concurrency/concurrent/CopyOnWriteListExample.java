package com.java.concurrency.concurrent;

import com.java.concurrency.annotations.ThreadSafe;
import com.java.concurrency.commonUnsafe.ArrayListExample;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class CopyOnWriteListExample {
    private static int requestTotal = 5000;

    private static int concurrencyNum = 200;
    //使用并发容器
    private static CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        final Semaphore semaphore = new Semaphore(concurrencyNum);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < requestTotal ; i++ ){
            service.execute(() ->{
                try {
                    semaphore.acquire();
                    CopyOnWriteListExample.add();
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
        log.info("size:{}",integers.size());
    }
    private static void add(){
        integers.add(1);
    }
}
