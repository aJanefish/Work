package zy.walk.com.thewalkers.newwork;


import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.R;

public class Main2Activity extends AppCompatActivity {

    private String TAG = "Main2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void test(View view) {

        ///storage/emulated/0/test.png

        File file = new File("/storage/emulated/0/Screenshots/Screenshot_20180926-123140.png");
///storage/emulated/0/test.png

        Log.d("Singou", "test:---"+Environment.getExternalStorageDirectory().getAbsolutePath());
        PhotoService photoService = new PhotoService();
        photoService.uploadFile(file, new PhotoService.UploadCallback() {
            @Override
            public void onComplete(String qrCodeUrl) {

                Log.d(TAG, "onComplete:" + qrCodeUrl);
//                try {
//                    final Bitmap qrcode = HttpUtils.getBitmap(qrCodeUrl);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            mProgressBar.setVisibility(View.GONE);
//                            mImageView.setVisibility(View.VISIBLE);
//                            mImageView.setImageBitmap(qrcode);
//                            mStateView.setText("上傳相片成功，請使用微信掃描二維碼下載照片");
//                            //TtsService.getInstance().tts("請使用微信掃描二維碼下載照片。");
//                            Director.getChatbotManager().tts("請使用微信掃描二維碼下載照片。");
//                        }
//                    });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onError() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mProgressBar.setVisibility(View.GONE);
//                        mImageView.setVisibility(View.VISIBLE);
//                        mImageView.setImageResource(R.mipmap.ic_error_outline_black_48dp);
//                        mStateView.setText("上傳失敗，請稍後重試。");
//                        mContainer.setVisibility(View.VISIBLE);
//                        mCloseButton.setVisibility(View.GONE);
//                    }
//                });
            }
        });
    }
}
