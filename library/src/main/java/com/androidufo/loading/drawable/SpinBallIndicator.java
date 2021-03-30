package com.androidufo.loading.drawable;

import android.graphics.Canvas;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

/**
 * @ClassName SpinBallIndicator
 * @Description TODO
 * @Author admin
 * @Date 2021/3/29 14:02
 */
public class SpinBallIndicator extends SpinIndicator {

    private float mRadius;
    private float mDeltaRadius;

    public SpinBallIndicator(@ColorInt int color, @SpeedMode int speed) {
        super(color, speed);
    }

    public SpinBallIndicator(@ColorInt int color) {
        super(color);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        for (int i = 0; i < patternCount(); i++) {
            setPaintAlpha(darkPatternCount(), i);
            int save = canvas.save();
            float curRotateDegree = rotateDegree() * (i + getOffsetCount());
            canvas.rotate(curRotateDegree, mCenterX, mCenterY);
            float drawRadius = mRadius - i * mDeltaRadius;
            canvas.drawCircle(mCenterX, mRadius, drawRadius, mPaint);
            canvas.restoreToCount(save);
        }
    }

    @Override
    protected void calculateDrawParams() {
        // 计算绘制最大ball的半径
        mRadius = mFitSize * patternLenRate() / 2f;
        // 计算绘制最小ball的半径
        float minRadius = mRadius * 0.3f;
        // 每个ball依次递减的半径长度
        mDeltaRadius = (mRadius - minRadius) / patternCount();
    }

    @Override
    protected int animationMaxValue() {
        return patternCount();
    }

    @Override
    protected int patternCount() {
        return 8;
    }

    @Override
    protected int darkPatternCount() {
        return 3;
    }

    @Override
    protected float patternLenRate() {
        return 0.22f;
    }
}
