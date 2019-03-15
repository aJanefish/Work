package zy.walk.com.thewalkers;

import androidx.core.app.ActivityCompat;
import zy.walk.com.thewalkers.activity.BaseActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;

import java.util.List;

/**
 * Android M对权限管理系统进行了改版，之前我们的App需要权限，只需在manifest中申明即可，用户安装后，一切申明的权限都可来去自如的使用。
 * 但是Android M把权限管理做了加强处理，在manifest申明了，在使用到相关功能时，还需重新授权方可使用。
 * 当然，不是所有权限都需重新授权，所以就把这些需要重新授权方可使用的权限称之为运行时权限
 *
 * *Android 6.0及其以上版本上，系统在APP安装时授权所有普通权限，危险权限需要在使用时动态让用户授权
 *
 * *危险权限在AndroidManifest.xml文件中也必须申明，否则动态申请会失败
 *
 *
 *
 *
 * */

public class PermissionActivity extends BaseActivity {

    /**
     * 授权了
     */
    public static final int PERMISSION_GRANTED = 0;

    /**
     * 拒绝了
     */
    public static final int PERMISSION_DENIED = -1;
    private String TAG = "PermissionActivity";
    private TextView permission_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    @Override
    public void initView() {
        permission_text_view = findViewById(R.id.permission_text_view);
    }

    @Override
    public void initDate() {

    }

    @Override
    protected String getLog() {
        return TAG;
    }

    private void show(String values){
        permission_text_view.append(values+"\n");
    }


    //传统方式查询权限
    public void checkIntent(View view) {
        if (ActivityCompat.checkSelfPermission(this,"android.permission.INTERNET" ) == PackageManager.PERMISSION_GRANTED){
            ToastUtils.show("true");
            show("传统方式查询权限:true");
        }else {
            ToastUtils.show("false");
            show("传统方式查询权限:false");
        }
    }

    //打开apk的应用设置界面
    public void openSettings(View view) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", getPackageName(), null));
        Log.d(TAG,""+intent);
        show (""+intent);
        startActivity(intent);
    }

    //请求运行时权限
    public void requestPermission(View view) {

        String[] pers = new String[]{
                Permission.Group.STORAGE[0],
                Permission.Group.STORAGE[1],
                Permission.Group.CALENDAR[0],
                Permission.Group.CALENDAR[1],
                Permission.Group.CONTACTS[0],
                Permission.Group.CONTACTS[1],
                Permission.Group.CONTACTS[2],
                Permission.Group.LOCATION[0],
                Permission.Group.LOCATION[1],
                Permission.CAMERA,
                Permission.SEND_SMS,
                Permission.RECEIVE_SMS,
                Permission.READ_SMS,
                Permission.RECEIVE_WAP_PUSH,
                Permission.RECEIVE_MMS,
                Permission.BODY_SENSORS,
                Permission.RECORD_AUDIO,
        };



        XXPermissions.with(this)
                //.constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                //.permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES) //支持请求6.0悬浮窗权限8.0请求安装权限
                .permission(pers) //不指定权限则自动获取清单中的危险权限
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        show("hasPermission isAll:"+isAll);
                        for(String s:granted){
                            show("hasPermission:"+s);
                        }
                        if (isAll) {
                            ToastUtils.show("获取权限成功");
                        }else {
                            ToastUtils.show("获取权限成功，部分权限未正常授予");
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        show("noPermission quick:"+quick);
                        for(String s:denied){
                            show("noPermission:"+s);
                        }
                        if(quick) {
                            ToastUtils.show("被永久拒绝授权，请手动授予权限");
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.gotoPermissionSettings(PermissionActivity.this);
                        }else {
                            ToastUtils.show("获取权限失败");
                        }
                    }
                });


    }


    public void cancel(View view) {
        permission_text_view.setText("");
    }

    public void isHasPermissionD(View view) {

        if (XXPermissions.isHasPermission(PermissionActivity.this, Permission.Group.STORAGE)) {
            ToastUtils.show("已经获取到权限，不需要再次申请了");
            show("已经获取到权限，不需要再次申请了");
        }else {
            ToastUtils.show("还没有获取到权限或者部分权限未授予");
            show("还没有获取到权限或者部分权限未授予");
        }
    }

    public void settings(View view) {
        show("采用集成的方式");
        XXPermissions.gotoPermissionSettings(PermissionActivity.this);
    }
}
