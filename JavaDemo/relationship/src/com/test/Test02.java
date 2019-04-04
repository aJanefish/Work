package com.test;

import com.utils.P;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class Test02 {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        P.pln("finalize:" + this);
    }

    @Override
    public String toString() {
        return "Test02{"+hashCode()+"}";
    }

    public static void main(String args[]) throws InterruptedException {
        Test02 test02;
        test02 = new Test02();
        test02 = null;

        WeakReference<Test02>  weakReference = new WeakReference<>(new Test02());
        SoftReference<Test02> softReference = new SoftReference<>(new Test02());
        softReference.clear();

        PhantomReference<Test02> phantomReference = new PhantomReference<>(new Test02(),new ReferenceQueue<>());

        System.gc();
        TimeUnit.SECONDS.sleep(1);
    }


}
