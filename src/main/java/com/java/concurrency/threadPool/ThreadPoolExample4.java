package com.java.concurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        //允许调度的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
//        for(int i = 0 ; i < 10 ; i++ ){
//            final int num = i;
//            scheduledExecutorService.execute(()->{
//                log.info("task{}",num);
//            });
//        }



        //延迟三秒执行
//        scheduledExecutorService.schedule(()->{
//            log.info("schedule run");
//        },3, TimeUnit.SECONDS);


        //按照一定的速率去执行
        scheduledExecutorService.scheduleAtFixedRate(()->{
            log.info("run task");
        },1,3,TimeUnit.SECONDS);

        //定时执行任务
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer task");
            }
        },new Date(),5*1000);

        //scheduledExecutorService.shutdown();
    }
}
