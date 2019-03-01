package com.interfaces;

import com.utils.P;

/**
 * 工厂设计模式
 * */
interface Fruit{
    void eat();
}

class Apple implements Fruit{

    @Override
    public void eat() {
        P.pln("eat Apple");
    }
}

class Orange implements Fruit{

    @Override
    public void eat() {
        P.pln("eat Orange");
    }
}

class Banana implements Fruit{

    @Override
    public void eat() {
        P.pln("eat Banana");
    }
}


class Factory{
    public static Fruit getInstance(String className) throws Exception {
        Fruit fruit = null;
        if("apple".equals(className)){
            fruit = new Apple();
        }else if("orange".equals(className)){
            fruit = new Orange();
        }else if("banana".equals(className)){
            fruit = new Banana();
        }else {
            throw new Exception("the className is no definition");
        }
        return fruit;
    }
}


public class InterfaceCaseDemo04 {

    public static void main(String[] args) throws Exception {
        Fruit fruit = Factory.getInstance("apple");
        fruit.eat();
        fruit = Factory.getInstance("orange");
        fruit.eat();
        fruit = Factory.getInstance("banana");
        fruit.eat();

        fruit = Factory.getInstance("bananaa");
        fruit.eat();
    }
}
