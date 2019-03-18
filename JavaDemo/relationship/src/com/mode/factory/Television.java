package com.mode.factory;

import com.utils.P;

public class Television extends Product {

    String name;

    Television(String name) {
        this.name = name;
    }

    @Override
    public void use() {
        P.pln("use:"+name+" Television");
    }

    @Override
    public String toString() {
        return "Television{" +
                "name='" + name + '\'' +
                '}';
    }
}
