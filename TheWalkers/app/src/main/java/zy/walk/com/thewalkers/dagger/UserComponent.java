package zy.walk.com.thewalkers.dagger;

import javax.inject.Singleton;
import dagger.Component;
import zy.walk.com.thewalkers.activity.DaggerActivity;


@Singleton
@Component(modules = {OkHttpModule.class, BModule.class})
public interface UserComponent {
//    @Component.Builder
//    interface Builder{
//
//        //@BindsInstance
//        Builder application(Application application);
//        UserComponent build();
//
//
//    }
    //void show(Context context);
    void inject(DaggerActivity daggerActivity);
}
