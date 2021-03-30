package com.androidufo.loading.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

/**
 * @ClassName SpinLineIndicator
 * @Description TODO
 * @Author admin
 * @Date 2021/3/29 9:26
 */
public class SpinLineIndicator extends SpinIndicator {
    private float mStartY;
    private float mStopY;

    public SpinLineIndicator(@ColorInt int color, @SpeedMode int speed) {
        super(color, speed);
    }

    public SpinLineIndicator(@ColorInt int color) {
        super(color);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        for (int i = 0; i < patternCount(); i++) {
            setPaintAlpha(darkPatternCount(), i);
            int save = canvas.save();
            float curRotateDegree = rotateDegree() * (i + getOffsetCount());
            canvas.rotate(curRotateDegree, mCenterX, mCenterY);
            canvas.drawLine(mCenterX, mStartY, mCenterX, mStopY, mPaint);
            canvas.restoreToCount(save);
        }
    }

    @Override
    protected void calculateDrawParams() {
        // SpinLine模式为线条整体长度为mFitSize的patternLenRate倍
        float patternLen = mFitSize * patternLenRate();
        // Paint.Cap.ROUND会为线条两头添加mStrokeWidth宽度的长度，因此要重新计算坐标
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        // 计算stroke宽度，为mFitSize的十四分之一
        float strokeWidth = mFitSize / 14.0f;
        // 绘制line的起点y坐标
        mStartY = strokeWidth;
        mPaint.setStrokeWidth(strokeWidth);
        // 绘制line的终点y坐标
        mStopY = patternLen - 2 * strokeWidth;
    }

    @Override
    protected int animationMaxValue() {
        return patternCount();
    }

    @Override
    protected int patternCount() {
        return 12;
    }

    @Override
    protected int darkPatternCount() {
        return 5;
    }

    @Override
    protected float patternLenRate() {
        return 0.4f;
    }
}
