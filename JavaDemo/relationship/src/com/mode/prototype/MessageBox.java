package com.mode.prototype;

import com.utils.P;

public class MessageBox extends Product {
    private char aChar;

    public MessageBox(char aChar) {
        this.aChar = aChar;
    }

    @Override
    public void use(String s) {
        int length = s.length();
        for (int i = 0; i < length + 4; i++) {
            P.p(aChar);
        }
        P.pln();

        P.pln(aChar + " " + s + " " + aChar);
        for (int i = 0; i < length + 4; i++) {
            P.p(aChar);
        }
        P.pln();

    }

}
