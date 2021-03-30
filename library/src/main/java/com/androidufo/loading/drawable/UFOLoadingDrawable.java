package com.androidufo.loading.drawable;

import android.animation.ValueAnimator;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.Nullable;

/**
 * @ClassName UFODrawable
 * @Description TODO
 * @Author admin
 * @Date 2021/3/29 9:13
 */
public abstract class UFOLoadingDrawable extends Drawable implements ValueAnimator.AnimatorUpdateListener {
    protected static final int TOTAL_ROTATE_DEGREE = 360;
    protected static final int DARK_ALPHA = 255;
    protected static final int LIGHT_ALPHA = 80;
    private static final int MIN_WIDTH = 65;
    private static final int MIN_HEIGHT = 65;
    protected Paint mPaint;
    protected Rect mRect;
    protected float mWidth;
    protected float mHeight;
    protected float mFitSize;
    protected float mCenterX;
    protected float mCenterY;
    protected @SpeedMode int mSpeed;
    private ValueAnimator mAnimator;

    public UFOLoadingDrawable(@ColorInt int color, @SpeedMode int speed) {
        mSpeed = speed;
        initPaint(color);
    }

    public UFOLoadingDrawable(@ColorInt int color) {
        this(color, SpeedMode.NORMAL);
    }

    private void initPaint(int color) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setDither(true);
        mPaint.setColor(color);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mRect = new Rect(left, top, right, bottom);
        mWidth = mRect.width();
        mHeight = mRect.height();
        mFitSize = Math.min(mWidth, mHeight);
        mCenterX = mWidth / 2.0f;
        mCenterY = mHeight / 2.0f;
        calculateDrawParams();
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicHeight() {
        return MIN_HEIGHT;
    }

    @Override
    public int getIntrinsicWidth() {
        return MIN_WIDTH;
    }

    public void start() {
        if (mAnimator == null) {
            mAnimator = ValueAnimator.ofInt(0, animationMaxValue());
            mAnimator.setDuration(mSpeed * 100);
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mAnimator.setRepeatMode(ValueAnimator.RESTART);
            mAnimator.addUpdateListener(this);
            mAnimator.setInterpolator(new LinearInterpolator());
        }
        if (!mAnimator.isStarted()) {
            mAnimator.start();
        }
    }

    public void stop() {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
    }

    protected abstract void calculateDrawParams();
    protected abstract int animationMaxValue();

    static final int NORMAL = 10;
    static final int SLOW = 15;
    static final int FAST = 5;

    @IntDef({SLOW, NORMAL, FAST})
    public @interface SpeedMode {
        int SLOW = SpinIndicator.SLOW;
        int NORMAL = SpinIndicator.NORMAL;
        int FAST = SpinIndicator.FAST;
    }
}
