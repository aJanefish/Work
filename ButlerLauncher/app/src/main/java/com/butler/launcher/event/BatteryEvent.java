package com.butler.launcher.event;

public class BatteryEvent {

    /**
     * 电量
     * */
    private int level = 0;

    public BatteryEvent(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
