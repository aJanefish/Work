package demo.dui.zy.com.duiwakeupdemo;

import android.util.Log;

import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.common.AIConstant;
import com.aispeech.common.JSONResultParser;
import com.aispeech.export.engines.AICloudASREngine;
import com.aispeech.export.listeners.AIASRListener;

public class ASRManager {
    private static final String TAG = "ASRManager";

    private AICloudASREngine mEngine;

    ASRManager(){
        init();
    }

    private void init() {

        if (!AICloudASREngine.checkLibValid()){
           // mToast.setText("so加载失败");
            //mToast.show();
            Log.e(TAG,"so加载失败");
        } else {
            mEngine = AICloudASREngine.createInstance();
            mEngine.setVadResource("vad.bin");//配置assets目录下的本地vad资源
            //mEngine.setVadResBinPath("/sdcard/aispeech/vad1.bin");//自定义vad资源加载目录。默认在assets目录下，无需配置
            mEngine.setLocalVadEnable(true);//设置是否启用本地vad,默认开启为true
            mEngine.setPauseTime(300);//设置本地VAD右边界,默认为300ms
            mEngine.setServer("ws://asr.dui.ai/runtime/v2/recognize");//设置服务器地址，默认不用设置
            mEngine.setEnablePunctuation(false);//设置是否启用标点符号识别,默认为false关闭
            mEngine.setResourceType("comm");//设置识别引擎的资源类型,默认为comm
            mEngine.init(new AICloudASRListenerImpl());
            mEngine.setNoSpeechTimeOut(0);
            //mEngine.setMaxSpeechTimeS(0);//音频最大录音时长
            mEngine.setCloudVadEnable(true);//设置是否开启服务端的vad功能,默认开启为true
            mEngine.setSaveAudioPath("/sdcard/aispeech");//保存的音频路径,格式为.ogg
        }
    }
    public void start(){
        mEngine.start();
    }

    public void cancel(){
        mEngine.cancel();
    }
    public void destroy(){
        mEngine.destroy();
    }



    private class AICloudASRListenerImpl implements AIASRListener {

        @Override
        public void onReadyForSpeech() {
            //resultText.setText("请说话...");
            Log.e(TAG,"onReadyForSpeech");
        }

        @Override
        public void onBeginningOfSpeech() {
            //resultText.setText("检测到说话");
            Log.e(TAG,"onBeginningOfSpeech");
        }

        @Override
        public void onEndOfSpeech() {
            Log.e(TAG,"onEndOfSpeech");
            //resultText.append("检测到语音停止，开始识别...\n");
        }

        @Override
        public void onRmsChanged(float rmsdB) {
            //语音大小
            Log.e(TAG,"rmsdB:"+rmsdB);
                    //showTip("RmsDB = " + rmsdB);
        }

        @Override
        public void onError(AIError error) {
            Log.e(TAG, "error:" + error.toString());
            //resultText.setText(error.toString());
        }

        @Override
        public void onResults(AIResult results) {
            if (results.isLast()) {
                if (results.getResultType() == AIConstant.AIENGINE_MESSAGE_TYPE_JSON) {
                    Log.i(TAG, "result JSON = " + results.getResultObject().toString());
                    // 可以使用JSONResultParser来解析识别结果
                    // 结果按概率由大到小排序
                    JSONResultParser parser = new JSONResultParser(results.getResultObject()
                            .toString());
                    //resultText.append("识别结果为 :  " + parser.getText() + "\n");
                    //resultText.append("识别结果为 :  " + results.getResultObject().toString());
                    Log.i(TAG,"识别结果为 :  " + parser.getText());
                            Log.i(TAG,"识别结果为 :  " + results.getResultObject().toString());

                }
            }
        }

        @Override
        public void onInit(int status) {
            Log.i(TAG, "Init result " + status);
            if (status == AIConstant.OPT_SUCCESS) {
               // resultText.setText("初始化成功!");
                Log.i(TAG, "初始化成功" );


                //btnStart.setEnabled(true);
                //btnStop.setEnabled(true);
            } else {

                Log.i(TAG, "初始化失败!code" +status);
                //resultText.setText("初始化失败!code:" + status);
            }
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            // TODO Auto-generated method stub

        }

    }


}
