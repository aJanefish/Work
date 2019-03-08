package zy.walk.com.thewalkers.manager;


import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

public class RobustPlayer implements MediaPlayer.OnCompletionListener {
    private Context mContext;
    private MediaPlayer mPlayer;

    public RobustPlayer(Context context) {
        mContext = context;
    }

    public void play(int resId) {
        synchronized (this) {
            if (mPlayer != null) {
                mPlayer.release();
                mPlayer = null;
            }

            mPlayer = MediaPlayer.create(mContext, resId);
            mPlayer.setOnCompletionListener(this);
            mPlayer.start();
        }
    }

    public void stop() {
        synchronized (this) {
            if (mPlayer != null) {
                mPlayer.release();
                mPlayer = null;
            }
        }
    }


    public void pause() {
        synchronized (this) {
            if (mPlayer != null) {
                mPlayer.pause();
            }
        }
    }

    public void start() {
        synchronized (this) {
            if (mPlayer != null) {
                mPlayer.start();
            }
        }
    }

    public void play(Uri uri) {
        synchronized (this) {
            if (mPlayer != null) {
                mPlayer.release();
                mPlayer = null;
            }

            mPlayer = MediaPlayer.create(mContext, uri);
            mPlayer.setOnCompletionListener(this);
            mPlayer.start();
        }
    }

    public int getDuration() {
        if(mPlayer == null){
            return 0;
        }
        return mPlayer.getDuration();
    }


    public void playFileName(String fileName) {
        synchronized (this) {

            if (TextUtils.isEmpty(fileName)) {
                return;
            }

            String filesPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/guard/mp3/";
            //String lastPath = filesPath + "speak.m4a";
            File file = new File(filesPath);
            File[] files = file.listFiles();

            String lastPath = "";
            for (File file1 : files) {
                if (file1.getName().contains(fileName)) {
                    lastPath = file1.getAbsolutePath();
                }
            }

            if (!TextUtils.isEmpty(lastPath)) {
                play(Uri.parse(lastPath));
            }

        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.i("RobustPlayer", "onCompletion");
        mPlayer.release();
        mPlayer = null;
    }

    public int getRawResId(String name) {
        return mContext.getResources().getIdentifier(name, "raw", mContext.getPackageName());
    }
}

