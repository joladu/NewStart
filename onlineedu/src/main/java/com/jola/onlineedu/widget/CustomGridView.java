package com.jola.onlineedu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by jola on 2018/9/1.
 */

public class CustomGridView extends GridView {

    public CustomGridView(Context context) {
        this(context,null);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 重写该方法、使ListView能在所有可以滚动的视图中使用，比如ListView嵌套GridView，或者ScrollView潜逃GridView
         */
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
