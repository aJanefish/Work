package jvm;

import utils.P;

public class Test06 {
    public static void main(String args[]) {

        int tmp = 0;
        while (true) {
            P.pln("sss");
            if (tmp++ < 10) {
                break;
            }
        }
    }
}
