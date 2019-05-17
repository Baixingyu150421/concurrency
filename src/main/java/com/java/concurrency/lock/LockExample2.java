package com.java.concurrency.lock;

import com.java.concurrency.annotations.ThreadSafe;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
@ThreadSafe
/**
 * 读写锁使用Demo 慎用！！！
 */
public class LockExample2 {

   private final static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

   private final Map<String,MyData> map = new TreeMap<>();

   private  static  final Lock ReadLock =  lock.readLock();

   private static final Lock WriteLock = lock.writeLock();

   public MyData get(String key){
       ReadLock.lock();
       try {
          return map.get(key);
       }finally {
           ReadLock.unlock();
       }
   }

   public void put(String key ,MyData value){
       WriteLock.lock();
       try {
           map.put(key,value);
       }finally {
           WriteLock.unlock();
       }
   }
   class MyData{

   }
}
