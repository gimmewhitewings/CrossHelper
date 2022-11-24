package com.example.crosshelper.storage;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class StorageController {
    public static final String STORAGE_NAME = "STORAGE_NAME";
    public static final String BITMAP = "BITMAP";
    public static final String ACTIVATED_PIXELS = "ACTIVATED_PIXELS";
    public static SharedPreferences settings;
    public static SharedPreferences.Editor editor;

    public static String encodeToBase64(Bitmap image) {
        //Bitmap compressedImage = image.copy();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] b = stream.toByteArray();

        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    public static Bitmap decodeToBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
