package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.server.MyIntentService;
import zy.walk.com.thewalkers.server.MyService;
import zy.walk.com.thewalkers.viewinjection.ViewField;
import zy.walk.com.thewalkers.viewinjection.ViewLayout;
import zy.walk.com.thewalkers.viewinjection.ViewMethod;
import zy.walk.com.thewalkers.viewinjection.ViewUtils;

/**
 * 研究Android Server 服务
 * */

@ViewLayout(R.layout.activity_android_server_main)
public class AndroidServerMainActivity extends AppCompatActivity {


    @ViewField(R.id.activity_android_server_main_des)
    private TextView des;

    private Handler mHandler = new Handler();

    private MyService.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.setName(""+name);
            //myBinder.getName();
            myBinder.show();
            des.setText(myBinder.getName());
        }
        @Override public void onServiceDisconnected(ComponentName name) {
            myBinder = null;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_android_server_main);
        ViewUtils.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @ViewMethod(R.id.activity_android_server_main_start_server)
    public void start_normal_server(View view){
        /**
         * 启动服务
         * */
        Intent startIntent = new Intent(this, MyService.class);
        startService(startIntent);
    }

    @ViewMethod(R.id.activity_android_server_main_stop_server)
    public void stop_normal_server(View view){
        /**
         * 关闭服务
         * */
        Intent startIntent = new Intent(this, MyService.class);
        stopService(startIntent);
    }

    @ViewMethod(R.id.activity_android_server_main_bind_server)
    public void bind_server(View view){
        /**
         * Bind
         * */
        Intent bindIntent = new Intent(this,MyService.class);
        bindService(bindIntent,connection,BIND_AUTO_CREATE);
    }

    @ViewMethod(R.id.activity_android_server_main_unbind_server)
    public void unbind_server(View view){
        /**
         * Unbind
         * */
        unbindService(connection);
    }


    @ViewMethod(R.id.activity_android_server_main_set_name)
    public void set_name(View view){
        Date date = new Date();
        myBinder.setName(date.toString());
    }

    @ViewMethod(R.id.activity_android_server_main_get_name)
    public void get_name(View view){
        des.setText(myBinder.getName());
    }



    @ViewMethod(R.id.activity_android_server_main_intent_server_baz)
    public void baz(View view){
        MyIntentService.startActionBaz(this,"baz1","baz2");
    }

    @ViewMethod(R.id.activity_android_server_main_intent_server_foo)
    public void foo(View view){
        MyIntentService.startActionBaz(this,"foo1","foo2");

    }


}
