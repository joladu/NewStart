package com.jola.onlineedu.banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.jola.onlineedu.app.MyLog;

/**
 * Created by lenovo on 2018/9/30.
 */

public class ViewpagerIndicator extends LinearLayout {

    private static final int DEFAULT_ITEMCOUNT = 1;
    private static final int DEFAULT_RADIUS = 10;
    private static final int DEFAULT_PADDING = 10;

    private int mItemCount = DEFAULT_ITEMCOUNT;
    private int mRadius = DEFAULT_RADIUS;
    private int mPadding = DEFAULT_PADDING;
    private int mDistanceBtwItem = 2 * mRadius + mPadding;

    private int mCurrentPostion = 0;
    private float mPositionOffset;


    private Context mContext;
    private Paint mPaint;
    private MovieView mMovieView;

    public ViewpagerIndicator(Context context) {
        this(context,null);
    }

    public ViewpagerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ViewpagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setWillNotDraw(false);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);

        mMovieView = new MovieView(mContext);
        addView(mMovieView);

    }

    public void setItemCount(int count){
        mItemCount = count;
        requestLayout();
    }

    public void setRadius(int radius){
        mRadius = radius;
        mDistanceBtwItem = mRadius * 2 + mPadding;
        requestLayout();
    }

    public void setPadding(int padding){
        mPadding = padding;
        mDistanceBtwItem = mRadius * 2 + mPadding;
        requestLayout();
    }

    public void setPositionAndOffset(int position,float offset){
        mCurrentPostion = position;
        mPositionOffset = offset;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        MyLog.logMy("ViewpagerIndicator : onMeasure");

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((mRadius * 2+mPadding) * mItemCount + mPadding,mRadius * 2 + mPadding * 2);

        MyLog.logMy(" setMeasuredDimension"+ "** width:"+(mRadius * 2+mPadding) * mItemCount + mPadding+"*****height:"+mRadius * 2 + mPadding * 2);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mMovieView.layout(
                (int) (mPadding + mDistanceBtwItem * (mCurrentPostion + mPositionOffset)),
                mPadding,
                (int) (mDistanceBtwItem * (1 + mCurrentPostion + mPositionOffset)),
                mPadding + mRadius * 2);
//        MyLog.logMy("**left:"+(int) (mPadding + mDistanceBtwItem * (mCurrentPostion + mPositionOffset))+"**top:"+ mPadding+"**right:"+(int) (mDistanceBtwItem * (1 + mCurrentPostion + mPositionOffset))+"**bottom:"+mPadding + mRadius * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        MyLog.logMy("ViewpagerIndicator : onDraw");
        super.onDraw(canvas);
        for (int i = 0 ; i < mItemCount ;i++){
            canvas.drawCircle(mRadius + mPadding + mRadius * 2 * i + mPadding * i,mRadius +mPadding,mRadius,mPaint);

//            MyLog.logMy("circle i="+i+"**x:"+mRadius + mPadding + mRadius * 2 * i + mPadding * i+"**y:"+mRadius +mPadding+"**radius:"+mRadius);

        }
    }

    private class MovieView extends View{

        private final Paint mPaint;

        public MovieView(Context context) {
            super(context);
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.GREEN);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//            MyLog.logMy("MovieView : onMeasure");
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            setMeasuredDimension(mRadius * 2,mRadius * 2);

//            MyLog.logMy(" width height;"+ mRadius * 2);

        }

        @Override
        protected void onDraw(Canvas canvas) {
//            MyLog.logMy("MovieView : onDraw");

            canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);

//            MyLog.logMy(" canvas.drawCircle(mRadius,mRadius,mRadius,mPaint);");

        }

    }


}
