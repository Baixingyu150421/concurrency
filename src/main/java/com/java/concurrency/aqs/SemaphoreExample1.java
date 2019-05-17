package com.java.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample1 {
    public static int threadCount = 20;

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);
        for(int i = 0 ; i < threadCount ; i++){
            final int num = i;
            service.execute(() ->{
                try {
                    //控制并发数
                    //获取多个许可
                    //semaphore.acquire(3);
                    semaphore.acquire(); //获得许可
                    test(num);
                    semaphore.release(); //释放许可
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
