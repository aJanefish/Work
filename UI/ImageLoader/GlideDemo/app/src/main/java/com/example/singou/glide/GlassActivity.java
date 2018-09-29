package com.example.singou.glide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.singou.glide.MainAdapter.Type;

/**
 * 图片处理网址：https://github.com/wasabeef/glide-transformations
 * */

public class GlassActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glass);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Type> dataSet = new ArrayList<>();
        dataSet.add(Type.Mask);
        dataSet.add(Type.NinePatchMask);
        dataSet.add(Type.CropTop);
        dataSet.add(Type.CropCenter);
        dataSet.add(Type.CropBottom);
        dataSet.add(Type.CropSquare);
        dataSet.add(Type.CropCircle);
        dataSet.add(Type.ColorFilter);
        dataSet.add(Type.Grayscale);
        dataSet.add(Type.RoundedCorners);
        dataSet.add(Type.Blur);
        dataSet.add(Type.SupportRSBlur);
        dataSet.add(Type.Toon);
        dataSet.add(Type.Sepia);
        dataSet.add(Type.Contrast);
        dataSet.add(Type.Invert);
        dataSet.add(Type.Pixel);
        dataSet.add(Type.Sketch);
        dataSet.add(Type.Swirl);
        dataSet.add(Type.Brightness);
        dataSet.add(MainAdapter.Type.Kuawahara);
        dataSet.add(MainAdapter.Type.Vignette);

        recyclerView.setAdapter(new MainAdapter(this, dataSet));


    }






}
