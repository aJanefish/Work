package demo.okhttp.zy.com.okhttpdemo.http;

import android.support.annotation.Nullable;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import demo.okhttp.zy.com.okhttpdemo.event.ClientEvent;
import demo.okhttp.zy.com.okhttpdemo.log.MyLog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class OkhttpUtils {


    private static final String TAG = "OkhttpUtils";
    private final OkHttpClient okHttpClient;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final String URL = "http://192.168.201.112:9000/";
    //private static final String URL = "http://localhost:9000/";


    public OkhttpUtils() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        okHttpClient = builder.build();
    }




    public void requestJson(){
        Request request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                .tag("Resquest Json+aaaa")
                .build();
        newCall(request);
    }


    public void sendMultipartBody(){
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                .addFormDataPart("image", "logo-square.png",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("/storage/emulated/0/a.png")))
                .addFormDataPart("Id","zhangyu")
                .build();

        send(requestBody, "MultipartBody");
    }


    public void sendPostForm() {
        RequestBody formBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();

        send(formBody, "Form");

    }

    private void send(RequestBody body, String tag) {
        Request request = new Request.Builder()
                .url(URL)

                .tag(tag)
                .post(body)
                .build();

        newCall(request);
    }


    //post File
    public void sendPostFile() {
        File file = new File("/storage/emulated/0/a.png");
        Log.d(TAG, "sendPostFile:" + file.getPath());
        RequestBody FileRequestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, file);

        send(FileRequestBody, "File");

    }


    //post DIY
    public void sendPostDIY(final String values) {
        RequestBody DIYRequestBody = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 2; i <= 10; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }

                sink.writeUtf8(values);
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) return factor(x) + " × " + i;
                }
                return Integer.toString(n);
            }
        };
        send(DIYRequestBody, values);

    }

    //post 发送字符串
    public void sendPostString(String values) {
        RequestBody stringRequestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, values);
        Request request = new Request.Builder()
                .url(URL)
                .tag("zhangyu")
                .post(stringRequestBody)
                .build();

        newCall(request);
    }


    public void sendBaidu() {

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .tag("Hello Baidu")
                .build();

        newCall(request);
    }


    //GET 无参
    public void sendGetNull() {
        Request request = new Request.Builder()
                .url(URL)
                .build();

        newCall(request);
    }

    //GET 有参
    public void sendGet(String values) {
        Request request = new Request.Builder()
                .url(URL + values)
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

    private void newCall(final Request request) {
        Call cell = okHttpClient.newCall(request);
        cell.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyLog.d(TAG, "onFailure:" + call.toString() + " e:" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MyLog.d(TAG, "onResponse:" + call.toString());
                MyLog.d(TAG, "onResponse response:" + response);
                MyLog.d(TAG, "onResponse contentType:" + response.body().contentType());
                MyLog.d(TAG, "onResponse headers:" + response.headers());
                MyLog.d(TAG, "onResponse request:" + response.request());
                Call call1 = call;
                EventBus.getDefault().post(new ClientEvent("[" + response.toString() + "\n" + response.request() + "]"));
                MyLog.d(TAG,"charStream:"+response.body().charStream());
                //MyLog.d(TAG,"bytes:"+new String(response.body().bytes()));


                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;

                InputStream is=null;
                try {
                    is = response.body().byteStream();

                    //文件大小
                    long total = response.body().contentLength();
                    //File file = new File(url, fileName);
                    //fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        //fos.write(buf, 0, len);
                        MyLog.d(TAG,"buf:"+buf.length);
                        MyLog.d(TAG,"buf:"+new String(buf));
                    }
                    //fos.flush();
                    MyLog.d(TAG,"xxxxxxxx"+ "下载成功");
                } catch (Exception e) {
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
//                    try {
//                        if (fos != null)
//                            fos.close();
//                    } catch (IOException e) {
//                    }
                }


            }
        });
    }





}
