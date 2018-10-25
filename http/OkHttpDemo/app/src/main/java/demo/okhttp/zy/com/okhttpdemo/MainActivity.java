package demo.okhttp.zy.com.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import demo.okhttp.zy.com.okhttpdemo.application.App;
import demo.okhttp.zy.com.okhttpdemo.event.ClientEvent;
import demo.okhttp.zy.com.okhttpdemo.event.LongClientEvent;
import demo.okhttp.zy.com.okhttpdemo.event.LongServerEvent;
import demo.okhttp.zy.com.okhttpdemo.event.ServerEvent;
import demo.okhttp.zy.com.okhttpdemo.http.OkhttpUtils;

public class MainActivity extends AppCompatActivity {

    private App app;
    private TextView serverTestShow;
    private OkhttpUtils okhttpUtils;

    private TextView clientTestShow;
    private StringBuilder serverShowStringBuilder = new StringBuilder("");
    private StringBuilder clientShowStringBuilder = new StringBuilder("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        app = (App)getApplication();
        okhttpUtils = new OkhttpUtils();
        initView();
    }

    private void initView() {
        serverTestShow = findViewById(R.id.serverTestShow);
        clientTestShow = findViewById(R.id.clientTestShow);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServerEvent(ServerEvent event) {
        serverShowStringBuilder.append(event.clientMessage+"\n");
        serverTestShow.setText(serverShowStringBuilder.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLongServerEvent(LongServerEvent event) {
        serverShowStringBuilder.append(event.clientMessage+"\n");
        serverTestShow.setText(serverShowStringBuilder.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onServerEvent(ClientEvent event) {
        clientShowStringBuilder.append(event.response+"\n");
        clientTestShow.setText(clientShowStringBuilder.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLongClientEvent(LongClientEvent event) {
        clientShowStringBuilder.append(event.response+"\n");
        clientTestShow.setText(clientShowStringBuilder.toString());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        app.getMyServerManager().stop();
    }

    public void sendJson(View view) {
        okhttpUtils.sendJson("zhangyu");
    }

    public void sendGetNull(View view) {
        okhttpUtils.sendGetNull();
    }

    public void sendGet(View view) {
        okhttpUtils.sendGet("???zhangyu???==age=100");
    }

    public void sendBaidu(View view) {
        okhttpUtils.sendBaidu();
    }

    public void sendPostString(View view) {
        okhttpUtils.sendPostString("zhangyu");
    }

    public void sendPostDiy(View view) {
        okhttpUtils.sendPostDIY("DIY");
    }

    public void sendPostFile(View view) {
        okhttpUtils.sendPostFile();
    }

    public void sendPostForm(View view) {
        okhttpUtils.sendPostForm();
    }

    public void sendPostMultipartBody(View view) {
        okhttpUtils.sendMultipartBody();
    }

    public void sendRequsetJson(View view) {
        okhttpUtils.requestJson();
    }

    public void longSendToServer(View view) {
        app.getMyWsManager().send("Hello Server!");
    }

    public void longSendToClient(View view) {
        app.getMyAsyncHttpServer().sendWs("Hello Client!");
    }
}
