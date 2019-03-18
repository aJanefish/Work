package com.mode.factory;

public abstract class Factory {

    public final Product create(String name) {
        Product product = createProduct(name);
        registerProduct(product);
        return product;
    }


//    protected Product createProduct(String name){
//        throw new FactoryMethodRuntimeException();
//    };

    protected abstract Product createProduct(String name);

    protected abstract void registerProduct(Product product);


}
