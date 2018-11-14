package zy.walk.com.thewalkers;

import zy.walk.com.thewalkers.activity.BaseActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.toast.IToastStyle;
import com.hjq.toast.ToastUtils;
import com.hjq.toast.style.ToastBlackStyle;
import com.hjq.toast.style.ToastQQStyle;
import com.hjq.toast.style.ToastWhiteStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 吐司是apk开发中常用到的功能,做一个小集合，方便开发
 *
 * 集成方法:
 * implementation 'com.hjq:toast:3.0'
 *
 * // 在Application中初始化
 * ToastUtils.init(this);
 *
 * */

public class ToastActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        ToastUtils.init(getApplication());
    }

    @SuppressLint("WrongConstant")
    @Override
    public void initView() {
    }

    @Override
    public void initDate() {
        List<String> list = new ArrayList<>();
        list.add("我是系统默认Toast");
    }

    public void defaultToast(View view) {
        Toast.makeText(this,"我是系统默认Toast",Toast.LENGTH_LONG).show();
    }

    public void toast1(View view) {
        ToastUtils.show("我是Toast");
    }

    public void toast2(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.show("我是子线程中弹出的吐司");
            }
        }).start();
    }

    public void toast3(View view) {
        ToastUtils.initStyle(new ToastWhiteStyle());
        ToastUtils.show("动态切换吐司样式成功");
    }

    public void toast4(View view) {
        ToastUtils.initStyle(new ToastBlackStyle());
        ToastUtils.show("动态切换吐司样式成功");
    }

    public void toast5(View view) {
        ToastUtils.initStyle(new ToastQQStyle());
        ToastUtils.show("QQ样式那种还不简单，分分钟的事");
    }

    public void toast6(View view) {

        ToastUtils.initStyle(new ToastQQStyle(){
            @Override
            public int getGravity() {
                return Gravity.BOTTOM;
            }
        });
        ToastUtils.init(getApplication());

        ToastUtils.show("我是自定义Toast");

    }

    public void toast7(View view) {
        ToastUtils.show(ToastUtils.isNotificationEnabled(this));
    }
}
