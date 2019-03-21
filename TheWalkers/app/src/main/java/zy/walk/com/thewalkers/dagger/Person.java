package zy.walk.com.thewalkers.dagger;

import javax.inject.Inject;

public class Person {
    int age;
    User user;
    String name;

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", user=" + user +
                ", name='" + name + '\'' +
                '}';
    }

    public Person(int age, User user, String name) {
        this.age = age;
        this.user = user;
        this.name = name;
    }

}
