package zy.walk.com.thewalkers.diy;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;

/**
 * 连个小球反弹
 */

public class RunBallSix extends View {
    private ValueAnimator mAnimator;//时间流
    private Paint mPaint;//主画笔
    private Point mCoo;//坐标系

    private float defaultR = 64;//默认小球半径
    private float defaultVX = 10;//默认小球x方向速度
    private float defaultVY = 10;//默认小球y方向速度
    private String TAG = "RunBallSix";
    List<Ball> list;
    private long mRunTime;

    public RunBallSix(Context context) {
        this(context, null);
    }

    public RunBallSix(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        list = new ArrayList<>();

        initBall();
        //初始画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //初始化时间流ValueAnimator
        mAnimator = ValueAnimator.ofInt(0, 1);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(1000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBall();//更新小球信息
                invalidate();
            }
        });


    }

    //初始化时准备一个小球数组---参数值随机一些
    private void initBall() {

        //list.clear();
    }

    private float d = 50;//复刻的像素边长

    boolean isBall = false;
    public void setList(List<Ball> list, float d,boolean isBall) {

        this.list = list;
        this.d = d;
        this.isBall =  isBall;
        //mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));//设置对源的叠合模式
        invalidate();
    }


    private float rangeInt(int num1, int num2) {
        return num1 + (int) (Math.random() * (num2 - num1));
    }


    private void updateBall() {
        for (int i = 0; i < list.size(); i++) {
            Ball ball = list.get(i);
            if (System.currentTimeMillis() - mRunTime > 2000) {
                list.remove(i);
            }
            ball.x += ball.vX;
            ball.y += ball.vY;
            ball.vY += ball.aY;
            ball.vX += ball.aX;
        }


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG,"onDraw");
        if (mCoo == null) {

            mCoo = new Point(canvas.getWidth(), canvas.getHeight());
            Log.d(TAG, "mCoo" + mCoo);
        }
        canvas.save();


        canvas.translate(0, 0);
        canvas.drawLine(0, 0, canvas.getWidth(), 0, mPaint);

        drawBall(canvas);

        canvas.restore();

    }

    /**
     * 绘制小球
     *
     * @param canvas
     */


    private void drawBall(Canvas canvas) {


        for (Ball ball : list) {
            mPaint.setColor(ball.color);
            Log.d(TAG, "isBall:" +isBall);
            if(isBall){
                canvas.drawCircle(ball.x, ball.y, ball.r, mPaint);
            }else {
                canvas.drawRect(ball.x, ball.y, ball.x + d, ball.y + d, mPaint);

            }
        }


    }


    public void start() {
        mAnimator.start();//开启时间流

    }

    public void pause() {
        reset();
        //mAnimator.pause();//暂停时间流
        mAnimator.cancel();
    }

    private void reset() {
        initBall();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mRunTime = System.currentTimeMillis();//记录点击时间
                start();
                break;
            case MotionEvent.ACTION_UP:
                pause();
                break;
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG,"onLayout");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG,"onMeasure");
    }

    /**
     * 返回随机颜色
     *
     * @return 随机颜色
     */
    public static int randomRGB() {
        Random random = new Random();
        int r = 30 + random.nextInt(200);
        int g = 30 + random.nextInt(200);
        int b = 30 + random.nextInt(200);
        return Color.rgb(r, g, b);
    }

}
