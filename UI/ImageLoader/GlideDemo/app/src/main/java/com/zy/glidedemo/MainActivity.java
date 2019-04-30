package com.zy.glidedemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ViewTarget;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView_one;
    private ImageView imageView_two;


    private String TAG= "MainActivityZy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView_one = findViewById(R.id.activity_main_image_view_one);
        imageView_two = findViewById(R.id.activity_main_image_view_two);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onBase(View view){
        Toast.makeText(this,"onBase",Toast.LENGTH_LONG).show();

        final String uri = "https://i.loli.net/2019/02/21/5c6e1e24c4689.png";
        RequestManager requestManager = Glide.with(this);

        RequestBuilder<Drawable> requestBuilder = requestManager.load(uri);
        requestBuilder.skipMemoryCache(true);
        requestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL);
        ViewTarget<ImageView, Drawable> viewTarget = requestBuilder.into(imageView_one);



        Glide.with(this).load(uri).into(imageView_two);


        // Glide.with(this).load()
//        RequestManager requestManager = Glide.with(getApplicationContext());
//        RequestBuilder<Drawable> requestBuilder = requestManager.load(uri);
//        requestBuilder.placeholder(getDrawable(R.drawable.ic_launcher_background));
//        //requestBuilder.e
//
//
//        ViewTarget<ImageView, Drawable> viewDrawableViewTarget = requestBuilder.into(imageView_one);
//        ViewTarget<ImageView, Drawable> viewDrawableViewTarget1 = requestBuilder.into(imageView_one);
//
//        Log.d(TAG, ""+viewDrawableViewTarget.getClass()
//        );



        //RequestBuilder<Integer> integerRequestBuilder = null;

    }
}
