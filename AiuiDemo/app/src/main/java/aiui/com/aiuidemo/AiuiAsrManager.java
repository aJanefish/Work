package aiui.com.aiuidemo;

import android.content.Context;
import android.util.Log;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;

public class AiuiAsrManager {

    private Context context;

    private AsrTask.AsrTaskListener mAsrTaskListener;
    private AsrTask mAsrTask;
    private static final String TAG = "AiuiAsrManager";

    public AiuiAsrManager(Context context) {
        this.context = context;

        mAsrTaskListener = new AsrTask.AsrTaskListener() {
            @Override
            public void onBeginOfSpeech() {
                Log.d(TAG,"onBeginOfSpeech");
            }

            @Override
            public void onEndOfSpeech() {
                Log.d(TAG,"onEndOfSpeech");
            }

            @Override
            public void onResult(RecognizerResult results, boolean isLast) {
                Log.d(TAG,"onResult:"+results.getResultString());
            }

            @Override
            public void onError(SpeechError error) {
                Log.d(TAG,"onError");
                Log.d(TAG,"onError-" + error.getPlainDescription(true));
                Log.d(TAG,"onError-" + error.getPlainDescription(false));
            }

            @Override
            public void onDestroy(AsrTask asrTask) {
                //移除
                canListener = true;
                mAsrTask = null;
                Log.d(TAG,"onDestroy");
            }
        };
    }
    boolean canListener = true;

    public void startListening() {

        if(canListener){
            canListener = false;
            mAsrTask = new AsrTask(context, mAsrTaskListener);
        }else {
            Log.d(TAG,"startListening canListener:"+canListener);
        }
    }

    public void stopListening() {
        if(mAsrTask != null){
            mAsrTask.onDestroy();
        }
    }
}
