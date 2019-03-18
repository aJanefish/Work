package com.mode.template;

public abstract class AbstractDisplay {
    protected abstract void open();

    protected abstract void print();

    protected abstract void close();

    public final void disPlay() {
        open();
        for (int i = 0; i < 5; i ++) {
            print();
        }
        close();
    }
}
