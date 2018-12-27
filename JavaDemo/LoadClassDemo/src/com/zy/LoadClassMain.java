package com.zy;

import com.sun.org.apache.bcel.internal.generic.LoadClass;
import com.zy.utils.Print;
import sun.dc.pr.PRError;

public class LoadClassMain {

    public static void main(String[] ages){
        Print.P("LoadClassMain ---------------- main");
        Print.P(ClassLoader.getSystemClassLoader());
    }
}
