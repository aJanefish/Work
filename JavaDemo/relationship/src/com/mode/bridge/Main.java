package com.mode.bridge;

public class Main {
    public static void main(String args[]){
        Display d1 = new Display(new StringDisplayImpl("Hello , China"));
        d1.display();
        Display d2 = new Display(new StringDisplayImpl("Hello , World"));
        d2.display();

        CountDisplay countDisplay = new CountDisplay(new StringDisplayImpl("Hello, Universe"));
        countDisplay.multiDisplay(3);
        countDisplay.display();

        RandDispaly dispaly = new RandDispaly(new CharDisplayImpl('A'));
        dispaly.display();
        dispaly.randomDisplay(10);

        RandDispaly dispaly1 = new RandDispaly(new StringDisplayImpl("Hello"));
        dispaly1.display();
        dispaly1.randomDisplay(10);

    }
}
