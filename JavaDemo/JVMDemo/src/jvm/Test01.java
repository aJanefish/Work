package jvm;

import utils.P;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test01 {
    public static void main(String args[]) {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "你好");
        map.put("how are you?", "吃了没");
        P.pln(map.get("hello"));
        P.pln(map.get("how are you?"));

    }




}
