package com.java.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {
   static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception{
           log.info("do something ");
           Thread.sleep(5000);
           return "OK";
        }
    }
    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> submit = service.submit(new MyCallable());
        log.info("do in main thread");
        String s = submit.get();
        log.info("result:{}",s);
        service.shutdown();
    }
}
