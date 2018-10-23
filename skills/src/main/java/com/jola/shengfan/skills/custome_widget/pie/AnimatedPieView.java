package com.jola.shengfan.skills.custome_widget.pie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lenovo on 2018/10/22.
 */

public class AnimatedPieView extends View {

    private Paint mPaint;
    private RectF mRectF;

    public AnimatedPieView(Context context) {
        this(context,null);
    }

    public AnimatedPieView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AnimatedPieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public AnimatedPieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(80);


        mRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth() - getPaddingStart() - getPaddingEnd();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        float radius = (float)(Math.min(width, height) / 2 * 0.85);


        canvas.translate(width/2,height/2);

        mRectF.set(-radius,-radius,radius,radius);

        mPaint.setColor(Color.GREEN);
        canvas.drawArc(mRectF,0,120,false,mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawArc(mRectF,120,120,false,mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawArc(mRectF,240,120,false,mPaint);

    }



}
