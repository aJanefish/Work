package com.algorithm.algorithm;

import com.algorithm.utils.P;

import java.util.HashMap;


public class HashMapDemo {
    public static void main(String[] args) {
        P.pln("HashMap Demo");
        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("zhangyu",1);
        hashMap.put("wangxiaofang",2);
        P.pln(hashMap);
        // hashMap 的大小都是2^n
        int tmp = 35;
        for (int i = 0; i < tmp; i++) {
            P.pln(1<<i);
        }

    }
}
