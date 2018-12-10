package aiui.com.aiuidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AsrTestActivity extends AppCompatActivity {

    private TextView activity_asr_test_show;

    private AiuiAsrManager aiuiAsrManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asr_test);
        initView();
        aiuiAsrManager = new AiuiAsrManager(getApplicationContext());

    }

    private void initView() {
        activity_asr_test_show = findViewById(R.id.activity_asr_test_show);
    }

    private void show(String show){
        activity_asr_test_show.append(show+"\n");
    }


    public void activity_asr_test_start(View view) {
        aiuiAsrManager.startListening();
    }

    public void activity_asr_test_stop(View view) {
        aiuiAsrManager.stopListening();
    }

}
