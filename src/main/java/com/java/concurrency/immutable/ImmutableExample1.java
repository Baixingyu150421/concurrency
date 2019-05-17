package com.java.concurrency.immutable;

import com.google.common.collect.Maps;
import com.java.concurrency.annotations.ThreadUnSafe;

import java.util.Map;
@ThreadUnSafe
public class ImmutableExample1 {
    private static final String s = "a";
    private static final int b = 5;
    private static final Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(2,4);
        map.put(3,8);
    }

    public static void main(String[] args) {
        //s = 'a';  final修饰的基本类型初始化后不允许被修改
        //b = 10;
        //map = Maps.newHashMap(); final修饰的应用类型初始化后不允许指向新的引用
        //其内部值是可以修改的
        map.put(1,3);
        System.out.println(map.get(1));
    }

    public void test(final int b){
        //若不允许修改传参的值用final修饰
        //b = 7;
    }
}
