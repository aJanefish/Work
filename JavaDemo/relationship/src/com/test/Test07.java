package com.test;

import com.utils.P;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.sun.deploy.perf.DeployPerfUtil.clear;

public class Test07 {
    private static final int MAX = 4;
    private static  Map<String, String> cache;
    public static void main(String args[]) {

        cache = new LinkedHashMap<>(MAX, 0.75f, true);
        int tmp = 10;
        for (int i = 0; i < tmp; i++) {
            P.pln(cache.put("" + i, "" + i));
        }
        P.pln(cache);
        cache.get("5");
        P.pln(cache);
        cache.get("7");
        P.pln(cache);
        String ss = cache.put("5", "55");
        P.pln(ss);
        //P.pln(cache);
        //P.pln(cache.remove("5"));
        P.pln(cache);

        balance();
        P.pln(cache);

        clear();
        P.pln(cache);

    }

    public static void balance(){
        cortol(MAX);
    }


    public static void clear(){
        cortol(0);
    }




    public static void cortol(int size){
        while(cache.size() > size){
            Iterator<Map.Entry<String, String>> ss = cache.entrySet().iterator();
            Map.Entry<String, String> sss = ss.next();
            P.pln(sss);
            ss.remove();
        }
    }
}
