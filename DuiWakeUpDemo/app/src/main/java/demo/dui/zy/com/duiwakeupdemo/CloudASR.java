package demo.dui.zy.com.duiwakeupdemo;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.common.AIConstant;
import com.aispeech.common.JSONResultParser;
import com.aispeech.export.engines.AICloudASREngine;
import com.aispeech.export.listeners.AIASRListener;

public class CloudASR extends Activity implements View.OnClickListener {

    final String Tag = this.getClass().getName();
    AICloudASREngine mEngine;

    TextView resultText;
    Button btnStart;
    Button btnStop;
    Toast mToast;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_wakeup);
        resultText = (TextView) findViewById(R.id.text_result);
        resultText.setText("正在加载资源...");
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStop = (Button) findViewById(R.id.btn_end);

        btnStart.setEnabled(false);
        btnStop.setEnabled(false);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        mToast = Toast.makeText(this, "", Toast.LENGTH_LONG);
        if (!AICloudASREngine.checkLibValid()){
            mToast.setText("so加载失败");
            mToast.show();
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

    @Override
    public void onClick(View v) {
        if (v == btnStart) {
            mEngine.start();
        } else if (v == btnStop) {
            mEngine.cancel();
            resultText.setText("已取消");
        }
    }



    private class AICloudASRListenerImpl implements AIASRListener {

        @Override
        public void onReadyForSpeech() {
            resultText.setText("请说话...");
        }

        @Override
        public void onBeginningOfSpeech() {
            resultText.setText("检测到说话");
        }

        @Override
        public void onEndOfSpeech() {
            resultText.append("检测到语音停止，开始识别...\n");
        }

        @Override
        public void onRmsChanged(float rmsdB) {
            showTip("RmsDB = " + rmsdB);
        }

        @Override
        public void onError(AIError error) {
            Log.e(Tag, "error:" + error.toString());
            resultText.setText(error.toString());
        }

        @Override
        public void onResults(AIResult results) {
            if (results.isLast()) {
                if (results.getResultType() == AIConstant.AIENGINE_MESSAGE_TYPE_JSON) {
                    Log.i(Tag, "result JSON = " + results.getResultObject().toString());
                    // 可以使用JSONResultParser来解析识别结果
                    // 结果按概率由大到小排序
                    JSONResultParser parser = new JSONResultParser(results.getResultObject()
                            .toString());
                    resultText.append("识别结果为 :  " + parser.getText() + "\n");
                    resultText.append("识别结果为 :  " + results.getResultObject().toString());
                }
            }
        }

        @Override
        public void onInit(int status) {
            Log.i(Tag, "Init result " + status);
            if (status == AIConstant.OPT_SUCCESS) {
                resultText.setText("初始化成功!");
                btnStart.setEnabled(true);
                btnStop.setEnabled(true);
            } else {
                resultText.setText("初始化失败!code:" + status);
            }
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            // TODO Auto-generated method stub

        }

    }

    private void showTip(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mToast.setText(str);
                mToast.show();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mEngine != null) {
            mEngine.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mEngine != null) {
            mEngine.destroy();
            mEngine = null;
        }
    }

}

