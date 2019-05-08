package com.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MapMain {
    public static void main(String args[]) {


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("hashMap1", "hashMap1");
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("hashtable1", "hashtable1");

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("linkedHashMap1", "linkedHashMap1");

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("concurrentHashMap1", "concurrentHashMap1");

    }
}
