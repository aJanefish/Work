package com.effective;

import com.utils.P;

public class EffectiveMain {
    public static void main(String args[]) {
        enumTest1();
        enumTest2();
        PayrollDayTest();



    }

    private static void PayrollDayTest() {
        P.pln("PayrollDayTest.................");
        PayrollDay[] payrollDays = PayrollDay.values();
        for (PayrollDay payrollDay : payrollDays) {
            P.pln(payrollDay.ordinal()+" - "+payrollDay+" - "+payrollDay.pay(12,2));
        }

    }

    private static void enumTest2() {
        double x = Double.parseDouble("9");

        double y = Double.parseDouble("6");
        for (Operation value : Operation.values()) {
            P.pln(y + "" + value + "" + x + "=" + value.apply(y, x));
        }

    }

    private static void enumTest1() {
        double earthWeight = Double.parseDouble("10");
        double mass = earthWeight / Planet.EARTH.getSurfaceGravity();
        for (Planet value : Planet.values()) {
            P.pln(value + " - " + value.surfaceWeight(mass));
        }
        P.pln(Planet.values());
    }

}
