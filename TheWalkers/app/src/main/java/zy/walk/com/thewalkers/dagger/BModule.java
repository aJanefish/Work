package zy.walk.com.thewalkers.dagger;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class BModule {

    int age;

    public BModule(int age) {
        this.age = age;
    }

    @Provides
    Person providesPerson(User user) {
        return new Person(age, user,"ZY");
    }

    @Provides
    School providesSchool(){
        return new School("巴中中学","四川省巴中市","100001");
    }

}
