package demo.dui.zy.com.duiwakeupdemo;

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

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private AIWakeupEngine mEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
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
}
