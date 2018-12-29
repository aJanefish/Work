package com;

class MixedOrder {
    int a = 0;
    boolean flag = false;
    public int i = 0;


    public void writer() {
        a = 1;
        flag = true;
    }

    public void read() {
        if (flag) {
            i = a + 1;
        }else {
            i = a + 1;
        }
    }

    @Override
    public String toString() {
        return "MixedOrder{" +
                "a=" + a +
                ", flag=" + flag +
                ", i=" + i +
                '}';
    }
}
