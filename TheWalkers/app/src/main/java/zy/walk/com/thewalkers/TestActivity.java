package zy.walk.com.thewalkers;

import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.newwork.Main2Activity;
import zy.walk.com.thewalkers.newwork.OkhttpUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private TextView test_text_view;
    private String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        test_text_view = findViewById(R.id.test_text_view);
    }

    public void Photo(View view) {
        startActivity(new Intent(TestActivity.this,Main2Activity.class));
    }

    public void add(View view) {


        add("双方的护手霜"+System.currentTimeMillis());
    }



    List<ShowToastEvent> list = new ArrayList<>();
    private void add(String values) {

        list.add(new ShowToastEvent(values));
        if(list.size() > 3){
            list.remove(0);
        }

        test_text_view.setText(list.toString());
    }

    public void Cookie(View view) {
        OkhttpUtils.getCookie();
    }

    public void getFaceList(View view) {
        OkhttpUtils.getFace();
        Log.d(TAG,"getFaceList");

        String str = "测试字符转换 hello word"; //默认环境，已是UTF-8编码
        try {
            String strGBK = URLEncoder.encode(str, "GBK");
            System.out.println(strGBK);
            String strUTF8 = URLDecoder.decode(strGBK, "UTF-8");
            System.out.println(strUTF8);
         Log.d(TAG,strGBK+" , "+strUTF8);
        } catch (Exception e) {
            Log.d(TAG,"Exception, "+e);
            e.printStackTrace();
        }
    }

    public void sendFaceError(View view) {
        OkhttpUtils.sendFaceError();
    }

    public void getRobotCode(View view) {
        OkhttpUtils.getRobotCode();
    }

    private class ShowToastEvent {
        String values;

        public ShowToastEvent(String values) {
            this.values = values;
        }

        @Override
        public String toString() {
            return values + '\n';
        }
    }
}
