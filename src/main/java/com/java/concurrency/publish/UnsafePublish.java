package com.java.concurrency.publish;


import com.java.concurrency.annotations.ThreadUnSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@ThreadUnSafe
@Slf4j
public class UnsafePublish {
    private String [] state ={"a","b","c"};
    public String [] getState(){
        return state;
    }
    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("Array{}", Arrays.toString(unsafePublish.getState()));
        String[] state = unsafePublish.getState();
        state[0] = "d";
        log.info("Array{}", Arrays.toString(unsafePublish.getState()));
    }
}
