package com.java.concurrency.syncContainer;


import com.java.concurrency.annotations.ThreadUnSafe;

import java.util.Iterator;
import java.util.Vector;
@ThreadUnSafe
public class VectorExample3 {
    private static Vector<Integer> integers = new Vector<>();
    //ConcurrentModificationException  foreach
    private void test1(){
        for(Integer i : integers){
            integers.remove(i);
        }
    }
    //ConcurrentModificationException   会出现并发问题 iterator
    private void test2(){
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()){
            integers.remove(iterator.next());
        }
    }
    //遍历没问题
    private  void test3(){
        for(int i = 0 ; i < integers.size() ; i++){
            integers.remove(i);
        }
    }
    public static void main(String[] args) {
        integers.add(1);
        integers.add(2);
        integers.add(3);
        VectorExample3 vectorExample3 = new VectorExample3();
        vectorExample3.test2();
    }
}
