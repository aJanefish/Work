package com.zy.example;

import com.zy.Constant;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

import java.io.IOException;

public class Example02 {

    public interface BlogService {

        @HTTP(method = "GET", path = "blog/{page}", hasBody = false)
        Call<ResponseBody> getBlog(@Path("page") int id);
    }

    public static void main(String args[]){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL)
                .build();

        BlogService service = retrofit.create(BlogService.class);
        Call<ResponseBody> call = service.getBlog(2);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
