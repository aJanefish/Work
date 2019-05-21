package zy.walk.com.thewalkers.diy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;
/**
 * moveTo 不会进行绘制，只用于移动移动画笔。结合以下方法进行使用。
 * lineTo 用于进行直线绘制。
 * quadTo 用于绘制圆滑曲线，即贝塞尔曲线。 mPath.quadTo(x1, y1, x2, y2) (x1,y1) 为控制点，(x2,y2)为结束点。
 * cubicTo 同样是用来实现贝塞尔曲线的。mPath.cubicTo(x1, y1, x2, y2, x3, y3) (x1,y1) 为控制点，(x2,y2)为控制点，(x3,y3) 为结束点。
 */

import androidx.annotation.Nullable;
import zy.walk.com.thewalkers.R;

public class GpuView extends View {

    private static final String TAG = "GPUTest";
    private Paint pointPaint;//绘制各标识点的画笔
    private Paint bgPaint;//背景画笔

    private MyPoint a, f, g, e, h, c, j, b, k, d, i;

    private int defaultWidth;//默认宽度
    private int defaultHeight;//默认高度
    private int viewWidth;
    private int viewHeight;
    private boolean isInit = false;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private Paint pathAPaint;
    private Path pathC;
    private Path pathB;
    private Paint pathCPaint;
    private Paint pathBPaint;

    public GpuView(Context context) {
        super(context);
        init();
    }

    public GpuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GpuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public GpuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        defaultWidth = 600;
        defaultHeight = 1000;

        viewWidth = defaultWidth;
        viewHeight = defaultHeight;

        a = new MyPoint(400, 800);
        f = new MyPoint(viewWidth, viewHeight);

        g = new MyPoint();
        e = new MyPoint();
        h = new MyPoint();
        c = new MyPoint();
        j = new MyPoint();
        b = new MyPoint();
        k = new MyPoint();
        d = new MyPoint();
        i = new MyPoint();

        //calcPointsXY(a, f);

        pathAPaint = new Paint();
        pathAPaint.setColor(Color.GREEN);
        pathAPaint.setAntiAlias(true);//设置抗锯齿

