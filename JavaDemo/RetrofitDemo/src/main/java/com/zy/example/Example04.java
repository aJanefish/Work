package com.zy.example;

import com.zy.Constant;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
/**
 * 添加diy headers
 * */
public class Example04 {

    public interface BlogService {

        @GET("/headers?showAll=true")
        @Headers({"CustomHeader1: customHeaderValue1", "CustomHeader2: customHeaderValue2","CustomHeader4: customHeaderValue4"})
        Call<ResponseBody> testHeader(@Header("CustomHeader3") String customHeaderValue3);
    }

    public static void main(String args[]){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL)
                .build();

        BlogService service = retrofit.create(BlogService.class);
        Call<ResponseBody> call = service.testHeader("zhangyu");
        ResponseBodyPrinter.printResponseBody(call);

    }
}
