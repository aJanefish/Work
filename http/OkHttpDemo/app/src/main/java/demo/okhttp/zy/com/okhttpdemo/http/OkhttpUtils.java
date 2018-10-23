package demo.okhttp.zy.com.okhttpdemo.http;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import demo.okhttp.zy.com.okhttpdemo.event.ClientEvent;
import demo.okhttp.zy.com.okhttpdemo.log.MyLog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpUtils {


    private static final String TAG = "OkhttpUtils";
    private final OkHttpClient okHttpClient;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String URL = "http://192.168.201.16:9000/";

    public OkhttpUtils() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        okHttpClient = builder.build();
    }


    //GET 无参
    public void sendGetNull(){
        Request request = new Request.Builder()
                .url(URL)
                .build();

        newCall(request);
    }

    //GET 有参
    public void sendGet(String values){
        Request request = new Request.Builder()
                .url(URL+values)
                .build();

        newCall(request);
    }

    //post 发送JSON
    public void sendJson(final String valus) {

        JSONObject body = new JSONObject();
        try {
            body.put("data", valus);
        } catch (Exception e) {
            MyLog.i(TAG, "sendJson body e: " + e.toString());
        }
        MyLog.i(TAG, "body e: " + body.toString());
        RequestBody requestBody = RequestBody.create(JSON, body.toString());
        Request request = new Request.Builder().url(URL).post(requestBody).build();

        newCall(request);
    }

    private void newCall(Request request){
        Call cell = okHttpClient.newCall(request);
        cell.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyLog.d(TAG, "onFailure:" + call.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MyLog.d(TAG, "onResponse:" + call.toString());
                MyLog.d(TAG, "onResponse response:" + response);
                MyLog.d(TAG, "onResponse response:" + response.body().contentType());
                EventBus.getDefault().post(new ClientEvent(response.toString()));
            }
        });
    }


}
