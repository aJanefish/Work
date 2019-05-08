package com.bean;

import com.utils.P;

public class Apple {

    private String name;

    public Apple(String name) {
        this.name = name;
        P.pln("Apple Init: " + name + " - " + Thread.currentThread());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 覆盖finalize，在回收的时候会执行。
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        P.pln("Apple finalize： " + name + " - " + Thread.currentThread());
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                '}' + ", hashCode:" + this.hashCode();
    }
}
