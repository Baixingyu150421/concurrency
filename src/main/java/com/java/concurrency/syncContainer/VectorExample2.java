package com.java.concurrency.syncContainer;

import com.java.concurrency.annotations.ThreadUnSafe;

import java.util.Vector;
@ThreadUnSafe
public class VectorExample2 {
    private static Vector<Integer> integers = new Vector<>();
    public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                integers.add(i);
            }
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        integers.remove(i);  // 移除9
                    }
                }
            };
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        integers.get(i);  // get9
                    }
                }
            };
            thread1.start();
            thread2.start();
    }
}
