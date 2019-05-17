package com.java.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample2 {
    private static CyclicBarrier barrier = new CyclicBarrier(5);
    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 10 ; i++){
            int num = i;
            Thread.sleep(800);
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
        //为保证后续的逻辑继续执行，要捕捉所有可能发生的异常
        try{
            barrier.await(2000, TimeUnit.MILLISECONDS);
        }catch (BrokenBarrierException | TimeoutException e ){
            log.warn("Exception",e);
        }
        log.info("{} continue",num);
    }
}
