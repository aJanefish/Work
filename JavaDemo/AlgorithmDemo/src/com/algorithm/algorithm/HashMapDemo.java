package com.algorithm.algorithm;

import com.algorithm.utils.Print;

import java.util.HashMap;


public class HashMapDemo {
    public static void main(String[] args) {
        Print.println("HashMap Demo");
        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("zhangyu",1);
        hashMap.put("wangxiaofang",2);
        Print.println(hashMap);
        // hashMap 的大小都是2^n
        int tmp = 35;
        for (int i = 0; i < tmp; i++) {
            Print.println(1<<i);
        }

    }
}
