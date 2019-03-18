package com.mode.template;

import com.utils.P;

public class StringDisplay extends AbstractDisplay {

    private String values;
    private int width = 0;

    public StringDisplay(String values) {
        this.values = values;
        this.width = values.getBytes().length;
    }

    @Override
    protected void open() {
        printLine();
    }

    @Override
    protected void print() {
        P.pln("|" + values + "|");
    }

    @Override
    protected void close() {
        printLine();
    }

    private void printLine() {
        P.p("+");

        for (int i = 0; i < width; i++) {
            P.p("-");
        }
        P.pln("+");

    }
}
