package com.pattern;

import com.utils.P;

import java.util.regex.Pattern;

public class PatternBean {
    private final String contents[];
    private final String pattern;
    private final String des;

    public PatternBean(String contents[], String pattern, String des) {
        this.contents = contents;
        this.pattern = pattern;
        this.des = des;
    }


    public void pattern() {
        P.pln("测试:" + des);
        for (String content : contents) {
            boolean isMatch = Pattern.matches(pattern, content);
            P.pln(pattern + " 子字符串 " + isMatch + " content:" + content);
        }
        P.pln("\n");
    }
}
