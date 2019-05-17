package com.java.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable ");
                Thread.sleep(5000);
                return "done";
            }
        });
        new Thread(futureTask).start();
        try {
            String s = futureTask.get();
            log.info("main Thread");
            log.info("result : {}",s);
        }catch (InterruptedException | ExecutionException e){
            log.warn("Exception",e);
        }
    }
}
