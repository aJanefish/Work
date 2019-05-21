package zy.walk.com.thewalkers.activity;

import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.diy.Ball;
import zy.walk.com.thewalkers.diy.RunBallSix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BitmapActivity extends AppCompatActivity {

    private TextView activity_bitmap_show;
    private int[][] mColArr;
    private ImageView activity_bitmap_image_view;
    private String TAG= "BitmapActivity";
    private RunBallSix run_ball_six;
    private Bitmap qq;
    private Bitmap qq_big;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        initView();

        initDate();
    }

    private void initDate() {
        activity_bitmap_image_view.setImageBitmap(createBitmap(200,200));
        qq = BitmapFactory.decodeResource(getResources(), R.drawable.qq);
        qq_big = BitmapFactory.decodeResource(getResources(), R.drawable.qq_big);
    }


    private float d = 10;//复刻的像素边长
    private List<Ball> mBalls = new ArrayList<>();//粒子集合
    /**
     * 根像素初始化粒子
     * @param bitmap
     * @return
     */
    private List<Ball> initRect(Bitmap bitmap,float d) {
        for (int i = 0; i < bitmap.getWidth(); i+=d) {
            for (int j = 0; j < bitmap.getHeight(); j+=d) {
                Ball ball = new Ball();
                ball.x = i * d ;
                ball.y = j * d ;


                ball.vX = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                ball.vY = rangeInt(-15, 35);
                ball.aY = 0.98f;

                ball.color = bitmap.getPixel(i, j);
                ball.born = System.currentTimeMillis();


                mBalls.add(ball);
            }
        }
        return mBalls;
    }

    private float rangeInt(int num1, int num2) {
        return num1+(int)(Math.random()*(num2-num1));
    }



    private void initView() {
        activity_bitmap_show   = findViewById(R.id.activity_bitmap_show);
        activity_bitmap_image_view   = findViewById(R.id.activity_bitmap_image_view);
        run_ball_six = findViewById(R.id.activity_bitmap_run_ball_six);
    }

    //获取像素
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
    private Bitmap createBitmap(int width,int height){

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

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


    /**
     * 将颜色从int 拆分成argb,并打印出来
     * @param msg
     * @param color
     */
    private void printColor(String msg, int color) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        Log.d(TAG,msg + "----a:" + a + ", r:" + r + ", g:" + g + ", b:" + b);
    }




    /**
     * 保存bitmap到本地
     *
     * @param path    路径
     * @param mBitmap 图片
     * @return 路径
     */
    public static String saveBitmap(String path, Bitmap mBitmap) {



//        File filePic = FileHelper.get().createFile(path + ".png");
//        try {
//            FileOutputStream fos = new FileOutputStream(filePic);
//            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return filePic.getAbsolutePath();
        return null;
    }


    public void ball(View view) {
        mBalls.clear();
        if (qq_big == null){
            Log.d(TAG,"qq_big == null");
            qq_big = BitmapFactory.decodeResource(getResources(), R.drawable.qq_big);
            return;
        }
        Log.d(TAG,"bitmap.size():"+qq_big.getWidth()+" , "+qq_big.getHeight());
        int r = 16;
        d = r*2;

        initBall(qq_big,d);
        Log.d(TAG,"bitmap.size():"+mBalls.size());
        run_ball_six.setList(mBalls,d,true);
    }

    //        if (x >= getWidth()) {
    //            throw new IllegalArgumentException("x must be < bitmap.width()");
    //        }
    //        if (y >= getHeight()) {
    //            throw new IllegalArgumentException("y must be < bitmap.height()");
    //        }

    private void initBall(Bitmap bitmap, float d) {
        for (int i = 0; i < bitmap.getHeight(); i+=d) {
            for (int j = 0; j < bitmap.getWidth(); j+=d) {

                Ball ball = new Ball();
                ball.x = j ;
                ball.y = i ;



                ball.vX = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                ball.vY = rangeInt(-15, 35);
                ball.aY = 0.98f;
                ball.r = d/2;
                ball.color = bitmap.getPixel(j, i);
                ball.born = System.currentTimeMillis();


                mBalls.add(ball);
            }
        }

    }

    public void rect(View view) {
        mBalls.clear();
        if (qq == null){
            Log.d(TAG,"qq == null");
            qq = BitmapFactory.decodeResource(getResources(), R.drawable.qq);
            return;
        }


        Log.d(TAG,"bitmap.size():"+qq.getWidth()+" , "+qq.getHeight());
        d = 5;
        initRect(qq,d);
        Log.d(TAG,"mBalls.size():"+mBalls.size());
        run_ball_six.setList(mBalls,d,false);
    }
}
