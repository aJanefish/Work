package com.zy.example.entity;

public class InfoBean {
    /**
     * robotCode : 888888889
     * robotName : 天機一號 (小紫)
     * location : 0
     */

    private String robotCode;
    private String robotName;
    private int location;

    public String getRobotCode() {
        return robotCode;
    }

    public void setRobotCode(String robotCode) {
        this.robotCode = robotCode;
    }

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "InfoBean{" +
                "robotCode='" + robotCode + '\'' +
                ", robotName='" + robotName + '\'' +
                ", location=" + location +
                '}';
    }
}
