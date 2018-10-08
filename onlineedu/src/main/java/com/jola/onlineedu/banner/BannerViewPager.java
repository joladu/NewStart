package com.jola.onlineedu.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.jola.onlineedu.util.SystemUtil;

/**
 * Created by jola on 2018/9/29.
 *  包含banner 和 indication
 */

public class BannerViewPager extends FrameLayout implements ViewPager.OnPageChangeListener{

    private Context mContext;

    private ViewPager viewPager;
    private BannerPagerAdapter mAdapter;
    private ViewpagerIndicator mIndicator;

    private boolean isFirstVisible;
    private int mCurrentPostion;


    public BannerViewPager(@NonNull Context context) {
        this(context,null);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {

        viewPager = new ViewPager(mContext);
        ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewPager.LayoutParams.MATCH_PARENT;
        viewPager.setLayoutParams(layoutParams);


        mIndicator = new ViewpagerIndicator(mContext);
        LayoutParams layoutParamsIndicator = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParamsIndicator.gravity = Gravity.CENTER | Gravity.BOTTOM;
        layoutParamsIndicator.bottomMargin = SystemUtil.dp2px(10);
        mIndicator.setLayoutParams(layoutParamsIndicator);

        isFirstVisible = true;

    }

    public void setAdapter(BannerPagerAdapter adapter,int count){
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        mAdapter = adapter;
        mIndicator.setItemCount(count);
        addView(viewPager);
        addView(mIndicator);
    }

    private void setIndicator(int position,float offset){
        mIndicator.setPositionAndOffset(position,offset);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        setIndicator(position,positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPostion = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
