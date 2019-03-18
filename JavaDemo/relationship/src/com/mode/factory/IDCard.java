package com.mode.factory;

import com.utils.P;

public class IDCard extends Product {

    private String owner;

    private final int id;

    IDCard(String owner, int id) {
        this.owner = owner;
        this.id = id;
        P.pln("制作" + owner + "的IDCard");
    }

    @Override
    public void use() {
        P.pln("使用" + owner + "的IDCard");
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "IDCard{" +
                "owner='" + owner + '\'' +
                ", id=" + id +
                '}';
    }
}
