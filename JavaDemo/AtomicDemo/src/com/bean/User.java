package com.bean;

public class User{
    public User(){
        System.out.println("user 构造方法被调用");
    }

    private String name = "zhangyu";
    private int age = 10;
    private static String id="USER_ID";
    private Person person = new Person(1,name);

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", person=" + person +
                ", id=" +id+
                '}';
    }
}
