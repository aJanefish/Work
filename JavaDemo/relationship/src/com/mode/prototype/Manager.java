package com.mode.prototype;


import com.utils.P;

import java.util.HashMap;

public class Manager{
    private HashMap hashMap = new HashMap();

    public void register(String name, Product product) {
        hashMap.put(name, product);
    }

    public Product create(String name) {
        Product product = (Product) hashMap.get(name);
        //return product;
        return product.createClone();
    }
}
