package jvm;

import utils.P;

public class Test10 {
    public static void main(String args[]) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(1);

        P.pln(new Test10().getName("z","y","w"));
    }

    private String getName(String name1, String name2, String name3) {

        return name1 + name2 + name3;
    }


}
