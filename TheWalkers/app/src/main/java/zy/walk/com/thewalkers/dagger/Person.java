package zy.walk.com.thewalkers.dagger;


import javax.inject.Inject;

public class Person {
    int age;

    //@Inject
    public Person() {
    }

    //@Inject
    public Person(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}
