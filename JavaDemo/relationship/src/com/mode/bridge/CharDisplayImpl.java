package com.mode.bridge;

import com.utils.P;

public class CharDisplayImpl extends DisplayImpl {
    private char aChar;

    public CharDisplayImpl(char aChar) {
        this.aChar = aChar;
    }

    @Override
    void rawOpen() {
        printline();
    }

    @Override
    void rawPrint() {
        P.p(aChar);
    }

    @Override
    void rawClose() {
        printline();
    }

    private void printline() {
        P.p("\n+");
        for (int i = 0; i < 10; i++) {
            P.p("-");
        }
        P.pln("+");

    }
}
