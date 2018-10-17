//package com.jola.shengfan.toutiaojola.refresh;
//
//import android.content.Context;
//import android.os.Handler;
//import android.os.Looper;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.RecyclerView;
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.ViewConfiguration;
//import android.webkit.WebView;
//import android.widget.AbsListView;
//import android.widget.FrameLayout;
//import android.widget.LinearLayout;
//import android.widget.ScrollView;
//
//
//public class BGARefreshLayout extends LinearLayout {
//
//    private static final String TAG = BGARefreshLayout.class.getSimpleName();
//
//    private BGARefreshViewHolder mRefreshViewHolder;
//
//    private LinearLayout mWholeHeaderView;
//
//
//    private View mRefreshHeaderView;
//
//    private int mRefreshHeaderViewHeight;
//    private int mMinWholeHeaderViewPaddingTop;
//    private int mMaxWholeHeaderViewPaddingTop;
//
//    private View mLoadMoreFooterView;
//    private int mLoadMoreFooterViewHeight;
//
//    private int mTouchSlop;
//    private Handler mHandler;
//    private LinearLayout mWholeHeadView;
//
//    private View mContentView;
//    private AbsListView mAbsListView;
//    private RecyclerView mRecyclerView;
//    private ScrollView mScrollView;
//    private WebView mWebView;
//    private View mNormalView;
//
//    private boolean mIsInitedContentViewScrollListener = false;
//
//    private boolean mIsLoadingMore = false;
//
//    private RefreshStatus mCurrentRefreshStatus = RefreshStatus.IDLE;
//
//    private BGARefreshLayoutDelegate mDelegate;
//
//
//
//    public BGARefreshLayout(Context context) {
//        this(context,null);
//    }
//
//    public BGARefreshLayout(Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs,0);
//    }
//
//    public BGARefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        setOrientation(VERTICAL);
//        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
//        mHandler = new Handler(Looper.getMainLooper());
//        initWholeHeadView();
//    }
//
//    private void initWholeHeadView() {
//        mWholeHeadView = new LinearLayout(getContext());
//        mWholeHeadView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
//        mWholeHeadView.setOrientation(VERTICAL);
//        addView(mWholeHeadView);
//    }
//
//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        if (getChildCount() != 2){
//            throw new RuntimeException(TAG+"只能有一个子控件");
//        }
//        mContentView = getChildAt(1);
//        if (mContentView instanceof AbsListView){
//            mAbsListView = (AbsListView)mContentView;
//        }else if (mContentView instanceof RecyclerView){
//            mRecyclerView = (RecyclerView)mContentView;
//        }else if (mContentView instanceof ScrollView){
//            mScrollView = (ScrollView)mContentView;
//        }else if (mContentView instanceof WebView){
//            mWebView = (WebView)mContentView;
//        }
////        else if (mContentView instanceof BGAStickyNavLayout) {
////        mStickyNavLayout = (BGAStickyNavLayout) mContentView;
////        mStickyNavLayout.setRefreshLayout(this);
////        }
//        else if (mContentView instanceof FrameLayout){
//            FrameLayout frameLayout = (FrameLayout) this.mContentView;
//            View childView = frameLayout.getChildAt(0);
//            if (childView instanceof RecyclerView){
//                mRecyclerView = (RecyclerView)childView;
//            }
//        }else {
//            mNormalView = mContentView;
//            mNormalView.setClickable(true);
//        }
//    }
//
//    public void setRefreshViewHolder(BGARefreshViewHolder refreshViewHolder) {
//        mRefreshViewHolder = refreshViewHolder;
//        mRefreshViewHolder.setRefreshLayout(this);
//        initRefreshHeaderView();
//        initLoadMoreFooterView();
//    }
//
//    private void initRefreshHeaderView() {
//        mRefreshHeaderView = mRefreshViewHolder.getRefreshHeaderView();
//        if (mRefreshHeaderView != null) {
//            mRefreshHeaderView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
//
//            mRefreshHeaderViewHeight = mRefreshViewHolder.getRefreshHeaderViewHeight();
//            mMinWholeHeaderViewPaddingTop = -mRefreshHeaderViewHeight;
//            mMaxWholeHeaderViewPaddingTop = (int) (mRefreshHeaderViewHeight * mRefreshViewHolder.getSpringDistanceScale());
//
//            mWholeHeaderView.setPadding(0, mMinWholeHeaderViewPaddingTop, 0, 0);
//            mWholeHeaderView.addView(mRefreshHeaderView, 0);
//        }
//    }
//
//    private void initLoadMoreFooterView() {
//        mLoadMoreFooterView = mRefreshViewHolder.getLoadMoreFooterView();
//        if (mLoadMoreFooterView != null) {
//            // 测量上拉加载更多控件的高度
//            mLoadMoreFooterView.measure(0, 0);
//            mLoadMoreFooterViewHeight = mLoadMoreFooterView.getMeasuredHeight();
//            mLoadMoreFooterView.setVisibility(GONE);
//        }
//    }
//
//    @Override
//    protected void onAttachedToWindow() {
//        super.onAttachedToWindow();
//
//        // 被添加到窗口后再设置监听器，这样开发者就不必烦恼先初始化RefreshLayout还是先设置自定义滚动监听器
//        if (!mIsInitedContentViewScrollListener && mLoadMoreFooterView != null) {
//            setRecyclerViewOnScrollListener();
//            setAbsListViewOnScrollListener();
//
//            addView(mLoadMoreFooterView, getChildCount());
//
//            mIsInitedContentViewScrollListener = true;
//        }
//    }
//
//    private void setRecyclerViewOnScrollListener() {
//        if (mRecyclerView != null) {
//            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                    if ((newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_SETTLING) && shouldHandleRecyclerViewLoadingMore(mRecyclerView)) {
//                        beginLoadingMore();
//                    }
//                }
//            });
//        }
//    }
//
//    public boolean shouldHandleRecyclerViewLoadingMore(RecyclerView recyclerView) {
//        if (mIsLoadingMore || mCurrentRefreshStatus == RefreshStatus.REFRESHING || mLoadMoreFooterView == null || mDelegate == null || recyclerView.getAdapter() == null || recyclerView.getAdapter().getItemCount() == 0) {
//            return false;
//        }
//        return BGARefreshScrollingUtil.isRecyclerViewToBottom(recyclerView);
//    }
//
//    public interface BGARefreshLayoutDelegate {
//        /**
//         * 开始刷新
//         */
//        void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout);
//
//        /**
//         * 开始加载更多。由于监听了ScrollView、RecyclerView、AbsListView滚动到底部的事件，所以这里采用返回boolean来处理是否加载更多。否则使用endLoadingMore方法会失效
//         *
//         * @param refreshLayout
//         * @return 如果要开始加载更多则返回true，否则返回false。（返回false的场景：没有网络、一共只有x页数据并且已经加载了x页数据了）
//         */
//        boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout);
//    }
//
//    public enum RefreshStatus {
//        IDLE, PULL_DOWN, RELEASE_REFRESH, REFRESHING
//    }
//
//
//
//}
