package com.java.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class LockExample4 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        new Thread(()->{
            try {
                reentrantLock.lock();
                log.info("wait signal ");//1
                condition.await();
            }catch (Exception e){
                log.warn("Exception",e);
            }
            log.info("get signal");//4
            reentrantLock.unlock();
        }).start();

        new Thread(()->{
            reentrantLock.lock();
            log.info("get lock");//2
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e){
                log.warn("InterruptedException",e);
            }
            condition.signalAll();
            log.info("send signal");//3
            reentrantLock.unlock();
        }).start();
    }
}
