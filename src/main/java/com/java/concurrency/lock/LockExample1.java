package com.java.concurrency.lock;

import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@ThreadSafe
public class LockExample1 {
    private static int requestTotal = 5000;

    private static int concurrencyNum = 200;

    private static int count = 0;

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        final Semaphore semaphore = new Semaphore(concurrencyNum);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < requestTotal ; i++ ){
            service.execute(() ->{
                try {
                    semaphore.acquire();
                    LockExample1.add();
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
    private static void add(){
        lock.lock();
        try{
            count ++;
        }finally {
            lock.unlock();
        }
    }
}
