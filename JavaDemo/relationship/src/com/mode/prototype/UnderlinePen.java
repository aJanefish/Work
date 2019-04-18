package com.mode.prototype;

import com.utils.P;

public class UnderlinePen extends Product {

    private char aChar;

    public UnderlinePen(char aChar) {
        this.aChar = aChar;
    }

    @Override
    public void use(String s) {
        int length = s.length();
        P.pln("\"" + s + "\"");
        for (int i = 0; i < length; i++) {
            P.p(aChar);
        }
        P.pln();
    }
}
