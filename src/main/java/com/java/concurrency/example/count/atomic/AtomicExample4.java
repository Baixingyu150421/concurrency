package com.java.concurrency.example.count.atomic;

import com.java.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
@Slf4j
public class AtomicExample4 {
    public  static AtomicReference<Integer> integerAtomicReference = new AtomicReference<>(0);

    public static void main(String[] args) {
        integerAtomicReference.compareAndSet(0,2);
        integerAtomicReference.compareAndSet(1,3);
        integerAtomicReference.compareAndSet(2,5);
        log.info("value:{}",integerAtomicReference.get());
    }
}
