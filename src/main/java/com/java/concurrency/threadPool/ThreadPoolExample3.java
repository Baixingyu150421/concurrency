package com.java.concurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class ThreadPoolExample3 {
    public static void main(String[] args) {
        //它是单线程执行的  不会乱序 按提交次序执行
        ExecutorService service = Executors.newSingleThreadExecutor();
        for(int i = 0 ; i < 10 ; i++ ){
            final int num = i;
            service.execute(()->{
                log.info("task{}",num);
            });
        }
        service.shutdown();
    }
}
