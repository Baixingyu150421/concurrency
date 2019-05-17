package com.java.concurrency.threadlocal;

public class RequestHolder {
    //书写规范
    private final static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void add(Long id){
        threadLocal.set(id);
    }

    public static Long getId(){
       return threadLocal.get();
    }

    public  static void remove(){
        threadLocal.remove();
    }
}
