package jvm;

import utils.P;

public class Test04 {
    public static void main(String args[]) {

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 331;
        Integer f = 331;
        Long g = 3l;

        P.pln(c == d);
        P.pln(e == f);
        P.pln(c == (a + b));
        P.pln(c.equals(a + b));
        P.pln(g == (a + b));
        P.pln(g.equals(a + b));
    }
}
