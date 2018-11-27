package com.jola.onlineedu.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by lenovo on 2018/11/27.
 */

public class CustomRecycleViewHeightMax extends RecyclerView {

    public CustomRecycleViewHeightMax(Context context) {
        super(context);
    }

    public CustomRecycleViewHeightMax(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecycleViewHeightMax(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandMesaureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandMesaureSpec);
    }
}
