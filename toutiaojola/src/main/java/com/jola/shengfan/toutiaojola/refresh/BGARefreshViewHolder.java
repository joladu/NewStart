//package com.jola.shengfan.toutiaojola.refresh;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.AnimationDrawable;
//import android.support.annotation.ColorRes;
//import android.support.annotation.DrawableRes;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.jola.shengfan.toutiaojola.R;
//
///**
// * Created by lenovo on 2018/10/17.
// */
//
//public abstract class BGARefreshViewHolder {
//
//
//    private static final float PULL_DISTANCE_SCALE = 1.8f;
//    private float mPullDistanceScale = PULL_DISTANCE_SCALE;
//
//    private static final float SPRING_DISTANCE_SCALE = 0.4f;
//    private float mSpringDistanceScale = SPRING_DISTANCE_SCALE;
//
//    protected Context mContext;
//
//
//    protected BGARefreshLayout mRefreshLayout;
//
//
//    protected View mRefreshHeaderView;
//
//    protected View mLoadMoreFooterView;
//
//    protected TextView mFooterStatusTv;
//
//    protected ImageView mFooterChrysanthemumIv;
//
//    protected AnimationDrawable mFooterChrysanthemumAd;
//
//    protected String mLodingMoreText = "加载中...";
//
//    private boolean mIsLoadingMoreEnabled = true;
//
//    private int mLoadMoreBackgroundColorRes = -1;
//
//    private int mLoadMoreBackgroundDrawableRes = -1;
//
//    protected int mRefreshViewBackgroundColorRes = -1;
//
//    protected int mRefreshViewBackgroundDrawableRes = -1;
//
//    private int mTopAnimDuration = 500;
//
//
//
//    public BGARefreshViewHolder(Context context, boolean isLoadingMoreEnabled) {
//        mContext = context;
//        mIsLoadingMoreEnabled = isLoadingMoreEnabled;
//    }
//
//    public void setLoadingMoreText(String loadingMoreText) {
//        mLodingMoreText = loadingMoreText;
//    }
//
//    public void setLoadMoreBackgroundColorRes(@ColorRes int loadMoreBackgroundColorRes) {
//        mLoadMoreBackgroundColorRes = loadMoreBackgroundColorRes;
//    }
//
//    public void setLoadMoreBackgroundDrawableRes(@DrawableRes int loadMoreBackgroundDrawableRes) {
//        mLoadMoreBackgroundDrawableRes = loadMoreBackgroundDrawableRes;
//    }
//
//    public void setRefreshViewBackgroundColorRes(@ColorRes int refreshViewBackgroundColorRes) {
//        mRefreshViewBackgroundColorRes = refreshViewBackgroundColorRes;
//    }
//
//    public void setRefreshViewBackgroundDrawableRes(@DrawableRes int refreshViewBackgroundDrawableRes) {
//        mRefreshViewBackgroundDrawableRes = refreshViewBackgroundDrawableRes;
//    }
//
//    public int getTopAnimDuration() {
//        return mTopAnimDuration;
//    }
//
//    public void setTopAnimDuration(int topAnimDuration) {
//        mTopAnimDuration = topAnimDuration;
//    }
//
//    public View getLoadMoreFooterView() {
//        if (!mIsLoadingMoreEnabled){
//            return null;
//        }
//        if (mLoadMoreFooterView == null){
//            mLoadMoreFooterView = View.inflate(mContext, R.layout.view_normal_refresh_footer,null);
//            mLoadMoreFooterView.setBackgroundColor(Color.TRANSPARENT);
//        }
//        if (mLoadMoreBackgroundColorRes != -1) {
//            mLoadMoreFooterView.setBackgroundResource(mLoadMoreBackgroundColorRes);
//        }
//        if (mLoadMoreBackgroundDrawableRes != -1) {
//            mLoadMoreFooterView.setBackgroundResource(mLoadMoreBackgroundDrawableRes);
//        }
//        mFooterStatusTv = (TextView) mLoadMoreFooterView.findViewById(R.id.tv_normal_refresh_footer_status);
//        mFooterChrysanthemumIv = (ImageView) mLoadMoreFooterView.findViewById(R.id.iv_normal_refresh_footer_chrysanthemum);
//        mFooterChrysanthemumAd = (AnimationDrawable) mFooterChrysanthemumIv.getDrawable();
//        mFooterStatusTv.setText(mLodingMoreText);
//        return mLoadMoreFooterView;
//    }
//
//
//    public abstract View getRefreshHeaderView();
//
//    public abstract void handleScale(float scale, int moveYDistance);
//
//    public abstract void changeToIdle();
//
//    public abstract void changeToPullDown();
//
//    public abstract void changeToReleaseRefresh();
//
//    public abstract void changeToRefreshing();
//
//    public abstract void onEndRefreshing();
//
//    public float getPaddingTopScale() {
//        return mPullDistanceScale;
//    }
//
//    public void setPullDistanceScale(float pullDistanceScale) {
//        mPullDistanceScale = pullDistanceScale;
//    }
//
//    public float getSpringDistanceScale() {
//        return mSpringDistanceScale;
//    }
//
//    public void setSpringDistanceScale(float springDistanceScale) {
//        if (springDistanceScale < 0) {
//            throw new RuntimeException("下拉刷新控件paddingTop的弹簧距离与下拉刷新控件高度的比值springDistanceScale不能小于0");
//        }
//        mSpringDistanceScale = springDistanceScale;
//    }
//
//    public boolean canChangeToRefreshingStatus() {
//        return false;
//    }
//
//
//    public void changeToLoadingMore() {
//        if (mIsLoadingMoreEnabled && mFooterChrysanthemumAd != null) {
//            mFooterChrysanthemumAd.start();
//        }
//    }
//
//    public void onEndLoadingMore() {
//        if (mIsLoadingMoreEnabled && mFooterChrysanthemumAd != null) {
//            mFooterChrysanthemumAd.stop();
//        }
//    }
//
//    public int getRefreshHeaderViewHeight() {
//        if (mRefreshHeaderView != null) {
//            // 测量下拉刷新控件的高度
//            mRefreshHeaderView.measure(0, 0);
//            return mRefreshHeaderView.getMeasuredHeight();
//        }
//        return 0;
//    }
//
////    public void startChangeWholeHeaderViewPaddingTop(int distance) {
////        mRefreshLayout.startChangeWholeHeaderViewPaddingTop(distance);
////    }
//
//    public void setRefreshLayout(BGARefreshLayout refreshLayout) {
//        mRefreshLayout = refreshLayout;
//    }
//
//
//
//
//
//
//
//
//
//}
