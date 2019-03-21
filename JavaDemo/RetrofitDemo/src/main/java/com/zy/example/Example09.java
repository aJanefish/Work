package com.zy.example;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * 接受String数据
 * */
public class Example09 {

    public interface BlogService {
        @GET("blog/{id}")
        Call<String> getBlog(@Path("id") int id);
    }


    /**
     * 自定义Converter实现RequestBody到String的转换
     */
    public static class StringConverter implements Converter<ResponseBody, String> {

        public static final StringConverter INSTANCE = new StringConverter();

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }

    /**
     * 用于向Retrofit提供StringConverter
     */
    public static class StringConverterFactory extends Converter.Factory {

        public static final StringConverterFactory INSTANCE = new StringConverterFactory();

        public static StringConverterFactory create() {
            return INSTANCE;
        }

        // 我们只关实现从ResponseBody 到 String 的转换，所以其它方法可不覆盖
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            if (type == String.class) {
                return StringConverter.INSTANCE;
            }
            //其它类型我们不处理，返回null就行
            return null;
        }
    }

    public static void main(String[] args) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:4567/")
                // 如是有GSon这类的Converter 一定要放在其它前面
                .addConverterFactory(StringConverterFactory.create())
//                .addConverterFactory(new Converter.Factory() {
//                    @Override
//                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//                        return new Converter<ResponseBody, String>() {
//                            @Override
//                            public String convert(ResponseBody value) throws IOException {
//                                return value.string();
//                            }
//                        };
//                    }
//                })
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BlogService service = retrofit.create(BlogService.class);
        Call<String> call = service.getBlog(2);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println("onResponse:"+call.request());

                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
