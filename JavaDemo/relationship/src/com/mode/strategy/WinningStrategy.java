package com.mode.strategy;

public abstract class WinningStrategy implements Strategy {


    @Override
    public Hand nextHand () {
        return null;
    }

    @Override
    public abstract void study(boolean win);

    public void WinningStrategy(){

    }
}
