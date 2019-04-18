package com.mode.bridge;

import com.utils.P;

public class StringDisplayImpl extends DisplayImpl {
    private String string;
    private int width;

    public StringDisplayImpl(String string) {
        this.string = string;
        this.width = string.getBytes().length;
    }

    @Override
    void rawOpen() {
        printline();
    }

    @Override
    void rawPrint() {
        P.pln("|" + string + "|");
    }

    @Override
    void rawClose() {
        printline();
    }

    private void printline() {
        P.p("+");
        for (int i = 0; i < width; i++) {
            P.p("-");
        }
        P.pln("+");

    }

}
