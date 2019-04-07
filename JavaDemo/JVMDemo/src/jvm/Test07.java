package jvm;

import utils.P;

public class Test07 {
    public static void main(String args[]) {
        String name = "abcdABCD";

        int cp = Character.charCount(0);
        P.pln(cp);

        for (int i = 0; i < name.length(); i++) {
            int sfs = name.codePointAt(i);
            P.pln(sfs);
        }

    }
}
