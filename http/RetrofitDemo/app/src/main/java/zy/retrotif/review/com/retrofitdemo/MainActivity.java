package zy.retrotif.review.com.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void demo1(View view) {

        String API_BASE_URL = "https://api.github.com/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(
                        GsonConverterFactory.create()
                );

        Retrofit retrofit = builder.client(httpClient.build()).build();

        GitHubClient client = retrofit.create(GitHubClient.class);
        Call<List<GitHubRepo>> tmp = client.reposForUser("zhangyu");
        tmp.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                Log.d("zhangyu","onResponse:"+response);
                Log.d("zhangyu","onResponse:"+response.body().toString());
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Log.d("zhangyu","onFailure:"+t);
            }
        });


    }

    public void demo2(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                //使用自定义的mGsonConverterFactory
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://apis.baidu.com/txapi/")
                .build();
        APi mApi = retrofit.create(APi.class);

        mApi = retrofit.create(APi.class);
        Call<News> news = mApi.getNews("1", "10");
        news.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                Log.d("zhangyu","onResponse:"+response);
                Log.d("zhangyu","onResponse:"+response.body().toString());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d("zhangyu","onFailure:"+t);
            }
        });




    }
}
