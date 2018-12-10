package aiui.com.aiuidemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;

public class AsrTask {

    private SpeechRecognizer mSpeechRecognizer;
    private Context mContext;
    private static final String TAG = "AsrTask";
    private long startTime;

    private AsrTaskListener mAsrTaskListener;

    public AsrTask(Context context,AsrTaskListener asrTaskListener) {
        this.mContext = context;
        this.mAsrTaskListener = asrTaskListener;
        Log.d(TAG,"this:"+this);
        // 使用SpeechRecognizer对象，可根据回调消息自定义界面；
        mSpeechRecognizer = SpeechRecognizer.createRecognizer(mContext, mInitListener);
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败，错误码：" + code);
            } else {
                // 设置参数
                //setParam("en-us");
                setParam("zh-cn");

                // 不显示听写对话框
                int ret = mSpeechRecognizer.startListening(mRecognizerListener);
                if (ret != ErrorCode.SUCCESS) {
                    showTip("听写失败,错误码：" + ret);
                } else {
                    showTip("请开始说话");
                }
            }
        }
    };


    /**
     * 語音聽寫参数设置
     */
    private void setParam(String language) {
        // 清空参数
        mSpeechRecognizer.setParameter(SpeechConstant.PARAMS, null);

        // 设置听写引擎
        mSpeechRecognizer.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置返回结果格式
        mSpeechRecognizer.setParameter(SpeechConstant.RESULT_TYPE, "json");

        // 设置语言， lag可選的值有：普通话(mandarin)、英语(en_us)、粤语(cantonese)、四川话(lmz)、河南话(henanese)
        if ("en-us".equals(language)) {
            mSpeechRecognizer.setParameter(SpeechConstant.LANGUAGE, "en_us");
        } else if ("zh-cn".equals(language)) {
            mSpeechRecognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            mSpeechRecognizer.setParameter(SpeechConstant.ACCENT, language);
        } else if ("cantonese".equals(language)) {
            mSpeechRecognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            mSpeechRecognizer.setParameter(SpeechConstant.ACCENT, language);
        }

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mSpeechRecognizer.setParameter(SpeechConstant.VAD_BOS, "6000");

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mSpeechRecognizer.setParameter(SpeechConstant.VAD_EOS, "1000");

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mSpeechRecognizer.setParameter(SpeechConstant.ASR_PTT, "1");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mSpeechRecognizer.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mSpeechRecognizer.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/Android/data/mo.singou.chatbot.android/files/iat.wav");
    }

    /**
     * 参数设置
     *
     * @return
     */
    public void setParam() {
        // 清空参数
        mSpeechRecognizer.setParameter(SpeechConstant.PARAMS, null);
        //        <item>mandarin</item>
        //        <item>cantonese</item>
        //        <item>en_us</item>


        // 设置引擎
        mSpeechRecognizer.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置返回结果格式
        mSpeechRecognizer.setParameter(SpeechConstant.RESULT_TYPE, "json");

        // 设置语言
        //mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mSpeechRecognizer.setParameter(SpeechConstant.LANGUAGE, "en_us");
        // 设置语言区域
        mSpeechRecognizer.setParameter(SpeechConstant.ACCENT, "en_us");


        // 设置返回结果格式
        mSpeechRecognizer.setParameter(SpeechConstant.RESULT_TYPE, "json");

        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        //mIat.setParameter(SpeechConstant.VAD_BOS, "10000");

        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mSpeechRecognizer.setParameter(SpeechConstant.VAD_EOS, "1000");

        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mSpeechRecognizer.setParameter(SpeechConstant.ASR_PTT, "1");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        mSpeechRecognizer.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mSpeechRecognizer.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/iat.wav");
    }

    private void showTip(String s) {
        Log.d(TAG,""+s);
    }

    /**
     * 听写监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            startTime = System.currentTimeMillis();
            showTip("onBeginOfSpeech-开始说话");
            if(mAsrTaskListener != null){
                mAsrTaskListener.onBeginOfSpeech();
            }
        }

        @Override
        public void onError(SpeechError error) {
            // Tips：
            // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
            showTip("onError-" + error.getPlainDescription(true));
            showTip("onError-" + error.getPlainDescription(false));

            if(mAsrTaskListener != null){
                mAsrTaskListener.onError(error);
            }

            finish(false);
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            long endTime = System.currentTimeMillis();

            showTip("onEndOfSpeech-结束说话-时间间隔:" + (endTime - startTime) + "ms");
            if(mAsrTaskListener != null){
                mAsrTaskListener.onEndOfSpeech();
            }

            finish(true);
        }

        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
//            mResultText.append("isLast:"+isLast+" , "+results.getResultString() + "\n");
            showTip("onResult:" + results.getResultString());
//            printResult(results);
//
//            if (isLast) {
//                //TODO 最后的结果
//                StringBuilder resultBuffer = new StringBuilder();
//                for (String key : mIatResults.keySet()) {
//                    resultBuffer.append(mIatResults.get(key));
//                }
//                String text = resultBuffer.toString();
//                if (!TextUtils.isEmpty(text)) {
//                    showTip(text);
//                }else {
//                    showTip("str == null || str.length() == 0");
//                }
//            }
            if(mAsrTaskListener != null){
                mAsrTaskListener.onResult(results,isLast);
            }

        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            //showTip("当前正在说话，音量大小：" + volume);
            //Log.d(TAG, "返回音频数据："+data.length);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };

    private void finish(boolean b) {
        onDestroy();
    }


    public void cancel(){
        mSpeechRecognizer.cancel();
    }

    public void stopListening(){
        mSpeechRecognizer.stopListening();
    }

    public void onDestroy() {

        if (null != mSpeechRecognizer) {
            // 退出时释放连接
            mSpeechRecognizer.stopListening();
            mSpeechRecognizer.cancel();
            mSpeechRecognizer.destroy();

            if(mAsrTaskListener != null){
                mAsrTaskListener.onDestroy(this);
            }

            //mAsrTaskListener = null;
            mSpeechRecognizer = null;
        }
    }

    interface AsrTaskListener{

        void onBeginOfSpeech();

        void onEndOfSpeech();

        void onResult(RecognizerResult var1, boolean var2);

        void onError(SpeechError var1);

        void onDestroy(AsrTask asrTask);
    }
}
