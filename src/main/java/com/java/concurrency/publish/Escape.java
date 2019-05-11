package com.java.concurrency.publish;

import com.java.concurrency.annotations.ThreadUnSafe;
import com.java.concurrency.annotations.UnRecommend;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadUnSafe
@UnRecommend
public class Escape {
    private int thisCanbeEscape = 0;
    public Escape(){
        new Innerclass();
    }
    private class Innerclass{
        public Innerclass(){
            log.info("value{}",Escape.this.thisCanbeEscape);
        }
    }
    public static void main(String[] args) {
        new Escape();
    }
}
