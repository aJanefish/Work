package com.mode.strategy;

public class Hand {
    public static final int HAND_GUN = 0;
    public static final int HAND_GAO = 1;
    public static final int HAND_PAA = 2;
    private static final Hand[] hand = {
            new Hand(HAND_GUN), new Hand(HAND_GAO),
            new Hand(HAND_PAA)
    };
    private static final String[] name = {"石头", "剪刀", "布"};


    private int handvalue;

    public Hand(int handPaa) {
        this.handvalue = handPaa;
    }

    public static Hand getHand(int handvalue) {
        return hand[handvalue];
    }

    public boolean isStrongerThan(Hand hand) {
        return fight(hand) == 1;
    }

    private int fight(Hand hand) {
        if (this == hand) {
            return 0;
        } else if ((this.handvalue + 1) % 3 == hand.handvalue) {
            return 1;
        }else {
            return -1;
        }
    }


    @Override
    public String toString() {
        return name[handvalue];
    }
}
