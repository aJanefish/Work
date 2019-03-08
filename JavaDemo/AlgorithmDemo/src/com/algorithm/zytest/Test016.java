package com.algorithm.zytest;

import java.math.BigDecimal;
import java.util.Scanner;

public class Test016 {


    //+1.23400E-03 -- 0.00123400
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        BigDecimal s=scan.nextBigDecimal();
        System.out.println(s.toPlainString());
        scan.close();
    }
}
