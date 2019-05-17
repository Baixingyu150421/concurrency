package com.java.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierExample3 {
    //到达屏障后
    private static CyclicBarrier barrier = new CyclicBarrier(5 , ()->{
        log.info("callback is running");
    });
    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 10 ; i++){
            int num = i;
            Thread.sleep(700);
            service.execute(()->{
                try {
                    race(num);
                }catch (Exception e){
                    log.error("Exception",e);
                }
            });
        }
        service.shutdown();
    }
    public static void race(int num) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready" , num);
        barrier.await();
        log.info("{} continue",num);
    }
}
