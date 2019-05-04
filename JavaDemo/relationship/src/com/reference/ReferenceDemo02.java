package com.reference;

import com.bean.Apple;
import com.utils.P;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ReferenceDemo02 {
    private static List<Reference> list = new ArrayList<>();

    public static void main(String args[]) {

        int tmp = 10;
        for (int i = 0; i < tmp; i++) {
            list.add(new WeakReference(new Apple("apple" + i)));
        }
        show();

        System.gc();
        show();
    }

    private static void show() {
        for (Reference reference : list) {
            P.pln(reference + " - " + reference.get());
        }
    }
}
