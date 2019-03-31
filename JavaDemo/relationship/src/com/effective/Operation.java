package com.effective;

public enum Operation {
    PLUS("+") {
        @Override
        double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        double apply(double x, double y) {
            return x / y;
        }
    };

    private final String aymbol;

    Operation(String aymbol) {
        this.aymbol = aymbol;
    }

    @Override
    public String toString() {
        return aymbol;
    }

    abstract double apply(double x, double y);
}
