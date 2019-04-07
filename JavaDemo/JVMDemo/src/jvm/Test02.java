package jvm;

import utils.P;

import java.util.Arrays;
import java.util.List;

public class Test02 {
    public static void main(String args[]) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        int sum = 0;
        for (int integer : list) {
            sum+=integer;
        }

        P.pln(sum);

    }

}
