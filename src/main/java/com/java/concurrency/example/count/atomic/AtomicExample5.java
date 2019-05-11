package com.java.concurrency.example.count.atomic;

import com.java.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExample5 {

    public static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");
    @Getter
    public volatile int count = 100;

    public  static AtomicExample5 example5 = new AtomicExample5();

    public static void main(String[] args) {

        if(updater.compareAndSet(example5,100,120)){
            log.info("update 1: {}",example5.getCount());
        }
        if(updater.compareAndSet(example5,100,120)){
            log.info("update 2: {}",example5.getCount());
        }else {
            log.info("update false");
        }
    }
}
