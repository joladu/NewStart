package com.jola.shengfan.toutiaojola.refresh;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;


public class BGARefreshLayout extends LinearLayout {

    private static final String TAG = BGARefreshLayout.class.getSimpleName();

    private int mTouchSlop;
    private Handler mHandler;
    private LinearLayout mWholeHeadView;

    private View mContentView;
    private AbsListView mAbsListView;
    private RecyclerView mRecycleView;
    private ScrollView mScrollView;
    private WebView mWebView;
    private View mNormalView;


    public BGARefreshLayout(Context context) {
        this(context,null);
    }

    public BGARefreshLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BGARefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mHandler = new Handler(Looper.getMainLooper());
        initWholeHeadView();
    }

    private void initWholeHeadView() {
        mWholeHeadView = new LinearLayout(getContext());
        mWholeHeadView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        mWholeHeadView.setOrientation(VERTICAL);
        addView(mWholeHeadView);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 2){
            throw new RuntimeException(TAG+"只能有一个子控件");
        }
        mContentView = getChildAt(1);
        if (mContentView instanceof AbsListView){
            mAbsListView = (AbsListView)mContentView;
        }else if (mContentView instanceof RecyclerView){
            mRecycleView = (RecyclerView)mContentView;
        }else if (mContentView instanceof ScrollView){
            mScrollView = (ScrollView)mContentView;
        }else if (mContentView instanceof WebView){
            mWebView = (WebView)mContentView;
        }
//        else if (mContentView instanceof BGAStickyNavLayout) {
//        mStickyNavLayout = (BGAStickyNavLayout) mContentView;
//        mStickyNavLayout.setRefreshLayout(this);
//        }
        else if (mContentView instanceof FrameLayout){
            FrameLayout frameLayout = (FrameLayout) this.mContentView;
            View childView = frameLayout.getChildAt(0);
            if (childView instanceof RecyclerView){
                mRecycleView = (RecyclerView)childView;
            }
        }else {
            mNormalView = mContentView;
            mNormalView.setClickable(true);
        }


    }
}
