package zy.walk.com.thewalkers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import zy.walk.com.thewalkers.imagesandanimations.BitmapUtils;
import zy.walk.com.thewalkers.json.JsonUtils;
import zy.walk.com.thewalkers.newwork.Main2Activity;
import zy.walk.com.thewalkers.newwork.OkhttpUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private ImageView myimage;

    int number = 1000;
    Drawable[] array;

    Bitmap bitmap[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myimage = findViewById(R.id.myimage);
        drawableTest();
        //bitmapTest();



    }

    private void bitmapTest() {
        bitmap = new Bitmap[number];

        for (int i = 0; i < number; i++)
        {
            Log.e(TAG, "测试第" + (i+1) + "张图片");
            bitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.gril);
        }
    }

    private void drawableTest() {
        array = new BitmapDrawable[number];

            for (int i = 0; i < number; i++) {
                Log.e(TAG, "测试第" + (i + 1) + "张图片");
                array[i] = getApplicationContext().getResources().getDrawable(R.drawable.gril);
            }

        }

        public void test(View view) {

        myimage.setImageBitmap(BitmapUtils.loadbitmap(this));
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gril);
        //Log.d(TAG,""+bitmap);
        //myimage.setImageBitmap(bitmap);
    }

    public void TestNEwWORk(View view) {
        startActivity(new Intent(getApplicationContext(),Main2Activity.class));
    }

    public void JsonTest(View view) {
        JsonUtils.test1();
        JsonUtils.test2();
        JsonUtils.test3();
        JsonUtils.test4();
    }

    public void getAuxiliaryAll(View view) {
        OkhttpUtils.getAuxiliaryAll();
    }
}
