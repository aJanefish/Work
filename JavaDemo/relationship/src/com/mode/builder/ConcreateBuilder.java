package com.mode.builder;

public class ConcreateBuilder extends Builder {

    private Builder builder;

    public ConcreateBuilder(Builder builder) {
        this.builder = builder;
    }

    @Override
    void makeTitle(String title) {
        this.builder.makeTitle(title);
    }

    @Override
    void makeString(String str) {
        this.builder.makeString(str);
    }

    @Override
    void makeItem(String[] items) {
        this.builder.makeItem(items);
    }

    @Override
    void close() {
        this.builder.close();
    }
}
