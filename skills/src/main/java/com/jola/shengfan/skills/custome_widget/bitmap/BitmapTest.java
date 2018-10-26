package com.jola.shengfan.skills.custome_widget.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jola.shengfan.skills.R;

/**
 * Created by lenovo on 2018/10/26.
 */

public class BitmapTest {
    public void testBitmapSize(Context context){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPreferredConfig = Bitmap.Config.ALPHA_8;

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_fast_forward_64dp, options);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int byteCount = bitmap.getByteCount();

        options.inJustDecodeBounds = true;

    }
}
