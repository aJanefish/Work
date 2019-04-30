package com.test;

import com.utils.P;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test07 {
    public static void main(String args[]) {

        Map<String, String> cache = new LinkedHashMap<>(100, 0.75f, true);
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
        P.pln(cache);
        P.pln(cache.remove("5"));
        P.pln(cache);

    }
}
