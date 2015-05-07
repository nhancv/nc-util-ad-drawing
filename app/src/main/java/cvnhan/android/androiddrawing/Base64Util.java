package cvnhan.android.androiddrawing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by cvnhan on 07-May-15.
 */
public class Base64Util {
    private static final String TAG = Base64Util.class.getSimpleName();
    public static String encode(String s) {
        byte[] data;
        String base64="";
        try {
            data = s.getBytes("UTF-8");
            base64= Base64.encodeToString(data, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64;
    }
    public static String encode(byte[] data) throws UnsupportedEncodingException {
        String base64= Base64.encodeToString(data, Base64.DEFAULT);
        return base64;
    }
    public static String encode(Bitmap bitmap) throws UnsupportedEncodingException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
        String base64= Base64.encodeToString(byteArray.toByteArray(), Base64.DEFAULT);
        return base64;
    }
    public static String decode(String s) {
        byte[] data = Base64.decode(s, Base64.DEFAULT);
        String text="";
        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }
    public static String decode(byte[] data) {
        String text="";
        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }
    public static Bitmap decodeToBitmap(String s){
        byte[] data = Base64.decode(s, Base64.DEFAULT);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = calculateSampleSize(data, 550, 400);
        options.inDither = true;
        options.inPurgeable = true;
        options.inJustDecodeBounds = false;
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        if (bmp == null) {
            Log.e(TAG, String.format("Error: BitmapFactory.decodeByteArray() failed"));
        }
        return bmp;
    }
    private static int calculateSampleSize(byte[] buffer, int requestWidth, int requestHeight) {
        int inSampleSize = 1;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(buffer, 0, buffer.length, options);
            final int height = options.outHeight;
            final int width = options.outWidth;

            if (height > requestHeight || width > requestWidth) {
                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                while ((halfWidth / inSampleSize) > requestHeight && (halfWidth / inSampleSize) > requestWidth) {
                    inSampleSize *= 2;
                }
            }
        } catch (Exception ex) {
            return 1;
        }
        return inSampleSize;
    }
}

