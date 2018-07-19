package com.example.singou.leakcanarydemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView leak_textview;
    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leak_textview = findViewById(R.id.leak_textview);

        LeakSingle.getInstance().setRetainedTextView(leak_textview);
        //模拟内存泄露
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 3 * 60 * 1000);


    }


    public void onLeak(View view) {
        LeakSingle.getInstance().setRetainedTextView(leak_textview);
    }

    public void onLeakClear(View view) {
        //LeakSingle.getInstance().removeRetainedTextView();
    }

    public void onJump(View view) {
        startActivity(new Intent(MainActivity.this,JumpActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //LeakSingle.getInstance(this).removeRetainedTextView();
        LeakCanaryApplication.getRefWatcher(this).watch(this);
    }


}
/**
 * 内存泄漏（Memory Leak）是指程序中己动态分配的堆内存由于某种原因程序未释放或无法释放，造成系统内存的浪费，导致程序运行速度减慢甚至系统崩溃等严重后果。
 * 总结下为：内存泄漏是不使用或用完的内存，因为某些原因无法回收，造成的一种内存浪费；内存泄漏的本质是内存浪费。
 * 例如：
 * 当我们使用一个Bitmap,使用完成后，没有recycle回收；
 * 当我们使用Handler, 在Activity销毁时没有处理；
 * 当我们使用Cursor，最后没有close并置空;
 *
 * 1.GC回收的对象必须是当前没有任何引用的对象
 * 2.当对象在使用完成后（对我们而言已经是垃圾对象了）， 我们没有释放该对象的引用，导致GC不能回收该对象而继续占用内存
 * 3.垃圾对象依旧占用内存，这块内存空间便浪费了
 *
 * 内存泄漏与内存溢出的区别是什么？
 * 从名称来看，一个泄漏，一个溢出，其实很好理解。一个就是站着毛坑不拉屎，一个是一口吃个大胖子。
 * 可以用水池和水龙头来比喻。
 *
 * 内存的溢出是内存分配达到了最大值，而内存泄漏是无用内存充斥了内存堆；
 * 因此内存泄漏是导致内存溢出的元凶之一，而且是很大的元凶；因为内存分配完后，哪怕占用再大，也会回收，而泄漏的内存则不然；当清理掉无用内存后，内存溢出的阀值也会相应降低。
 *
 *
 * */
