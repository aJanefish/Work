package com.effective;

enum PayType {
    WEEKDAY {
        @Override
        double overtimePay(double hrs, double payRate) {
            return (hrs <= HOURS_PER_SHIFT ? 0 : (hrs - HOURS_PER_SHIFT) * payRate / 2);
        }
    },
    WEEKEND {
        @Override
        double overtimePay(double hrs, double payRate) {
            return (hrs * payRate / 2);
        }
    };

    private static final int HOURS_PER_SHIFT = 8;

    abstract double overtimePay(double hrs, double payRate);

    double pay(double hoursWorked, double payRate) {
        double basePay = hoursWorked * payRate;
        return basePay + overtimePay(hoursWorked, payRate);
    }
}

