package zy.walk.com.thewalkers.dagger;

import dagger.Component;
import zy.walk.com.thewalkers.activity.DaggerActivity;


@Component(modules = {OkHttpModule.class, AModule.class, BModule.class})
public interface UserComponent {
    void inject(DaggerActivity daggerActivity);
}
