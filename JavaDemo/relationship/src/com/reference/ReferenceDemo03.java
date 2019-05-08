package com.reference;

import com.bean.Apple;
import com.utils.P;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class ReferenceDemo03 {
    public static void main(String args[]) {
        WeakReference weakReference = new WeakReference(new Apple("apple1"));

        ReferenceQueue<Apple> referenceQueue = new ReferenceQueue<>();
        WeakReference weakReference1 = new WeakReference(new Apple("apple01"), referenceQueue);
        WeakReference weakReference2 = new WeakReference(new Apple("apple02"), referenceQueue);
        P.pln("weakReference1 - "+weakReference1);
        P.pln("weakReference2 - "+weakReference2);

        System.gc();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        try {
//            P.pln("remove1 ");
//            Reference<? extends Apple> ss = referenceQueue.remove();
//            P.pln(ss);
//            P.pln("remove2 ");
//            Reference<? extends Apple> ss1 = referenceQueue.remove();
//            P.pln(ss1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        Reference<? extends Apple> ss = referenceQueue.poll();
        P.pln(ss);
        Reference<? extends Apple> ss1 = referenceQueue.poll();
        P.pln(ss1);
        Reference<? extends Apple> ss2 = referenceQueue.poll();
        P.pln(ss2);

    }
}
