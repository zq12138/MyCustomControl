package com.example.admin.mycustomcontrol.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zq on 2017/2/28.
 */

public class ArcProgressBar extends View {

    public ArcProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private Paint mPaint;
    private RectF mRectF;
    private int mStrokeWidth = 20;
    int radius, centre;

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

    }


    Handler mHandler = new Handler();
    private int mProgress;

    public void setProgress(int progress) {
        this.mProgress = progress;
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                postInvalidate();
                mProgress++;
                if (mProgress < 160) {
                    mHandler.removeCallbacksAndMessages(null);
                    mHandler.postDelayed(this, 30);
                }

            }
        }, 30);
    }

    int width;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.width = w;
        mPaint.setTextSize(width / 8);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec) / 2);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        centre = getWidth() / 2;
        radius = centre - mStrokeWidth / 2;
        mRectF = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
        mPaint.setColor(Color.RED);
        canvas.drawArc(mRectF, 180, 180, false, mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawArc(mRectF, 180, mProgress, false, mPaint);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText("水水" + mProgress + "%水水", centre - mPaint.measureText("水水" + mProgress + "%水水") / 2, radius - 30, mPaint);


    }
}
