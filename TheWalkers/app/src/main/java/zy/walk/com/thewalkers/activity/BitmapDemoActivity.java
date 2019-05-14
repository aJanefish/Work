package zy.walk.com.thewalkers.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import zy.walk.com.thewalkers.R;
import zy.walk.com.thewalkers.adapter.MainAdapter;
import zy.walk.com.thewalkers.imagesandanimations.event.MainEvent;
import zy.walk.com.thewalkers.utils.Constant;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

/**
 * 对Bitmap的理解
 * Bitmap.Config是影响图片画质的重要因素，单位像素占用字节越大，画质越高。ARGB是一种存储色彩的模式，其中A：透明度；R：红色；G：绿色；B：蓝色
 * Bitmap.Config	值	描述	占用内存(字节)
 * Bitmap.Config	ARGB_8888	表示32位的ARGB位图	4
 * Bitmap.Config	ARGB_4444	表示16位的ARGB位图	2
 * Bitmap.Config	RGB_565	表示16位的RGB位图	2
 * Bitmap.Config	ALPHA_8	表示8位的Alpha位图	1
 */

public class BitmapDemoActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private ImageView image_view;
    private TextView text_view_des;

    Bitmap bitmap  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_demo);

        initView();
    }


    @SuppressLint("WrongConstant")
    @TargetApi(Build.VERSION_CODES.O)
    private void initView() {
        image_view = findViewById(R.id.bitmap_demo_image_view);
        text_view_des = findViewById(R.id.bitmap_demo_text_view_des);


        recycler_view = findViewById(R.id.bitmap_demo_recycler_view);

        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), VERTICAL, false));


        final List<BitmapEvent> list = new ArrayList<>();
        list.add(new BitmapEvent(1, "createBitmap创建ARGB_8888",Bitmap.Config.ARGB_8888));
        list.add(new BitmapEvent(1, "createBitmap创建ALPHA_8",Bitmap.Config.ALPHA_8));
        list.add(new BitmapEvent(1, "createBitmap创建ARGB_4444",Bitmap.Config.ARGB_4444));
        list.add(new BitmapEvent(1, "createBitmap创建RGB_565",Bitmap.Config.RGB_565));
        list.add(new BitmapEvent(1, "createBitmap创建RGBA_F16",Bitmap.Config.RGBA_F16));
        list.add(new BitmapEvent(2, "createBitmap(bitmap)"));
        list.add(new BitmapEvent(3, "createBitmap(bitmap,150,150,150,150)"));
        list.add(new BitmapEvent(4, "createBitmap(ints,100,100,Bitmap.Config.ARGB_4444)"));
        list.add(new BitmapEvent(5, "Bitmap加载内存优化(1)"));


        list.add(new BitmapEvent(6, "Bitmap加载内存优化(2)"));
        list.add(new BitmapEvent(7, "Bitmap加载内存优化(4)"));
        list.add(new BitmapEvent(8, "Bitmap加载内存优化(8)"));
        list.add(new BitmapEvent(9, "Bitmap转换[向左旋转]"));
        list.add(new BitmapEvent(10, "Bitmap转换[缩放图像]"));
        list.add(new BitmapEvent(11, "Bitmap转换[移动图像]"));
        list.add(new BitmapEvent(12, "Bitmap转换[圆角]"));
        list.add(new BitmapEvent(13, "Bitmap转换[灰阶处理]"));



        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));

        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));
        list.add(new BitmapEvent(10, ""));



        CommonAdapter<BitmapEvent> commonAdapter = new CommonAdapter<BitmapEvent>(this, R.layout.activity_bitmap_demo_adapter_item, list) {
            @Override
            protected void convert(ViewHolder holder, BitmapEvent bitmapEvent, int position) {
                holder.setText(R.id.activity_bitmap_demo_adapter_item_title, "" + bitmapEvent.id);
                holder.setText(R.id.activity_bitmap_demo_adapter_item_des, bitmapEvent.des);
            }
        };

        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int index) {
                BitmapEvent bitmapEvent = list.get(index);
                if(bitmap != null){
                    bitmap.recycle();
                }
                switch (bitmapEvent.id) {
                    case 1:
                        bitmap = createBitmap(bitmapEvent.config);
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;
                    case 2:
                        bitmap = createBitmap_2();
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;
                    case 3:
                        bitmap = createBitmap_3();
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;

                    case 4:
                        bitmap = createBitmap_4();
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;


                    case 5:
                        bitmap = createBitmap_5(1);
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;
                    case 6:
                        bitmap = createBitmap_5(2);
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;
                    case 7:
                        bitmap = createBitmap_5(4);
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;

                    case 8:
                        bitmap = createBitmap_5(8);
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;

                    case 9:
                        bitmap = createBitmap_6();
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;

                    case 10:
                        bitmap = createBitmap_7();
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;

                    case 11:
                        bitmap = createBitmap_8();
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;

                    case 12:
                        bitmap = createBitmap_9();
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;
                    case 13:
                        bitmap = createBitmap_10();
                        showBitmapDes(bitmap);
                        image_view.setImageBitmap(bitmap);
                        break;

                    default:
                        Toast.makeText(BitmapDemoActivity.this,"default",Toast.LENGTH_LONG).show();
                        break;
                }

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                return false;
            }
        });

        recycler_view.setAdapter(commonAdapter);
    }

    private void showBitmapDes(Bitmap bitmap) {
        StringBuilder stringBuilder = new StringBuilder("[");
        stringBuilder.append(image_view.getWidth());
        stringBuilder.append(",");
        stringBuilder.append(image_view.getHeight());
        stringBuilder.append("]\n[");
        stringBuilder.append(bitmap.getWidth());
        stringBuilder.append(",");
        stringBuilder.append(bitmap.getHeight());
        stringBuilder.append("]");
        stringBuilder.append("\n"+bitmap.getConfig());
        stringBuilder.append("\n"+bitmap.getByteCount());
        stringBuilder.append("\n"+((float)bitmap.getByteCount()/(float) 1024)+"KB");
        text_view_des.setText(stringBuilder);
    }


    public  Bitmap createBitmap_10(){
        Bitmap originBitmap = createBitmap_5(8);
        //【图片灰阶处理】
        Bitmap grayBitmap = Bitmap.createBitmap(originBitmap.getWidth(), originBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(grayBitmap);
        Paint mPaint = new Paint();

        //创建颜色变换矩阵
        ColorMatrix colorMatrix = new ColorMatrix();
        //设置饱和度为0，实现灰阶效果
        colorMatrix.setSaturation(0);
        //创建颜色过滤矩阵
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);

        //设置画笔的颜色过滤矩阵
        mPaint.setColorFilter(colorFilter);

        //使用处理后的画笔绘制图像
        canvas.drawBitmap(originBitmap, 0, 0, mPaint);
        return grayBitmap;

    }

    public  Bitmap createBitmap_9(){
        // 【圆角】
        // 准备画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Bitmap originBitmap = createBitmap_5(8);
        //Bitmap originBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),new Matrix(), true);
        // 准备裁剪的矩阵
        Rect rect = new Rect(0, 0, originBitmap.getWidth(), originBitmap.getHeight());

        RectF rectF = new RectF(new Rect(0, 0, originBitmap.getWidth(), originBitmap.getHeight()));

        Bitmap roundBitmap = Bitmap.createBitmap(originBitmap.getWidth(), originBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundBitmap);

        // 圆角矩阵，radius为圆角大小
        canvas.drawRoundRect(rectF, 20, 20, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); // 这个比较有意思，混合模式  SRC_IN:显示交汇SRC图
        canvas.drawBitmap(originBitmap, rect, rect, paint);

        return  roundBitmap;
    }


    //转换
    public  Bitmap createBitmap_8(){
        // 定义矩阵
        Matrix matrix = new Matrix();
        // 【缩放图像】
        //matrix.postScale(0.5f, 0.5f);
        // 【向左旋转】
        //matrix.postRotate(-90);
        // 【移动图像】
        matrix.postTranslate(100, 100);
        // 【裁减图像】
        //Bitmap.createBitmap(Bitmap source, int x, int y, int width, int height, Matrix m, boolean filter)
        Bitmap bitmap = createBitmap_5(8);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }


    //转换
    public  Bitmap createBitmap_7(){
        // 定义矩阵
        Matrix matrix = new Matrix();
        // 【缩放图像】
        matrix.postScale(0.5f, 0.5f);
        // 【向左旋转】
        //matrix.postRotate(-90);
        // 【移动图像】
        //matrix.postTranslate(100, 100);
        // 【裁减图像】
        //Bitmap.createBitmap(Bitmap source, int x, int y, int width, int height, Matrix m, boolean filter)
        Bitmap bitmap = createBitmap_5(8);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }


    //转换
    public  Bitmap createBitmap_6(){
        // 定义矩阵
        Matrix matrix = new Matrix();
        // 【缩放图像】
        //matrix.postScale(0.8f, 0.9f);
        // 【向左旋转】
        matrix.postRotate(-90);
        // 【移动图像】
        //matrix.postTranslate(100, 100);
        // 【裁减图像】
        //Bitmap.createBitmap(Bitmap source, int x, int y, int width, int height, Matrix m, boolean filter)
        Bitmap bitmap = createBitmap_5(8);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }


    //加载大的图片 , 内存优化
    public Bitmap createBitmap_5(int inSampleSize) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        //inJustDecodeBounds为true，不返回bitmap，只返回这个bitmap的尺寸
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.gril, options);

        //利用返回的原图片的宽高，我们就可以计算出缩放比inSampleSize（只能是2的整数次幂）
        //options.inSampleSize = caluelateInSampleSize(options, reqWidth, reqHeight);//使用RGB_565减少图片大小
        //压缩的比例，inSampleSize = 2 时： 压缩一半
        options.inSampleSize = inSampleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        //释放内存，共享引用（21版本后失效）
        //options.inPurgeable = true;
        //options.inInputShareable = true;


        //inJustDecodeBounds为false，返回bitmap
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gril, options);

        return bitmap;
    }


    public Bitmap createBitmap_4() {

        //Bitmap bitmap = createBitmap(Bitmap.Config.ARGB_8888);
        int[] ints = new int[100*100];
        for (int i = 0; i < 100/2; i++) {
            for (int i1 = 0; i1 < 100/2; i1++) {
                ints[i*100+ i1] =  Color.BLACK;
            }
        }

        for (int i = 100/2; i < 100; i++) {
            for (int i1 = 100/2; i1 < 100; i1++) {
                ints[i*100+ i1] =  Color.RED;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(ints,100,100,Bitmap.Config.ARGB_4444);
        return bitmap;
    }


    public Bitmap createBitmap(Bitmap.Config config) {

        Bitmap bitmap = Bitmap.createBitmap(300, 300, config);

        Canvas bitmapCanvas = new Canvas(bitmap);
        Paint pathAPaint = new Paint();
        pathAPaint.setAlpha(50);
        pathAPaint.setColor(Color.RED);
        Path pathA = new Path();
        pathA.moveTo(150, 150);
        pathA.lineTo(0, 300);
        pathA.lineTo(300, 300);
        pathA.close();
        bitmapCanvas.drawPath(pathA, pathAPaint);
//        bitmapCanvas.drawPath(getPathC(),pathCPaint);
//        bitmapCanvas.drawPath(getPathB(),pathBPaint);
        return bitmap;
    }

    public Bitmap createBitmap_2() {

        Bitmap bitmap = createBitmap(Bitmap.Config.ARGB_8888);

        bitmap = Bitmap.createBitmap(bitmap);
        return bitmap;
    }

    public Bitmap createBitmap_3() {

        Bitmap bitmap = createBitmap(Bitmap.Config.ARGB_8888);

        bitmap = Bitmap.createBitmap(bitmap,150,150,150,150);
        return bitmap;
    }


    class BitmapEvent {
        int id;
        String des;
        Bitmap.Config config;

        public BitmapEvent(int id, String des, Bitmap.Config config) {
            this.id = id;
            this.des = des;
            this.config = config;
        }

        public BitmapEvent(int id, String des) {
            this.id = id;
            this.des = des;
        }
    }


}
