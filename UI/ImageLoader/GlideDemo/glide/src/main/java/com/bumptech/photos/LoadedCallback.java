package com.bumptech.photos;

import android.graphics.Bitmap;

public interface LoadedCallback {
    public void loadCompleted(Bitmap loaded);
    public void onLoadFailed(Exception e);
}
