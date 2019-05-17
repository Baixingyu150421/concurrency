package com.java.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@Slf4j
public class SemaphoreExample2 {
    public static int threadCount = 20;

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);
        for(int i = 0 ; i < threadCount ; i++){
            final int num = i;
            service.execute(() ->{
                try {
                    if(semaphore.tryAcquire()){ //尝试获得许可，未获得许可的线程会被丢弃
                        test(num);
                        semaphore.release();
                    }
                }catch (Exception e) {
                    log.error("exception", e);
                }
            });
        }
        service.shutdown();
    }
    public static void test(int num)throws Exception{
        log.info("threadNum{}",num);
        Thread.sleep(2000);
    }
}
