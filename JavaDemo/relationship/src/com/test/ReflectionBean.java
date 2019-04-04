package com.test;

import java.util.Date;

public class ReflectionBean {
    private static ReflectionBean reflectionBean = new ReflectionBean();
    private Date date;
    private int id;
    private String name;

    private ReflectionBean() {

    }

    @Override
    public String toString() {
        return "ReflectionBean{" +
                "date=" + date +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
