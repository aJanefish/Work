package zy.annotations.com.annotationsdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * 自定义注解框架：
 * 知识点：
 * java注解，反射
 *
 * 1.生成文档。这是最常见的，也是java 最早提供的注解。常用的有@see @param @return 等；
 * 2.跟踪代码依赖性，实现替代配置文件功能。比较常见的是spring 2.5 开始的基于注解配置。作用就是减少配置。现在的框架基本都使用了这种配置来减少配置文件的数量；
 * 3.在编译时进行格式检查。如@Override放在方法前，如果你这个方法并不是覆盖了超类方法，则编译时就能检查出；
 *
 *
 * */

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.button1)
    private Button button1;
    @ViewInject(R.id.button2)
    private Button button2;
    @ViewInject(R.id.button3)
    private Button button3;

    private int tmp  = 0 ;

    private final List<byte[]>  list = new ArrayList<>();

    private float total ;
    private float free ;

    private int rowlenght = 10;
    private int len = 420000;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtilsTest.inject(this);

        if(savedInstanceState != null)
            Log.e("MainActivity","onCreate() : " + savedInstanceState.getString("octopus"));


        total = Runtime.getRuntime().totalMemory()*1.0f/(1024*1024);
        free = Runtime.getRuntime().freeMemory()*1.0f/(1024*1024);
        Log.e("MainActivity","onCreate() :total: " + total);
        Log.e("MainActivity","onCreate() :free :" + free);

    }

    @Click({R.id.button1,R.id.button2,R.id.button3})
    public void onClick(View view){
        total = Runtime.getRuntime().totalMemory()*1.0f/(1024*1024);
        free = Runtime.getRuntime().freeMemory()*1.0f/(1024*1024);
        switch (view.getId()) {
            case R.id.button1:
                Toast.makeText(this,"button1:"+button1,Toast.LENGTH_LONG).show();

                list.add(new byte[1024*128]);



                Log.e("MainActivity","onCreate() :total: " + total);
                Log.e("MainActivity","onCreate() :free :" + free);
                break;
            case R.id.button2:
                Toast.makeText(this,"button2:"+button2,Toast.LENGTH_LONG).show();
                list.clear();

                Log.e("MainActivity","onCreate() :total: " + total);
                Log.e("MainActivity","onCreate() :free :" + free);
                break;
            case R.id.button3:
                Toast.makeText(this,"button3:"+button3,Toast.LENGTH_LONG).show();
                Log.e("MainActivity","onCreate() :total: " + total);
                Log.e("MainActivity","onCreate() :free :" + free);
                //System.gc();
                //churn();
                break;
        }


    }

    private void churn(){
        for (int i = 0; i < rowlenght; i++) {
            String[] strings = new String[len];
            for (int i1 = 0; i1 < len; i1++) {
                strings[i1] = String.valueOf(random.nextDouble());
            }
            
        }
    }

    private void show(String s){

    }



    private static Handler handler = new Handler(){



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };




    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("MainActivity","onRestoreInstanceState() : " + savedInstanceState.getString("octopus"));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("octopus", "www.baidu.com");
        Log.e("MainActivity","onSaveInstanceState() : save date www.baidu.com");
    }

}
