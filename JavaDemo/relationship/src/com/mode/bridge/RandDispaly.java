package com.mode.bridge;

import java.util.Random;

public class RandDispaly extends Display {
    Random random = new Random();

    public RandDispaly(DisplayImpl impl) {
        super(impl);
    }

    public void randomDisplay(int times) {
        int time = random.nextInt(times);
        open();
        for (int i = 0; i < time; i++) {
            print();
        }
        close();
    }
}
