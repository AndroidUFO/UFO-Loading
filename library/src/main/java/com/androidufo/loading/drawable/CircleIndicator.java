package com.androidufo.loading.drawable;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

/**
 * @ClassName CircleIndicator
 * @Description TODO
 * @Author admin
 * @Date 2021/3/29 14:47
 */
public class CircleIndicator extends UFOLoadingDrawable {

    private int mRotateAngle;
    private float mRadius;
    private final RectF arcRect = new RectF();

    public CircleIndicator(@ColorInt int color, @SpeedMode int speed) {
        super(color, speed);
    }

    public CircleIndicator(@ColorInt int color) {
        super(color);
    }

    @Override
    protected void calculateDrawParams() {
        float strokeWidth = mFitSize / 8.0f;
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        float offset = strokeWidth / 2.0f;
        mRadius = mFitSize / 2.0f - offset;
        arcRect.left = offset;
        arcRect.right = arcRect.left + mRadius * 2;
        arcRect.top = offset;
        arcRect.bottom = arcRect.top + mRadius * 2;
    }

    @Override
    protected int animationMaxValue() {
        return TOTAL_ROTATE_DEGREE;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        mPaint.setAlpha(LIGHT_ALPHA);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
        mPaint.setAlpha(DARK_ALPHA);
        canvas.drawArc(arcRect, 270 + mRotateAngle, 90, false, mPaint);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int angle = (int) animation.getAnimatedValue();
        setRotateAngle(angle);
    }

    public void setRotateAngle(int rotateAngle) {
        if (mRotateAngle == rotateAngle) {
            return;
        }
        mRotateAngle = rotateAngle;
        invalidateSelf();
    }
}
