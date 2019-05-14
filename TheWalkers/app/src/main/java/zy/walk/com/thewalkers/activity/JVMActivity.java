package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;

import dalvik.system.PathClassLoader;
import zy.walk.com.thewalkers.MyApp;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.jvm.JvmBean;
import zy.walk.com.thewalkers.viewinjection.ViewLayout;
import zy.walk.com.thewalkers.viewinjection.ViewMethod;
import zy.walk.com.thewalkers.viewinjection.ViewUtils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;


@ViewLayout(R.layout.activity_jvm)
public class JVMActivity extends AppCompatActivity {
    static {
        Log.d(MyApp.TAG, "JVMActivity static ");


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
        Log.d(MyApp.TAG, "JVMActivity onCreate " + this);
    }


    @ViewMethod(R.id.activity_jvm_gc)
    private void gc(View view) {
        Log.d(MyApp.TAG, "JVMActivity gc " + jvm1 + "\n" + jvm2 + "\n" + jvm3);
        Class<? extends JVMActivity> aClass = getClass();
        Log.d(MyApp.TAG,""+aClass);
        ClassLoader classLoader = aClass.getClassLoader();
        PathClassLoader pathClassLoader  = (PathClassLoader) classLoader;


        Log.d(MyApp.TAG,""+pathClassLoader);
        ClassLoader classLoaderParent = classLoader.getParent();
        Log.d(MyApp.TAG,""+classLoaderParent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MyApp.TAG, "JVMActivity onStop " + this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MyApp.TAG, "JVMActivity onDestroy " + this);

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(MyApp.TAG, "JVMActivity finalize " + this);
    }
}
