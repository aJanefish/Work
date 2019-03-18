package com.mode.factory;

import com.utils.P;

import java.util.ArrayList;
import java.util.List;

public class TelevisionFactory extends Factory{


    private List<Product> list = new ArrayList<>();

    public void show(){
        P.pln(list);
    }

    @Override
    protected Product createProduct(String name) {
        return new Television(name);
    }

    @Override
    protected void registerProduct(Product product) {
        list.add(product);
    }
}
