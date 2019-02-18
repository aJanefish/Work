package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.utils.TranslateUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class GoogleTranslateActivity extends AppCompatActivity {

    private EditText et;
    private String TAG = "GoogleTranslateActivity";
    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_translate);
        initView();
    }

    private void initView() {
        et = findViewById(R.id.activity_google_translate_et);
        show = findViewById(R.id.activity_google_translate_show);
    }

    public void activity_google_translate_ft(View view) {
        translate("zh-tw");
    }

    public void activity_google_translate_english(View view) {
        translate("en");
    }

    public void activity_google_translate_por(View view) {
        translate("pt");
    }

    private void translate(String language){
        String content = et.getText().toString().trim();
        show(content);
        TranslateUtil.translate(language, content, new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG,""+e);
                show(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content1 = response.body().string();
                Log.d(TAG,""+response);
                Log.d(TAG,""+content1);
                //show(response.toString());
                show(content1);

                String result = "";
                // 处理返回结果，拼接
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(content1).getJSONArray(0);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        result += jsonArray.getJSONArray(i).getString(0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                show("\n"+result);

            }
        });
    }
    private void show(final String content){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                show.append(content+"\n");
            }
        });
    }
}
