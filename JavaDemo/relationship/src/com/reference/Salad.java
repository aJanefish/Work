package com.reference;

import com.bean.Apple;

import java.lang.ref.WeakReference;

public class Salad extends WeakReference<Apple> {
    public Salad(Apple apple) {
        super(apple);
    }
}