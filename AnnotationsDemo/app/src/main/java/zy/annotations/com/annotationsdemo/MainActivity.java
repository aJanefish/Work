package zy.annotations.com.annotationsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    Button button1;
    @ViewInject(R.id.button2)
    Button button2;
    @ViewInject(R.id.button3)
    Button button3;

    private int tmp  = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtilsTest.inject(this);

    }

    @Click({R.id.button1,R.id.button2,R.id.button3})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.button1:
                Toast.makeText(this,"button1:"+button1,Toast.LENGTH_LONG).show();

                break;
            case R.id.button2:
                Toast.makeText(this,"button2:"+button2,Toast.LENGTH_LONG).show();
                break;
            case R.id.button3:
                Toast.makeText(this,"button3:"+button3,Toast.LENGTH_LONG).show();
                break;
        }


    }

    private void show(String s){

    }
}
