package demo.dui.zy.com.duiwakeupdemo;

import android.util.Log;

import com.aispeech.AIError;
import com.aispeech.common.AIConstant;
import com.aispeech.export.engines.AIWakeupEngine;
import com.aispeech.export.listeners.AIWakeupListener;

public class WakeUpManager {

    private static final String TAG = "WakeUpManager";

    private AIWakeupEngine mEngine;
    private ASRManager asrManager;

    WakeUpManager(){
        init();
    }


    private void init(){
        if (!AIWakeupEngine.checkLibValid()) {
            Log.d(TAG,"so加载失败");
            //mToast.show();
        } else {
            mEngine = AIWakeupEngine.createInstance();
            mEngine.setWakeupWord(new String[]{"ni hao tian ji","ni hao xiao fang"}, new String[]{"0.1","0.1"});
            mEngine.setResBin("wakeup.bin");
            //mEngine.setResBinPath("/sdcard/aispeech/wakeup1.bin");//设置唤醒资源的绝对路径,包含文件名。默认在assets目录下，无需配置
            mEngine.init(new AISpeechListenerImpl());
        }

        asrManager = new ASRManager();
    }


    public void start(){
        mEngine.start();
    }

    public void stop(){
        if (mEngine != null) {
            mEngine.stop();
        }
    }

    public void destroy() {
        if (mEngine != null) {
            mEngine.stop();
            mEngine.destroy();
            mEngine = null;
        }
    }

    private class AISpeechListenerImpl implements AIWakeupListener {

        @Override
        public void onError(AIError error) {
            //showTip(error.toString());
            //error.
            Log.d(TAG,"onError:"+error.toString());
        }

        @Override
        public void onInit(final int status) {
            Log.i(TAG, "Init result " + status);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (status == AIConstant.OPT_SUCCESS) {
//                        resultText.append("初始化成功!");
//                        btnStart.setEnabled(true);
//                        btnStop.setEnabled(true);
//                    } else {
//                        resultText.setText("初始化失败!code:" + status);
//                    }
//                }
//            });

        }


        @Override
        public void onWakeup(String recordId, final double confidence, final String wakeupWord) {
            Log.d(TAG,"wakeup foreground");
            Log.d(TAG,"wakeup foreground:"+"唤醒成功  wakeupWord = " + wakeupWord + "  confidence = " + confidence + "\n");
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    resultText.append("\n唤醒成功  wakeupWord = " + wakeupWord + "  confidence = " + confidence
//                            + "\n");
//                }
//            });
            //在这里启动其他引擎，比如tts或者识别
            asrManager.start();

        }

        @Override
        public void onReadyForSpeech() {
            Log.d(TAG, "onReadyForSpeech: ");
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            Log.d(TAG, "onBufferReceived: "+buffer.length );
        }

        @Override
        public void onWakeupEngineStopped() {
            mEngine.start();//在这里启动下一轮唤醒
        }

    }
}
