package jvm;

import utils.P;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test03 {

    public static void main(String args[]) {

        List list = Arrays.asList(new Integer[]{
                Integer.valueOf(1),
                Integer.valueOf(2),
                Integer.valueOf(3),
                Integer.valueOf(4)
        });
        int sum = 0;
        for (Iterator integer = list.iterator();integer.hasNext();) {
            int i = ((Integer)integer.next()).intValue();
            sum += i;
        }

        P.pln(sum);

    }
}
