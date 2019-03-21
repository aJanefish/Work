package zy.walk.com.thewalkers.dagger;

import javax.inject.Inject;

class Teacher {

    Person person;

    @Inject
    public Teacher(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "person=" + person +
                '}';
    }
}
