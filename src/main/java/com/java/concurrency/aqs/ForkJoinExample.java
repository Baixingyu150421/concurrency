package com.java.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分治
 */
@Slf4j
public class ForkJoinExample extends RecursiveTask<Integer> {

    private int start ;

    private int end ;

    private int threshold = 2;

    public ForkJoinExample(int start , int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if(end - start <= threshold){
            for(int i = start ; i <=end ; i++){
                sum += i;
            }
        }else {
            int middle = (start +end) / 2;
            ForkJoinExample task1 = new ForkJoinExample(start, middle);
            ForkJoinExample task2 = new ForkJoinExample(middle + 1, end);
            task1.fork();
            task2.fork();
            int left = task1.join();
            int right = task2.join();
            sum = left + right;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception{
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinExample forkJoinExample = new ForkJoinExample(1,100);
        ForkJoinTask<Integer> submit = pool.submit(forkJoinExample);
        Integer integer = submit.get();
        log.info("result:{}", integer);
    }
}
