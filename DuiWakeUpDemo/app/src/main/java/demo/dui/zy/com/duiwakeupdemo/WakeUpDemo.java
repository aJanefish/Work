package demo.dui.zy.com.duiwakeupdemo;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aispeech.AIError;
import com.aispeech.common.AIConstant;
import com.aispeech.export.engines.AIWakeupEngine;
import com.aispeech.export.listeners.AIWakeupListener;

public class WakeUpDemo extends AppCompatActivity {

    private String TAG = "WakeUpDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wake_up_demo);
    }

    public void start(View view) {
        if (!AIWakeupEngine.checkLibValid()) {

            Log.d(TAG,"so加载失败");
        } else {
            AIWakeupEngine mEngine = AIWakeupEngine.createInstance();
            mEngine.setWakeupWord(new String[]{"ni hao tian ji"}, new String[]{"0.1"});
            mEngine.setResBin("wakeup.bin");
            //mEngine.setResBinPath("/sdcard/aispeech/wakeup1.bin");//设置唤醒资源的绝对路径,包含文件名。默认在assets目录下，无需配置
            AISpeechListenerImpl ai = new AISpeechListenerImpl(mEngine);
            mEngine.init(ai);
        }
    }

    private class AISpeechListenerImpl implements AIWakeupListener {

        AIWakeupEngine mEngine;

        public AISpeechListenerImpl(AIWakeupEngine mEngine) {
            this.mEngine = mEngine;
        }

        @Override
        public void onError(AIError error) {
            Log.d(TAG,error.toString());
        }

        @Override
        public void onInit(final int status) {
            Log.i(TAG, "Init result " + status);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (status == AIConstant.OPT_SUCCESS) {


                        Log.i(TAG, "Init result 初始化成功:"+mEngine );

                        mEngine.start();

                    } else {
                        Log.i(TAG, "初始化失败!code:" + status);
                    }
                }
            });

        }


        @Override
        public void onWakeup(String recordId, final double confidence, final String wakeupWord) {
            Log.d(TAG,"wakeup:"+"唤醒成功  wakeupWord = " + wakeupWord + "  confidence = " + confidence);
            //在这里启动其他引擎，比如tts或者识别
        }

        @Override
        public void onReadyForSpeech() {
            Log.d(TAG, "onReadyForSpeech: ");
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            //Log.d(TAG, "onBufferReceived: "+buffer.length );
        }

        @Override
        public void onWakeupEngineStopped() {
            Log.d(TAG, "onWakeupEngineStopped: "+Thread.currentThread().getName());
            mEngine.destroy();
            //start(null);
            new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    start(null);
                }
            },0);

        }
    }
}
