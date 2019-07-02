package demo.okhttp.zy.com.okhttpdemo.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.zy.viewinject.ViewField;
import com.zy.viewinject.ViewMethod;
import com.zy.viewinject.ZYViewUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import demo.okhttp.zy.com.okhttpdemo.R;
import demo.okhttp.zy.com.okhttpdemo.log.MyLog;
import demo.okhttp.zy.com.okhttpdemo.presenter.OkHttpPresenter;
import demo.okhttp.zy.com.okhttpdemo.view.IOkhttpView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static demo.okhttp.zy.com.okhttpdemo.http.OkhttpUtils.JSON;

public class OkHttpDemoActivity extends AppCompatActivity implements IOkhttpView {


    private static final String TAG = "OkHttpDemoActivity";
    private OkHttpPresenter httpPresenter;
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_demo);
        ZYViewUtils.register(this);
        httpPresenter = new OkHttpPresenter(this);
        init();
    }

    private void init() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return null;
            }
        });

        //builder.sslSocketFactory()


        okHttpClient = builder.build();
    }


    @ViewMethod(getId = R.id.activity_ok_http_demo_button_get)
    private void okhttp_get(View view) {
        new Thread(
                new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void run() {

                        OkHttpClient client = new OkHttpClient();

                        final Request request = new Request.Builder()
                                .url("https://www.baidu.com/")
                                .build();

                        Call call = client.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                MyLog.d(TAG, "IOException:" + e);

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                MyLog.d(TAG, "response:" + response);
                                Headers headers = response.request().headers();
                                Headers headers1 = response.headers();
                                MyLog.d(TAG, "request headers:" + headers);
                                MyLog.d(TAG, "response headers1:" + headers1);


                                String body = response.body().string();
                                MyLog.d(TAG, "body:" + body);

                            }
                        });

                    }
                }
        ).start();
    }

    @ViewMethod(getId = R.id.activity_ok_http_demo_button_post)
    private void okhttp_post(View view) {
        new Thread(
                new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void run() {

                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        //fastJson将对象转成JSON字符串
                        JSONObject json = new JSONObject();
                        try {
                            json.put("name", "zhangyu");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // 请求body
                        RequestBody body = RequestBody.create(JSON, json.toString());
                        //请求header的添加
                        Headers headers = new Headers.Builder().add("test", "12")
                                .add("test1", "456").build();
                        //请求组合创建
                        Request request = new Request.Builder()
                                .url("https://free-api.heweather.com/s6/air/now?location=beijing&key=f464c53cb02240a194640685ee425116")
                                //.post(body)
                                .headers(headers)
                                .build();
                        //发起请求
                        OkHttpClient client = new OkHttpClient();

                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) {
                                //获得返回，并使用FastJson将Json字符串存储在JavaBean对象中
                                MyLog.d(TAG, "response:" + response);
                                try {
                                    MyLog.d(TAG, "response:" + response.body().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });


                    }
                }
        ).start();
    }

}
