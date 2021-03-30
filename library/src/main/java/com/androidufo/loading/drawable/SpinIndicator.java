package com.androidufo.loading.drawable;

import android.animation.ValueAnimator;

import androidx.annotation.ColorInt;

/**
 * @ClassName SpinIndicator
 * @Description TODO
 * @Author admin
 * @Date 2021/3/29 9:26
 */
public abstract class SpinIndicator extends UFOLoadingDrawable implements ValueAnimator.AnimatorUpdateListener {

    private int mOffsetCount;

    public SpinIndicator(@ColorInt int color, @SpeedMode int speed) {
        super(color, speed);
    }

    public SpinIndicator(@ColorInt int color) {
        super(color);
    }

    protected void setPaintAlpha(int darkPatternCount, int index) {
        mPaint.setAlpha(index < darkPatternCount ? DARK_ALPHA : LIGHT_ALPHA);
    }

    public void setOffsetCount(int offsetCount) {
        if (mOffsetCount == offsetCount) {
            return;
        }
        mOffsetCount = offsetCount;
        invalidateSelf();
    }

    public int getOffsetCount() {
        return mOffsetCount;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int offset = (int) animation.getAnimatedValue();
        setOffsetCount(offset);
    }

    // 每次旋转的角度
    protected float rotateDegree() {
        return TOTAL_ROTATE_DEGREE * 1.0f / patternCount();
    }

    protected abstract int patternCount();
    protected abstract int darkPatternCount();
    protected abstract float patternLenRate();
}
