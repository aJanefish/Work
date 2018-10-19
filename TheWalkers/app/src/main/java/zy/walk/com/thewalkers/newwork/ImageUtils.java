package zy.walk.com.thewalkers.newwork;

import android.text.TextUtils;
import android.util.Base64;

class ImageUtils {

    public static String FileToBase64(String imagePath) {
        if (TextUtils.isEmpty(imagePath)) {
            return null;
        }
        String imgBase64 = "";
        try {
            byte[] data = FileIOUtils.readFile2BytesByChannel(imagePath);
            if (data == null || data.length == 0) {
                return "data:image/jpeg;base64,";
            }
            imgBase64 = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (Exception e) {
        }
        return "data:image/jpeg;base64," + imgBase64;
    }
}
