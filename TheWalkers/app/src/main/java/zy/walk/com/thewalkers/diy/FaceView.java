package zy.walk.com.thewalkers.diy;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.Random;

import androidx.annotation.Nullable;

public class FaceView extends View {
    private ValueAnimator mAnimator;//时间流
    private Ball mBall;//小球对象
    private Paint mPaint;//主画笔
    private Point mCoo;//坐标系

    private float defaultR = 20;//默认小球半径
    private int defaultColor = Color.BLUE;//默认小球颜色
    private float defaultVX = 0;//默认小球x方向速度
    private float defaultVY = 0;//默认小球y方向速度
    private String TAG = "RunBall";

    public FaceView(Context context) {
        this(context, null);
    }

    public FaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        //初始化小球
        mBall = new Ball(defaultR);
        mBall.color = defaultColor;

        mBall.vX = defaultVX;
        mBall.vY = defaultVY;
        mBall.aX = 1;
        mBall.aY = 1;
        //mBall.a = defaultA;
        //初始画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //初始化时间流ValueAnimator
        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(1000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //时间流,必须是线性的
                updateBall();//更新小球信息
                invalidate();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCoo == null) {
            mCoo = new Point(canvas.getWidth(), canvas.getHeight());
            method();
        }
        canvas.save();
        //平移
        canvas.translate(0, 0);
        drawBall(canvas, mBall);
        Log.d(TAG,"mBall:"+mBall);
        canvas.restore();
    }

    /**
     * 绘制小球
     *
     * @param canvas
     * @param ball
     */
    private void drawBall(Canvas canvas, Ball ball) {
        mPaint.setColor(ball.color);
        canvas.drawCircle(ball.x, ball.y, ball.r, mPaint);
    }

    /**
     * 更新小球
     */
    private float defaultF = 0.9f;//碰撞损耗
    private void updateBall() {
        switch (type) {
            case 7:
                update7();
                break;
            case 8:
                update8();
                break;
            case 9:
                update9();
                break;
            default:
                defaultUpdateBall();
                break;
        }
        //TODO --运动数据都由此函数变换

    }

    private void update9() {
        update8();
    }

    private void update7() {
        mBall.x += mBall.vX;
        mBall.y += mBall.vY;
        mBall.vX += mBall.aX;
        if (mBall.x > mCoo.x - mBall.r) {
            mBall.vX = -mBall.vX*9/10;
            mBall.color = randomRGB();//更改颜色
        }
    }


    private void update8() {
        mBall.x += mBall.vX;
        mBall.y += mBall.vY;
        mBall.vY += mBall.aY;
        if (mBall.x > mCoo.x) {
            mBall.x = mCoo.x;
            mBall.vX = -mBall.vX * defaultF;
            mBall.color = randomRGB();//更改颜色
        }
        if (mBall.x < 0) {
            mBall.x = 0;
            mBall.vX = -mBall.vX * defaultF;
            mBall.color = randomRGB();//更改颜色
        }
        if (mBall.y > mCoo.y) {
            mBall.y = mCoo.y;
            mBall.vY = -mBall.vY * defaultF;
            mBall.color = randomRGB();//更改颜色
        }
        if (mBall.y < 0) {
            mBall.y = 0;
            mBall.vY = -mBall.vY * defaultF;
            mBall.color = randomRGB();//更改颜色
        }



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
        return Color.rgb( r, g, b);
    }

    private void defaultUpdateBall() {

        mBall.vX += mBall.aX;
        mBall.vY += mBall.aY;

        mBall.x += mBall.vX;
        mBall.y += mBall.vY;

        if (mBall.x + mBall.r > mCoo.x) {
            mBall.vX = -Math.abs(mBall.vX);
            mBall.aX = -Math.abs(mBall.aX);
            mBall.color = randomRGB();//更改颜色
        }
        if (mBall.x - mBall.r < 0) {
            mBall.vX = Math.abs(mBall.vX);
            mBall.aX = Math.abs(mBall.aX);
            mBall.color = randomRGB();//更改颜色
        }

        if (mBall.y + mBall.r > mCoo.y) {
            mBall.vY = -Math.abs(mBall.vY);
            mBall.aY = -Math.abs(mBall.aY);
            mBall.color = randomRGB();//更改颜色
        }
        if (mBall.y - mBall.r < 0) {
            mBall.vY = Math.abs(mBall.vY);
            mBall.aY = Math.abs(mBall.aY);
            mBall.color = randomRGB();//更改颜色
        }
        Log.d(TAG, "mBall:" + mBall + " , " + mCoo);
    }

    int type = 0;

    public void setType(int type) {
        this.type = type;
    }

    private void method() {
        switch (type) {
            case 1:
                method1();
                break;
            case 2:
                method2();
                break;
            case 3:
                method3();
                break;
            case 4:
                method4();
                break;
            case 5:
                method5();
                break;
            case 6:
                method6();
                break;
            case 7:
                method7();
                break;
            case 8:
                method8();
                break;
            case 9:
                method9();
                break;
            default:
                mBall.x = mBall.r;
                mBall.y = mBall.r;

                mBall.vX = 0;
                mBall.vY = 0;

                mBall.aX = 1;
                mBall.aY = 1;
                break;
        }
    }
    //斜抛运动：具有初始水平和垂直速度
    private void method9() {

        mBall.x = mCoo.x/2;
        mBall.y = mCoo.y/2;

        mBall.vX = 20;
        mBall.vY = -12;

        mBall.aX = 0;
        mBall.aY = 0.98f;
    }

    //平抛运动+模拟碰撞损耗
    private void method8() {
        //private float defaultR = 20;//默认小球半径
        //private int defaultColor = Color.BLUE;//默认小球颜色
        //private float defaultVX = 0;//默认小球x方向速度
        //private float defaultVY = 0;//默认小球y方向速度
        //private float defaultAY = 0.98f;//默认小球加速度
        //private float mMaxY = 0;//Y最大值

        mBall.x = mBall.r;
        mBall.y = mBall.r;

        mBall.vX = 20;
        mBall.vY = 0;

        mBall.aX = 0;
        mBall.aY = 0.98f;


    }

    //自由落体
    private void method7() {
        //private float defaultR = 20;//默认小球半径
        //private int defaultColor = Color.BLUE;//默认小球颜色
        //private float defaultVX = 0;//默认小球x方向速度
        //private float defaultVY = 0;//默认小球y方向速度
        //private float defaultAY = 0.98f;//默认小球加速度
        //private float mMaxY = 0;//Y最大值

        mBall.x = mBall.r;
        mBall.y = mBall.r;

        mBall.vX = 0;
        mBall.vY = 0;

        mBall.aX = 0.98f;
        mBall.aY = 0;


    }

    private void method6() {
        mBall.x = mBall.r;
        mBall.y = mBall.r;

        mBall.vX = 10;
        mBall.vY = 0;

        mBall.aX = 0;
        mBall.aY = 2;

    }

    private void method5() {
        mBall.x = mBall.r;
        mBall.y = mBall.r;

        mBall.vX = 0;
        mBall.vY = 0;

        mBall.aX = 1;
        mBall.aY = 2;

    }

    private void method4() {
        mBall.x = mBall.r;
        mBall.y = mBall.r;

        mBall.vX = 10;
        mBall.vY = 0;

        mBall.aX = 1;
        mBall.aY = 0;

    }


    private void method3() {
        mBall.x = mBall.r;
        mBall.y = mBall.r;

        mBall.vX = 10;
        mBall.vY = 20;

        mBall.aX = 0;
        mBall.aY = 0;

    }

    private void method1() {
        mBall.x = mBall.r;
        mBall.y = mBall.r;

        mBall.vX = 10;
        mBall.vY = 0;

        mBall.aX = 0;
        mBall.aY = 0;

    }

    private void method2() {
        mBall.x = mBall.r;
        mBall.y = mBall.r;

        mBall.vX = 0;
        mBall.vY = 10;

        mBall.aX = 0;
        mBall.aY = 0;

    }


    public void start() {
        mAnimator.start();//开启时间流

    }

    public void pause() {
        reset();
        mAnimator.pause();//暂停时间流
    }

    private void reset() {
        method();
        invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                start();
                break;
            case MotionEvent.ACTION_UP:
                pause();
                break;
        }
        return true;
    }
}
