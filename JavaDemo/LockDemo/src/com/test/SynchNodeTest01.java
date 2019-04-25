package com.test;

import com.utils.P;
import sun.misc.Unsafe;
import sun.reflect.Reflection;

public class SynchNodeTest01 {
    public static void main(String args[]) {
        testShare();
        //Class var0 = Reflection.getCallerClass();
       // P.pln(var0);
        //Unsafe.getUnsafe();


    }

    private static void testShare() {
        SynchNode synchNode = new SynchNode();
        boolean isShared = synchNode.isShared();
        P.pln(isShared);
        //P.Pln(synchNode.predecessor());
        SynchNode shared = SynchNode.SHARED;
        P.pln(shared + " - " + shared.isShared());
        SynchNode synchNode1 = new SynchNode(Thread.currentThread(), SynchNode.SHARED);
        P.pln(synchNode1 + " - " + synchNode1.isShared());
        P.pln(synchNode1.prev+" - "+synchNode1.next);

    }
}
