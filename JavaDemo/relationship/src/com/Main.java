package com;

import com.utils.P;

/**
 * JAVA 复习
 */

class SuperMain {
    private static int SuperMainId;

    static {
        SuperMainId = 1000;
        P.pln("SuperMain static{" + SuperMainId + "}");
    }

    public static int getMainId() {
        return SuperMainId;
    }

    {
        P.pln("SuperMain {" + SuperMainId + "}");
    }

    public SuperMain(int age) {
        P.pln("SuperMain:" + SuperMainId++);
    }

}

public class Main extends SuperMain {
    private static int mainId;
    private String name = "sDefault";

    static {
        mainId = 1000;
        P.pln("static{" + mainId + "}");

    }

    public static int getMainId() {
        return mainId;
    }

    {
        P.pln("Main{" + mainId + "}=" + this.name);
        //System.exit(0);
    }

    public Main(String name) {
        super(1);
        P.pln("Main:" + mainId++);
        this.name = name;
    }
    public static boolean isAdmin(String userId){
        userId.toLowerCase().intern();
        return userId.toLowerCase()=="admin";
    }
    public static void main(String[] args){
        System.out.println(isAdmin("Admin"));
    }

//    public static void main(String[] args) {
//
//
//        Main main = null;
//        main = new Main("First");
//
//        main = new Main("Second");
//    }

}
