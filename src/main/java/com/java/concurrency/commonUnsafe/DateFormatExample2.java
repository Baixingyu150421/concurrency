package com.java.concurrency.commonUnsafe;

import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
@Slf4j
@ThreadSafe
public class DateFormatExample2 {
    private static int RequestCount = 5000;
    private static int concurrencyCount = 200;
    //线程安全的 处理日期有优势
    private static DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyyMMdd");
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(RequestCount);
        Semaphore semaphore = new Semaphore(concurrencyCount);
        for(int i = 0 ; i < RequestCount ; i++ ){
            executorService.execute(()->{
                try{
                    semaphore.acquire();
                    DateFormatExample2.update();
                    semaphore.release();
                    countDownLatch.countDown();
                }catch (Exception e){
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }
    public static void update(){
        dateTimeFormat.parseDateTime("20180202").toDate();
    }
}
