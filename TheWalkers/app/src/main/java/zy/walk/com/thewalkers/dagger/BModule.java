package zy.walk.com.thewalkers.dagger;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BModule {


    @Named("release")
    @Provides
    //@Singleton
    Person providePerson(){
        return new Person(100);
    }


    @Named("debug")
    @Provides
    Person providePerson1(){
        return new Person();
    }
}
