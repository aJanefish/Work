package com.mode.builder;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    protected void constriuct(){
        builder.makeTitle("Greeting");
        builder.makeString("morning");
        builder.makeItem(new String[]{
                "morning1 ","morning2 "
        });
        builder.makeString("hello");
        builder.makeItem(new String[]{
                "hello1","hello2","hello3"
        });
        builder.close();
    }
}
