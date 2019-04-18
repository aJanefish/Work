package com.mode.prototype;

import com.utils.P;

public abstract class Product implements Cloneable {
    abstract void use(String s);

    //
    Product  createClone(){
        Product product = null;
        try {
            P.pln("createClone:"+this);
            product = (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return product;
    }
}
