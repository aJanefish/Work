package zy.walk.com.thewalkers.dagger;


import dagger.Module;
import dagger.Provides;

@Module
public class AModule {

    @Provides
    AModule provideB(){
        return new AModule();
    }
}