        pathCPaint = new Paint();
        pathCPaint.setColor(Color.YELLOW);
        pathCPaint.setAntiAlias(true);//设置抗锯
        pathCPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));

        pathBPaint = new Paint();
        pathBPaint.setColor(getResources().getColor(R.color.blue_light));
        pathBPaint.setAntiAlias(true);//设置抗锯齿
        pathBPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));

        pathA = new Path();
        pathC = new Path();
        pathB = new Path();
        getPathAFromLowerRight();

        pointPaint = new Paint();
        pointPaint.setColor(Color.RED);
        pointPaint.setTextSize(25);

        bgPaint = new Paint();
        bgPaint.setColor(Color.GREEN);


    }

    private int measureSize(int defaultSize,int measureSpec) {
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = measureSize(defaultHeight, heightMeasureSpec);
        int width = measureSize(defaultWidth, widthMeasureSpec);
        setMeasuredDimension(width, height);

        viewWidth = width;
        viewHeight = height;
        f.x = width;
        f.y = height;
        calcPointsXY(a,f);//将初始化计算放在这
        Log.d(TAG,"onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG,"onLayout");
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG,"onDraw");
        if (isInit) {
            isInit = true;
            f = new MyPoint(canvas.getWidth() - 100, canvas.getHeight() - 100);
            calcPointsXY(a, f);
        }


        bitmap = Bitmap.createBitmap((int) viewWidth, (int) viewHeight, Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        bitmapCanvas.drawPath(pathA,pathAPaint);
        bitmapCanvas.drawPath(getPathC(),pathCPaint);
        bitmapCanvas.drawPath(getPathB(),pathBPaint);
        //bitmap.getPixel(0,0);
        canvas.drawBitmap(bitmap,0,0,null);


        //为了看清楚点与View的位置关系绘制一个背景
        //canvas.drawRect(0,0,f.x,f.y,bgPaint);
        //绘制各标识点
        canvas.drawText("a", a.x, a.y, pointPaint);
        canvas.drawText("f", f.x-10, f.y-10, pointPaint);
        canvas.drawText("g", g.x, g.y, pointPaint);

        canvas.drawText("e", e.x, e.y, pointPaint);
        canvas.drawText("h", h.x, h.y, pointPaint);

        canvas.drawText("c", c.x, c.y, pointPaint);
        canvas.drawText("j", j.x-10, j.y-10, pointPaint);

        canvas.drawText("b", b.x, b.y, pointPaint);
        canvas.drawText("k", k.x, k.y, pointPaint);

        canvas.drawText("d", d.x, d.y, pointPaint);
        canvas.drawText("i", i.x, i.y, pointPaint);


    }

    private Path pathA;

    /**
     * 获取f点在右下角的pathA
     *
     * @return
     */
    private void getPathAFromLowerRight() {

        pathA.reset();
        //pathA.lineTo(100, 100);//移动到左下角
        pathA.lineTo(0, viewHeight);//移动到左下角
        pathA.lineTo(c.x, c.y);//移动到c点
//        pathA.lineTo(d.x,d.y);
//        pathA.lineTo(b.x,b.y);
        pathA.quadTo(e.x, e.y, b.x, b.y);//从c到b画贝塞尔曲线，控制点为e
        pathA.lineTo(a.x, a.y);//移动到a点
        pathA.lineTo(k.x, k.y);//移动到k点
        //pathA.lineTo(i.x,i.y);
        //pathA.moveTo(i.x,i.y);
        //pathA.lineTo(j.x,j.y);

        //pathA.moveTo(j.x,j.y);
//        pathA.lineTo(f.x,f.y);
//        pathA.lineTo(c.x,c.y);

        pathA.quadTo(h.x, h.y, j.x, j.y);//从k到j画贝塞尔曲线，控制点为h
        pathA.lineTo(viewWidth, 0);//移动到右上角
        //pathA.lineTo(100, 100);
        //pathA.moveTo(viewWidth,0);//移动到右上角
        pathA.close();//闭合区域
        //return pathA;
    }




    /**
     * 绘制区域C
     * @return
     */
    private Path getPathC(){
        pathC.reset();
        pathC.moveTo(i.x,i.y);//移动到i点
        pathC.lineTo(d.x,d.y);//移动到d点
        pathC.lineTo(b.x,b.y);//移动到b点
        pathC.lineTo(a.x,a.y);//移动到a点
        pathC.lineTo(k.x,k.y);//移动到k点
        pathC.close();//闭合区域
        return pathC;
    }

    /**
     * 绘制区域B
     * @return
     */
    private Path getPathB(){
        pathB.reset();
        pathB.lineTo(0, viewHeight);//移动到左下角
        pathB.lineTo(viewWidth,viewHeight);//移动到右下角
        pathB.lineTo(viewWidth,0);//移动到右上角
        pathB.close();//闭合区域
        return pathB;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "MotionEvent.ACTION_MOVE");
                a = new MyPoint(event.getX(), event.getY());
                calcPointsXY(a, f);
                getPathAFromLowerRight();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
//                calcPointsXY(new MyPoint(f.x+10,f.y+10), f);
//                getPathAFromLowerRight();
//                invalidate();
                break;
        }
        return true;
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

    /**
     * 计算各点坐标
     *
     * @param a
     * @param f
     */
    private void calcPointsXY(MyPoint a, MyPoint f) {
        g.x = (a.x + f.x) / 2;
        g.y = (a.y + f.y) / 2;

        e.x = g.x - (f.y - g.y) * (f.y - g.y) / (f.x - g.x);
        e.y = f.y;

        h.x = f.x;
        h.y = g.y - (f.x - g.x) * (f.x - g.x) / (f.y - g.y);

        c.x = e.x - (f.x - e.x) / 2;
        c.x = c.x < 0 ? 0 : c.x;
        c.y = f.y;

        j.x = f.x;
        j.y = h.y - (f.y - h.y) / 2;
        j.y = j.y < 0 ? 0 : j.y;

        b = getIntersectionPoint(a, e, c, j);
        k = getIntersectionPoint(a, h, c, j);

        d.x = (c.x + 2 * e.x + b.x) / 4;
        d.y = (2 * e.y + c.y + b.y) / 4;

        i.x = (j.x + 2 * h.x + k.x) / 4;
        i.y = (2 * h.y + j.y + k.y) / 4;
    }

    /**
     * 计算两线段相交点坐标
     *
     * @param lineOne_My_pointOne
     * @param lineOne_My_pointTwo
     * @param lineTwo_My_pointOne
     * @param lineTwo_My_pointTwo
     * @return 返回该点
     */
    private MyPoint getIntersectionPoint(MyPoint lineOne_My_pointOne, MyPoint lineOne_My_pointTwo, MyPoint lineTwo_My_pointOne, MyPoint lineTwo_My_pointTwo) {
        float x1, y1, x2, y2, x3, y3, x4, y4;
        x1 = lineOne_My_pointOne.x;
        y1 = lineOne_My_pointOne.y;
        x2 = lineOne_My_pointTwo.x;
        y2 = lineOne_My_pointTwo.y;
        x3 = lineTwo_My_pointOne.x;
        y3 = lineTwo_My_pointOne.y;
        x4 = lineTwo_My_pointTwo.x;
        y4 = lineTwo_My_pointTwo.y;

        float pointX = ((x1 - x2) * (x3 * y4 - x4 * y3) - (x3 - x4) * (x1 * y2 - x2 * y1))
                / ((x3 - x4) * (y1 - y2) - (x1 - x2) * (y3 - y4));
        float pointY = ((y1 - y2) * (x3 * y4 - x4 * y3) - (x1 * y2 - x2 * y1) * (y3 - y4))
                / ((y1 - y2) * (x3 - x4) - (x1 - x2) * (y3 - y4));

        return new MyPoint(pointX, pointY);
    }

    class MyPoint {
        public float x, y;

        public MyPoint() {
        }

        public MyPoint(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
}
