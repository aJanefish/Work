package zy.walk.com.thewalkers.dagger;

import javax.inject.Inject;

/**
 * 学生
 * */
public class Student {
    Person person;
    School school;
    Teacher teacher;

    @Inject
    public Student(Person person, School school, Teacher teacher) {
        this.person = person;
        this.school = school;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student{" +
                "person=" + person +
                ", school=" + school +
                ", teacher=" + teacher +
                '}';
    }
}
