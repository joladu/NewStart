package com.jola.newnews.component;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by lenovo on 2018/7/19.
 */

public class ImageLoader {
    public static void load(Context context, String url, ImageView iv){
        Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
    }
}
