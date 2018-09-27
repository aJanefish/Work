package com.butler.launcher.event;

public class WifiEvent {

    private  boolean isConnect = false;

    public WifiEvent(boolean isConnect) {
        this.isConnect = isConnect;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }
}
