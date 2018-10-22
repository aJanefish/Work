package zy.walk.com.thewalkers.newwork;




import android.util.Log;


import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class PhotoService {
    private static final String UPLOAD_URL = "https://mice.singou.mo/api/tool/SaveImg";

    private OkHttpClient okHttpClient;
    private UploadCallback mCallback;

    private Callback mUploadCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.w("Singou", "onFailure", e);
            if (mCallback != null) {
                mCallback.onError();
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Log.i("Singou", "onResponse: " + response.code());
            Log.i("Singou", "onResponse: " + response.message());
            Log.i("Singou", "onResponse: " + response.toString());
            ResponseBody body = response.body();
            Log.i("Singou", "onResponse: " + body.string());

        }
    };

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public PhotoService() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        okHttpClient = builder.build();
    }

    public void uploadFile(final File file, final UploadCallback callback) {
        new Thread() {
            @Override
            public void run() {
                mCallback = callback;
                RequestBody photoPart = RequestBody.create(MediaType.parse("image/jpg"), file);

                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                //builder.addFormDataPart("file", file.getName(), photoPart);
                //builder.addFormDataPart("data", ImageUtils.FileToBase64(file.getPath()));
                JSONObject body = new JSONObject();
                try {

                    body.put("data", ImageUtils.FileToBase64(file.getPath()));

                }catch (Exception e){

                }


                RequestBody sss = RequestBody.create(JSON, body.toString());
                //RequestBody body = builder.build();

                Request request = new Request.Builder().url(UPLOAD_URL).post(sss).build();
                okHttpClient.newCall(request).enqueue(mUploadCallback);



            }
        }.start();
    }



    public interface UploadCallback {
        void onComplete(String qrCodeUrl);
        void onError();
    }
}

