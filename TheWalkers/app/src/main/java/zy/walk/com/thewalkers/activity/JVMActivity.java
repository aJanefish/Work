package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;

import dalvik.system.PathClassLoader;
import zy.walk.com.thewalkers.App;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.jvm.JvmBean;
import zy.walk.com.thewalkers.viewinjection.ViewLayout;
import zy.walk.com.thewalkers.viewinjection.ViewMethod;
import zy.walk.com.thewalkers.viewinjection.ViewUtils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


@ViewLayout(R.layout.activity_jvm)
public class JVMActivity extends AppCompatActivity {
    static {
        Log.d(App.TAG, "JVMActivity static ");


    }

    private static int id = 10002;
    private static final JvmBean jvm1 = new JvmBean("10000");
    private static JvmBean jvm2 = new JvmBean("10001");
    private JvmBean jvm3 = new JvmBean(id+++"");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_jvm);
        ViewUtils.register(this);
        Log.d(App.TAG, "JVMActivity onCreate " + this);
    }


    @ViewMethod(R.id.activity_jvm_gc)
    private void gc(View view) {
        Log.d(App.TAG, "JVMActivity gc " + jvm1 + "\n" + jvm2 + "\n" + jvm3);
        Class<? extends JVMActivity> aClass = getClass();
        Log.d(App.TAG,""+aClass);
        ClassLoader classLoader = aClass.getClassLoader();
        PathClassLoader pathClassLoader  = (PathClassLoader) classLoader;


        Log.d(App.TAG,""+pathClassLoader);
        ClassLoader classLoaderParent = classLoader.getParent();
        Log.d(App.TAG,""+classLoaderParent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(App.TAG, "JVMActivity onStop " + this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(App.TAG, "JVMActivity onDestroy " + this);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(App.TAG, "JVMActivity finalize " + this);
    }
}
