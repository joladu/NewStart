package com.jola.shengfan.skills.pull_refresh_custom;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jola.shengfan.skills.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by lenovo on 2018/11/9.
 */

public class CustomRefreshHeader extends LinearLayout implements RefreshHeader {


    private ImageView mImageView;
    private AnimationDrawable refreshAnim;

    private boolean hasSetPullDownAnim = false;
    private AnimationDrawable pullDownAnim;

    public CustomRefreshHeader(Context context) {
        this(context, null);
    }

    public CustomRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.view_head_refresh_custom, this);
        mImageView = view.findViewById(R.id.iv_refresh_header);
    }


    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
//        Log.e("jola","onInitialized");
    }



    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        Log.e("jola","onReleased");

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
//        Log.e("jola","onStartAnimator");
    }



    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case PullDownToRefresh:
                mImageView.setImageResource(R.drawable.commonui_pull_image);
                break;
            case Refreshing:
                mImageView.setImageResource(R.drawable.anim_pull_refreshing);
                refreshAnim = (AnimationDrawable) mImageView.getDrawable();
                refreshAnim.start();
                break;
            case RefreshReleased:
                break;
        }
    }


    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
//        Log.e("jola","isDragging:"+isDragging+"; percent:"+percent+"; offset:"+offset+"; height:"+height+"; maxDragHeight:"+maxDragHeight);
        if (percent < 1){
            mImageView.setScaleX(percent);
            mImageView.setScaleY(percent);
            if (hasSetPullDownAnim){
                hasSetPullDownAnim = false;
            }
        }

        if (percent >= 1.0){
            if (!hasSetPullDownAnim){
//                Log.e("jola","pullDownAnim.start()");
                mImageView.setImageResource(R.drawable.anim_pull_end);
                pullDownAnim = (AnimationDrawable) mImageView.getDrawable();
                pullDownAnim.start();

                hasSetPullDownAnim = true;

            }
        }

    }


    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
//        Log.e("jola","onFinish");
        if (null != pullDownAnim && pullDownAnim.isRunning()){
            pullDownAnim.stop();
        }
        if (null != refreshAnim && refreshAnim.isRunning()){
            refreshAnim.stop();
        }
        hasSetPullDownAnim = false;
        return 0;
    }




}
