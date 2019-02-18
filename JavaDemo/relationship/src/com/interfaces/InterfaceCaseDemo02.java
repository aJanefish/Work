package com.interfaces;

interface USB {        // 定义了USB接口
    public void start();    // USB设备开始工作

    public void stop();    // USB设备结束工作
}

class Computer {
    public static void plugin(USB usb) {    // 电脑上可以插入USB设备，向上转型，这里就相当于一个接口，只有符合这个接口的标准的类的对象（即只有继承这个接口的类的对象），才能被这个方法调用。
        usb.start();
        System.out.println("=========== USB 设备工作 ========");
        usb.stop();
    }
}

class Flash implements USB {
    public void start() {    // 覆写方法
        System.out.println("U盘开始工作。");
    }

    public void stop() {        // 覆写方法
        System.out.println("U盘停止工作。");
    }
}

class Print implements USB {
    public void start() {    // 覆写方法
        System.out.println("打印机开始工作。");
    }

    public void stop() {        // 覆写方法
        System.out.println("打印机停止工作。");
    }
};

public class InterfaceCaseDemo02 {
    public static void main(String args[]) {
        Computer.plugin(new Flash());
        Computer.plugin(new Print());
    }
}

