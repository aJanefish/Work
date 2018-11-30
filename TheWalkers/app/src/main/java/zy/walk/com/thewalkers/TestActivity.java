package zy.walk.com.thewalkers;

import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.newwork.Main2Activity;
import zy.walk.com.thewalkers.newwork.OkhttpUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private TextView test_text_view;

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
