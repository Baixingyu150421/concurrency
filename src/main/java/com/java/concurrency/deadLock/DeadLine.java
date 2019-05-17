package com.java.concurrency.deadLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadLine implements Runnable {
    public int flag = 1;

    private static Object object1 = new Object() , object2 = new Object();

    @Override
    public void run() {
        log.info("flag:{}",flag);
        if(flag == 1){
            synchronized(object1){
                try{
                    Thread.sleep(500);
                } catch (Exception e){
                    log.error("Exception" , e);
                }
                synchronized(object2){
                    log.info("1");
                }
            }
        }
        if(flag == 0){
            synchronized(object2){
                try{
                    Thread.sleep(500);
                } catch (Exception e){
                    log.error("Exception" , e);
                }
                synchronized(object1){
                    log.info("0");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLine deadLine = new DeadLine();
        DeadLine deadLine1 = new DeadLine();
        deadLine1.flag = 1;
        deadLine1.flag = 0;
        new Thread(deadLine).start();
        new Thread(deadLine1).start();
    }
}
