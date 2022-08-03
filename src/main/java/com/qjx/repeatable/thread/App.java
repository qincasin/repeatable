package com.qjx.repeatable.thread;


import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Integer put = map.put(i, i);
            System.out.println(put);
        }
        System.out.println("------");
        for (int i = 1; i < 11; i++) {
            Integer put = map.put(i, i);
            System.out.println(put + " i: "+i);
        }

    }
}
