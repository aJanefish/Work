package demo.dui.zy.com.duiwakeupdemo;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aispeech.AIError;
import com.aispeech.DUILiteSDK;
import com.aispeech.export.engines.AIWakeupEngine;
import com.aispeech.export.listeners.AIWakeupListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private AIWakeupEngine mEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPermission();
        //init();
    }

    private void initPermission(){
        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
        permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "手机状态", R.drawable.permission_ic_phone));
        permissionItems.add(new PermissionItem(Manifest.permission.INTERNET, "网络", R.drawable.permission_ic_phone));

        permissionItems.add(new PermissionItem(Manifest.permission.RECORD_AUDIO, "网络", R.drawable.permission_ic_storage));

        permissionItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读内存", R.drawable.permission_ic_storage));

        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写内存", R.drawable.permission_ic_storage));

        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_NETWORK_STATE, "网络", R.drawable.permission_ic_phone));

        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_WIFI_STATE, "WIFI", R.drawable.permission_ic_phone));

        permissionItems.add(new PermissionItem(Manifest.permission.MODIFY_AUDIO_SETTINGS, "MODIFY_AUDIO_SETTINGS", R.drawable.permission_ic_storage));



        HiPermission.create(MainActivity.this)
                .title(getString(R.string.permission_cus_title))
                .permissions(permissionItems)
                .msg(getString(R.string.permission_cus_msg))
                .animStyle(R.style.PermissionAnimScale)
                .style(R.style.PermissionDefaultBlueStyle)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        Log.i(TAG, "onClose");
                        //showToast(getString(R.string.permission_on_close));
                    }

                    @Override
                    public void onFinish() {
                        Log.i(TAG, "onFinish");
                        init();
                        //showToast(getString(R.string.permission_completed));
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                        Log.i(TAG, "onDeny");
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {
                        Log.i(TAG, "onGuarantee");
                    }
                });
    }

    private void init() {

        /**
         * 初始化授权信息
         * @param context 上下文
         * @param apikey 从DUI平台产品里获取的ApiKey
         * @param productId 产品ID
         * @param listener 授权回调
         */
        DUILiteSDK.openLog();//须在init之前调用
        // 同时会保存日志文件在/sdcard/duilite/DUILite_SDK.log

        // taishi  d77972ac76cdd77972ac76cd5bd2d9a8
        // xiaomi  79f393bf7e8179f393bf7e815bd52224
        DUILiteSDK.init(getApplicationContext(),"d77972ac76cdd77972ac76cd5bd2d9a8","278576160",new DUILiteSDK.InitListener() {
            @Override
            public void success() {
                Log.d(TAG, "授权成功! ");
            }

            @Override
            public void error(String errorCode,String errorInfo) {
                Log.d(TAG, "授权失败, errorcode: "+errorCode+",errorInfo:"+errorInfo);
            }
        });
    }

    public void LocalWakeup(View view) {
        startActivity(new Intent(MainActivity.this,LocalWakeup.class));

    }


    public void LocalWakeupCustom(View view) {
        startActivity(new Intent(MainActivity.this,LocalWakeupCustom.class));
    }

    public void CloudASR(View view) {
        startActivity(new Intent(MainActivity.this,CloudASR.class));
    }

    public void CloudASRMultiResult(View view) {
        startActivity(new Intent(MainActivity.this,CloudASRMultiResult.class));
    }

    public void CloudASRCustomBatch(View view) {
        startActivity(new Intent(MainActivity.this,CloudASRCustomBatch.class));
    }

    public void WakeUpAsr(View view) {
        startActivity(new Intent(MainActivity.this,WakeUpAsrActivity.class));
    }

    public void LocalWakeupDiy(View view) {
        startActivity(new Intent(MainActivity.this,WakeUpDemo.class));

    }
}
