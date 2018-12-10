package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BitmapActivity extends AppCompatActivity {

    private TextView activity_bitmap_show;
    private int[][] mColArr;
    private ImageView activity_bitmap_image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        initView();

        initDate();
    }

    private void initDate() {
        activity_bitmap_image_view.setImageBitmap(createBitmap());
    }

    private void initView() {
        activity_bitmap_show   = findViewById(R.id.activity_bitmap_show);
        activity_bitmap_image_view   = findViewById(R.id.activity_bitmap_image_view);
    }

    private void get(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        int color_0_0 = bitmap.getPixel(0, 0);//获取第1行，第1个像素颜色
        activity_bitmap_show.append(bitmap.getWidth() + " , "+bitmap.getHeight()+"\n");
        
        mColArr = new int[bitmap.getWidth()][bitmap.getHeight()];
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                mColArr[i][j] = bitmap.getPixel(i, j);
            }
        }

    }


    //创建Bitmap
    private Bitmap createBitmap(){

        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        for (int i = 0; i < width/2; i++) {
            for (int i1 = 0; i1 < height/2; i1++) {
                bitmap.setPixel(i, i1, Color.BLACK);
            }
        }

        for (int i = width/2; i < width; i++) {
            for (int i1 = height/2; i1 < height; i1++) {
                bitmap.setPixel(i, i1, Color.RED);
            }
        }


//        bitmap.setPixel(1, 0, Color.RED);
//        bitmap.setPixel(0, 1, Color.WHITE);
//        bitmap.setPixel(1, 1, Color.BLUE);

        return bitmap;
    }
}
