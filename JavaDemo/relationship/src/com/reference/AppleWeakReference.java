package com.reference;

import com.bean.Apple;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class AppleWeakReference extends WeakReference<Apple> {

    String name;

    public AppleWeakReference(Apple referent, ReferenceQueue<? super Apple> q) {
        super(referent, q);
        name = referent.getName();
    }

    @Override
    public String toString() {
        return "AppleWeakReference{" +
                "name='" + name + '\'' +
                " hashCode='"+hashCode()+
                '}';
    }
}
