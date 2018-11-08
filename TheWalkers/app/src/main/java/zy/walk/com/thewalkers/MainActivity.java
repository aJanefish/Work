package zy.walk.com.thewalkers;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import zy.walk.com.thewalkers.imagesandanimations.BitmapUtils;
import zy.walk.com.thewalkers.json.JsonUtils;
import zy.walk.com.thewalkers.newwork.Main2Activity;
import zy.walk.com.thewalkers.newwork.OkhttpUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    private ImageView myimage;

    int number = 1000;
    Drawable[] array;

    Bitmap bitmap[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myimage = findViewById(R.id.myimage);
        drawableTest();
        //bitmapTest();

        if (Build.VERSION.SDK_INT >= 25) {
//            ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(this, "zhangyu")
//                    .setShortLabel("nihao")
//                    .setLongLabel("")
//                    .setIcon(Icon.createWithResource(this, R.drawable.add))
//                    .setIntent(new Intent(this, MainActivity.class))
//                    .build();

            ShortcutInfo info = createShortcutForUrl("www.baid.com");

            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
            //这样就可以通过长按图标显示出快捷方式了
            shortcutManager.updateShortcuts(Arrays.asList(info));

        }


    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    private ShortcutInfo createShortcutForUrl(String urlAsString) {
        Log.i(TAG, "createShortcutForUrl: " + urlAsString);

        final ShortcutInfo.Builder b = new ShortcutInfo.Builder(this, urlAsString);

        final Uri uri = Uri.parse(urlAsString);
        b.setIntent(new Intent(Intent.ACTION_VIEW, uri));

        setSiteInformation(b, uri);
        setExtras(b);

        return b.build();
    }

    private static final String EXTRA_LAST_REFRESH =
            "com.example.android.shortcutsample.EXTRA_LAST_REFRESH";

    @TargetApi(Build.VERSION_CODES.N_MR1)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ShortcutInfo.Builder setExtras(ShortcutInfo.Builder b) {
        final PersistableBundle extras = new PersistableBundle();
        extras.putLong(EXTRA_LAST_REFRESH, System.currentTimeMillis());
        b.setExtras(extras);
        return b;
    }

    @RequiresApi(api = Build.VERSION_CODES.N_MR1)
    private ShortcutInfo.Builder setSiteInformation(ShortcutInfo.Builder b, Uri uri) {
        // TODO Get the actual site <title> and use it.
        // TODO Set the current locale to accept-language to get localized title.
        //b.setShortLabel(uri.getHost());
        b.setShortLabel(uri.toString());
        b.setLongLabel(uri.toString());

        Bitmap bmp = fetchFavicon(uri);
        if (bmp != null) {
            b.setIcon(Icon.createWithBitmap(bmp));
        } else {
            b.setIcon(Icon.createWithResource(toString(), R.drawable.link));
        }

        return b;
    }


    private Bitmap fetchFavicon(Uri uri) {
        final Uri iconUri = uri.buildUpon().path("favicon.ico").build();
        Log.i(TAG, "Fetching favicon from: " + iconUri);

        InputStream is = null;
        BufferedInputStream bis = null;
        try
        {
            URLConnection conn = new URL(iconUri.toString()).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 8192);
            return BitmapFactory.decodeStream(bis);
        } catch (IOException e) {
            Log.w(TAG, "Failed to fetch favicon from " + iconUri, e);
            return null;
        }
    }

    private void bitmapTest() {
        bitmap = new Bitmap[number];

        for (int i = 0; i < number; i++)
        {
            Log.e(TAG, "测试第" + (i+1) + "张图片");
            bitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.gril);
        }
    }

    private void drawableTest() {
        array = new BitmapDrawable[number];

            for (int i = 0; i < number; i++) {
                Log.e(TAG, "测试第" + (i + 1) + "张图片");
                array[i] = getApplicationContext().getResources().getDrawable(R.drawable.gril);
            }

        }

        public void test(View view) {

        myimage.setImageBitmap(BitmapUtils.loadbitmap(this));
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gril);
        //Log.d(TAG,""+bitmap);
        //myimage.setImageBitmap(bitmap);
    }

    public void TestNEwWORk(View view) {
        startActivity(new Intent(getApplicationContext(),Main2Activity.class));
    }

    public void JsonTest(View view) {
        JsonUtils.test1();
        JsonUtils.test2();
        JsonUtils.test3();
        JsonUtils.test4();
    }

    public void getAuxiliaryAll(View view) {
        OkhttpUtils.getAuxiliaryAll();
    }

    public void other(View view) {
        startActivity(new Intent(getApplicationContext(),OtherActivity.class));
    }
}
