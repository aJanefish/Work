package com.mode.builder;

public abstract class Builder {
    abstract void makeTitle(String title);
    abstract void makeString(String str);
    abstract void makeItem(String items[]);
    abstract void close();
}
