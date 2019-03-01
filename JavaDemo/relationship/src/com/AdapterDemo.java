package com;

import com.utils.P;

/**
 * 适配器模式
 */

interface Window {        // 定义Window接口，表示窗口操作
    public void open();    // 打开

    public void close();    // 关闭

    public void activated();    // 窗口活动

    public void iconified();    // 窗口最小化

    public void deiconified();// 窗口恢复大小
}

abstract class WindowAdapter implements Window {

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public void activated() {

    }

    @Override
    public void iconified() {

    }

    @Override
    public void deiconified() {

    }
}

class WindowImpl extends WindowAdapter {
    @Override
    public void open() {
        super.open();
        P.pln("窗口打开");
    }

    @Override
    public void close() {
        super.close();
        P.pln("窗口关闭");
    }
}


public class AdapterDemo {
    public static void main(String[] args) {
        Window window = new WindowImpl();
        window.open();
        window.close();
        window.activated();

        window = new WindowAdapter() {
            @Override
            public void open() {
                super.open();
                P.pln("窗口打开1");
            }
        };
        window.open();
    }
}

