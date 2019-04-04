package com.test;

import com.utils.P;

/**
 * 装箱测试
 */
public class Test03 {

    public static void main(String args[]) {
        boxInteger();
        boxBoolean();
        boxByte();
        boxChar();
        boxShort();
        boxLong();
        boxDouble();


    }

    private static void boxDouble() {
        P.pln("boxDouble..............................");
        Double double1 = 1.00;
        Double double2 = 1.00;
        showObject(double1, double2);

        Double double3 = Double.valueOf("1");
        Double double4 = Double.valueOf("1");
        showObject(double3, double4);
        double1.equals(double2);
    }


    private static void boxLong() {
        P.pln("boxLong..............................");
        Long long1 = 1l;
        Long long2 = 1l;
        showObject(long1, long2);


        Long long3 = Long.valueOf(1L);
        Long long4 = Long.valueOf(1L);
        showObject(long4, long3);

        Long long5 = Long.valueOf(128L);
        Long long6 = Long.valueOf(128L);
        showObject(long5, long6);

        Long long7 = Long.valueOf(-128L);
        Long long8 = Long.valueOf(-128L);
        showObject(long7, long8);

        Long long9 = new Long(11L);
        Long long10 = new Long(11L);
        showObject(long9, long10);
    }

    private static void showObject(Object o1, Object o2) {
        P.pln(o1.getClass() + " -          " + (o1 == o2) + " - " + o1.equals(o2));
    }

    private static void boxShort() {
        P.pln("boxShort..............................");
        Short short1 = 1;
        Short short2 = 1;
        showShort(short1, short2);

        Short short3 = Short.valueOf("1");
        Short short4 = Short.valueOf("1");
        showShort(short3, short4);

        Short short33 = Short.valueOf("128");
        Short short44 = Short.valueOf("128");
        showShort(short33, short44);

        Short short5 = new Short("1");
        Short short6 = new Short("1");
        showShort(short5, short6);
    }

    private static void showShort(Short s1, Short s2) {
        P.pln((s1 == s2) + " - " + s1.equals(s2));
    }

    private static void boxChar() {
        P.pln("boxChar..............................");

        Character character1 = 'A';
        Character character2 = 'A';
        showChar(character1, character2);

        Character character3 = Character.valueOf('*');
        Character character4 = Character.valueOf('*');
        showChar(character4, character3);


        Character character5 = new Character('C');
        Character character6 = new Character('C');
        showChar(character5, character6);

    }

    private static void showChar(Character c1, Character c2) {
        P.pln((c1 == c2) + " - " + c1.equals(c2));
    }

    private static void boxByte() {
        P.pln("boxBoolean..............................");
        Byte byte1 = 127;
        Byte byte2 = 127;
        showByte(byte1, byte2);

        Byte byte3 = Byte.valueOf("1");
        Byte byte4 = Byte.valueOf("1");
        showByte(byte3, byte4);


        Byte byte5 = new Byte("1");
        Byte byte6 = new Byte("1");
        showByte(byte5, byte6);
    }

    private static void showByte(Byte b1, Byte b2) {
        P.pln((b1 == b2) + " - " + b1.equals(b2));
    }


    private static void boxBoolean() {
        P.pln("boxBoolean..............................");
        Boolean boolean1 = true;
        Boolean boolean2 = true;
        showBoolean(boolean1, boolean2);

        Boolean boolean3 = false;
        Boolean boolean4 = false;
        showBoolean(boolean3, boolean4);

        Boolean boolean5 = new Boolean("true");
        Boolean boolean6 = new Boolean("true");
        showBoolean(boolean5, boolean6);

    }

    private static void showBoolean(Boolean b1, Boolean b2) {
        P.pln((b1 == b2) + " - " + b1.equals(b2));
    }


    private static void boxInteger() {
        //
        P.pln("boxInteger..............................");
        Integer integer1 = 1000;
        Integer integer2 = 1000;

        P.pln(integer1.toString());
        P.pln(integer1 == integer2);
        P.pln(integer1.equals(integer2));


        Integer integer3 = 122;
        Integer integer4 = 122;
        P.pln(integer3 == integer4);
        P.pln(integer3.equals(integer4));

        Integer integer5 = Integer.valueOf(128);
        Integer integer6 = Integer.valueOf(128);
        P.pln(integer6 == integer5);
        P.pln(integer6.equals(integer6));
    }

}
