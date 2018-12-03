package demo.okhttp.zy.com.okhttpdemo.http;

import android.content.Context;

import com.rabtman.wsmanager.WsManager;
import com.rabtman.wsmanager.listener.WsStatusListener;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import demo.okhttp.zy.com.okhttpdemo.event.LongClientEvent;
import demo.okhttp.zy.com.okhttpdemo.log.MyLog;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okio.ByteString;

/**
 * client
 * 长连接
 * */
public class MyWsManager {

    private static final String TAG = "MyWsManager";

    private WsManager wsManager;

    public MyWsManager(Context context) {
        init(context);
    }

    private void init(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .pingInterval(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();


        wsManager = new WsManager.Builder(context)
                //.wsUrl("ws://localhost:8888/demo/")
                .wsUrl("http://192.168.201.80:8888/demo/")
                .needReconnect(true)
                .client(okHttpClient)
                .build();


        wsManager.setWsStatusListener(new WsStatusListener() {
            @Override
            public void onOpen(Response response) {
                super.onOpen(response);
                MyLog.d(TAG,"onOpen"+response.toString());
                EventBus.getDefault().post(new LongClientEvent(response.toString()));
            }

            @Override
            public void onMessage(String text) {
                super.onMessage(text);
                MyLog.d(TAG,"onMessage String:"+text);
                EventBus.getDefault().post(new LongClientEvent("text:"+text));
            }

            @Override
            public void onMessage(ByteString bytes) {
                super.onMessage(bytes);
                MyLog.d(TAG,"onMessage ByteString:"+bytes);
                EventBus.getDefault().post(new LongClientEvent("bytes:"+bytes));
            }

            @Override
            public void onReconnect() {
                super.onReconnect();
                MyLog.d(TAG,"onReconnect");
            }

            @Override
            public void onClosing(int code, String reason) {
                super.onClosing(code, reason);
                MyLog.d(TAG,"onClosing code:"+code+" reason:"+code);
            }

            @Override
            public void onClosed(int code, String reason) {
                super.onClosed(code, reason);
                MyLog.d(TAG,"onClosed code"+code+" reason:"+reason);
            }

            @Override
            public void onFailure(Throwable t, Response response) {
                super.onFailure(t, response);
                if(response == null){
                    MyLog.d(TAG,"onFailure==null");
                }else {
                    MyLog.d(TAG,"onFailure:"+response +" t:"+ t);
                    MyLog.d(TAG,"onFailure:"+response.request());
                }

            }
        });
    }

    public  void start(){
        wsManager.startConnect();
    }
    public void send(String values){
        wsManager.sendMessage(values);
    }
    public void stop(){
        wsManager.stopConnect();
    }
}
