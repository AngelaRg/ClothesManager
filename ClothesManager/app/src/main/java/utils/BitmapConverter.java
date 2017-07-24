package utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Cesar on 11/8/2015.
 * fuente:http://stackoverflow.com/questions/11790104/how-to-storebitmap-image-and-retrieve-image-from-sqlite-database-in-android
 */
public class BitmapConverter {

    /**
     * Convierte de bitmap a byte[]
     * @param bitmap
     * @return
     */
    public static byte[] getBytes(Bitmap bitmap) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            return stream.toByteArray();
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Convierte de byte[] a bitmap
     * @param image
     * @return
     */
    public static Bitmap getImage(byte[] image) {
        try {
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        } catch (Exception e){
            return null;
        }
    }
}
