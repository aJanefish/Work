package com.mode.template;

import com.utils.P;

public class CharDisplay extends AbstractDisplay {

    private char ch;

    public CharDisplay(char ch) {
        this.ch = ch;
    }

    @Override
    protected void open() {
        P.p("<<");
    }

    @Override
    protected void print() {
        P.p(ch);
    }

    @Override
    protected void close() {
        P.pln(">>");
    }
}
