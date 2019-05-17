package com.java.concurrency.immutable;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.java.concurrency.annotations.ThreadSafe;

@ThreadSafe
public class ImmutableExample3 {

    private final static ImmutableList<Integer> list = ImmutableList.of(1,2,3,4);
    private final static ImmutableSet<Integer> SET = ImmutableSet.copyOf(list);

    private final static ImmutableBiMap<Integer,Integer> MAP1 = ImmutableBiMap.of(1,3,2,4,3,5);

    private final static ImmutableBiMap<Integer,Integer> MAP2 = ImmutableBiMap.<Integer,Integer>builder().put(1,4).put(2,5).put(3,6).build();

    public static void main(String[] args) {
        //修改时会报错
        MAP2.put(1,2);
    }
}
