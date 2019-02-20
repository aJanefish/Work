package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.R;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

public class AndroidDateActivity extends AppCompatActivity {

    private String TAG = "AndroidDateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }



    public void test1(View view) {
        SparseArray<String> sparseArray =new SparseArray();
        sparseArray.append(1,"A");
        sparseArray.put(2,"B");
        sparseArray.delete(2);
        Log.d(TAG,""+sparseArray);
    }
}
