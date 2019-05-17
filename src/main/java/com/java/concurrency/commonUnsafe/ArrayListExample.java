package com.java.concurrency.commonUnsafe;

import com.java.concurrency.annotations.ThreadUnSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@Slf4j
@ThreadUnSafe
public class ArrayListExample {

    private static int requestTotal = 5000;

    private static int concurrencyNum = 200;

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        final Semaphore semaphore = new Semaphore(concurrencyNum);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < requestTotal ; i++ ){
            service.execute(() ->{
                try {
                    semaphore.acquire();
                    ArrayListExample.add();
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
        log.info("size:{}",list.size());
    }
    private static void add(){
        list.add(1);
    }
}
