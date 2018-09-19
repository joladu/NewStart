package com.jola.shengfan.skills.picture_in_picture;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.jola.shengfan.skills.R;

/**
 * Created by lenovo on 2018/9/19.
 */

public class MovieView extends RelativeLayout {

    public MovieView(Context context) {
        this(context,null);
    }

    public MovieView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MovieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.BLACK);

        inflate(context, R.layout.view_movie,this);



    }




}
