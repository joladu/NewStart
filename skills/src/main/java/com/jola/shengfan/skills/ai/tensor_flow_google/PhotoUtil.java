package com.jola.shengfan.skills.ai.tensor_flow_google;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by lenovo on 2018/9/28.
 */

public class PhotoUtil {

    public static String startCamera(Activity activity,int requestcode){
        File outputImage = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/lite_mobile/", System.currentTimeMillis() + ".jpg");
        Log.d("jola","outputImage.getAbsolutePath: "+outputImage.getAbsolutePath());
        if (outputImage.exists()){
            outputImage.delete();
        }
        File out_path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/lite_mobile/");
        if (!out_path.exists()){
            out_path.mkdirs();
        }
        try {
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            imageUri = FileProvider.getUriForFile(activity, "com.jola.shengfan.skills.provider", outputImage);
        }else{
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,0);
        activity.startActivityForResult(intent,requestcode);
        return outputImage.getAbsolutePath();
    }

    public static void userPhoto(Activity activity,int requestCode){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("images/*");
        activity.startActivityForResult(intent,requestCode);

    }

    // get photo from Uri
    public static String get_path_from_URI(Context context, Uri uri) {
        String result;
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            result = uri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    // compress picture
    public static Bitmap getScaleBitmap(String filePath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, opt);
        int bmpWidth = opt.outWidth;
        int bmpHeight = opt.outHeight;
        int maxSize = 500;
        // compress picture with inSampleSize
        opt.inSampleSize = 1;
        while (true) {
            if (bmpWidth / opt.inSampleSize < maxSize || bmpHeight / opt.inSampleSize < maxSize) {
                break;
            }
            opt.inSampleSize *= 2;
        }
        opt.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, opt);
    }

    public static Bitmap compressBitmap(String imagePath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath,options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int maxSize = 500;
        options.inSampleSize = 1;
        while (true){
            if (outWidth / options.inSampleSize < maxSize || outHeight / options.inSampleSize < maxSize){
                break;
            }
            options.inSampleSize *= 2;
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imagePath,options);
    }


    // TensorFlow model，get predict data
    public static ByteBuffer getScaledMatrix(Bitmap bitmap, int[] ddims) {
        ByteBuffer imgData = ByteBuffer.allocateDirect(ddims[0] * ddims[1] * ddims[2] * ddims[3] * 4);
        imgData.order(ByteOrder.nativeOrder());
        // get image pixel
        int[] pixels = new int[ddims[2] * ddims[3]];
        Bitmap bm = Bitmap.createScaledBitmap(bitmap, ddims[2], ddims[3], false);
        bm.getPixels(pixels, 0, bm.getWidth(), 0, 0, ddims[2], ddims[3]);
        int pixel = 0;
        for (int i = 0; i < ddims[2]; ++i) {
            for (int j = 0; j < ddims[3]; ++j) {
                final int val = pixels[pixel++];
                imgData.putFloat(((((val >> 16) & 0xFF) - 128f) / 128f));
                imgData.putFloat(((((val >> 8) & 0xFF) - 128f) / 128f));
                imgData.putFloat((((val & 0xFF) - 128f) / 128f));
            }
        }

        if (bm.isRecycled()) {
            bm.recycle();
        }
        return imgData;
    }





}
