package aiui.com.aiuidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();

    }

    //请求运行时权限
    public void requestPermission() {

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
                        if (isAll) {
                            ToastUtils.show("获取权限成功");
                        }else {
                            ToastUtils.show("获取权限成功，部分权限未正常授予");
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                        if(quick) {
                            ToastUtils.show("被永久拒绝授权，请手动授予权限");
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.gotoPermissionSettings(MainActivity.this);
                        }else {
                            ToastUtils.show("获取权限失败");
                        }
                    }
                });
    }


    public void aiuiAsr(View view) {
        startActivity(new Intent(MainActivity.this,AsrActivity.class));
    }

    public void aiuiAsrTest(View view) {
        startActivity(new Intent(MainActivity.this,AsrTestActivity.class));
    }
}
