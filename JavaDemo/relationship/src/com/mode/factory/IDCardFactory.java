package com.mode.factory;

import com.utils.P;

import java.util.HashMap;

public class IDCardFactory extends Factory {

    private HashMap<Integer, Product> hashMap = new HashMap<>();
    private static int id = 10000;

    public void show() {
        P.pln(hashMap);
    }

    @Override
    protected Product createProduct(String name) {
        return new IDCard(name, id);
    }

    @Override
    protected void registerProduct(Product product) {
        hashMap.put(id, product);
        id++;
    }
}
