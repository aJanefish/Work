package stackframe;

import java.io.Serializable;

public class Overload {

    public static void sayHello(Object object){
        System.out.println("Hello object");
    }

    public static void sayHello(int i){
        System.out.println("Hello int");
    }

    public static void sayHello(long l){
        System.out.println("Hello long");
    }

    public static void sayHello(Character c){
        System.out.println("Hello Character");
    }

    public static void sayHello(char c) {
        System.out.println("Helllo char");
    }

    public static void sayHello(char... chars){
        System.out.println("Hello char...");
    }

    public static void sayHello(Serializable serializable){
        System.out.println("Hello Serializable");
    }


    public static void main(String args[]){
        sayHello('a');
    }
}
