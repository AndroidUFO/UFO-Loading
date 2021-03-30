package com.androidufo.loading;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import com.androidufo.loading.drawable.CircleIndicator;
import com.androidufo.loading.drawable.SpinBallIndicator;
import com.androidufo.loading.drawable.SpinLineIndicator;
import com.androidufo.loading.drawable.UFOLoadingDrawable;
import com.androidufo.loading.view.UFOLoadingView;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

/**
 * @ClassName UFOLoadingController
 * @Description TODO
 * @Author admin
 * @Date 2021/3/29 17:12
 */
public class UFOLoadingController {
    private static final int MODE_NONE = 0;
    private static final int MODE_DIALOG = 1;
    private static final int MODE_VIEW = 2;

    @IntDef({MODE_NONE, MODE_DIALOG, MODE_VIEW})
    private @interface CreateMode {
        int NONE = MODE_NONE;
        int DIALOG = MODE_DIALOG;
        int VIEW = MODE_VIEW;
    }

    private final Builder mBuilder;
    private UFOLoadingView mLoadingView;
    private @CreateMode int mCreateMode = CreateMode.NONE;

    UFOLoadingController(Builder builder) {
        this.mBuilder = builder;
    }

    public UFOLoadingDialog createDialog(Context context) {
        check();
        mCreateMode = CreateMode.DIALOG;
        return new UFOLoadingDialog(context, this);
    }

    public UFOLoadingView createLoadingView(Context context) {
        check();
        mCreateMode = CreateMode.VIEW;
        createView(context);
        return mLoadingView;
    }

    View createView(Context context) {
        mLoadingView = new UFOLoadingView(context);
        if (mBuilder != null) {
            mLoadingView.horizontal(mBuilder.horizontalMode)
                    .intervalMargin(mBuilder.intervalMargin)
                    .bgDrawable(getBgDrawable(mBuilder.bgColor, mBuilder.bgRadius))
                    .padding(mBuilder.padding)
                    .loadingMsg(mBuilder.msg)
                    .loadingMsgColor(mBuilder.msgTextColor)
                    .loadingMsgSize(mBuilder.msgTextSize)
                    .indicatorDrawable(getIndicatorDrawable(
                            mBuilder.indicatorStyle,
                            mBuilder.indicatorColor,
                            mBuilder.speedMode
                    ))
                    .indicatorSize(mBuilder.indicatorSize);
        }
        return mLoadingView;
    }

    private void check() {
        if (mCreateMode != CreateMode.NONE) {
            throw new RuntimeException("UFOLoadingController can only used once");
        }
    }

    private UFOLoadingDrawable getIndicatorDrawable(@NonNull IndicatorStyle indicatorStyle,
                                                    @ColorInt int color,
                                                    @UFOLoadingDrawable.SpeedMode int speedMode) {
        switch (indicatorStyle) {
            case CIRCLE:
                return new CircleIndicator(color, speedMode);
            case SPIN_BALL:
                return new SpinBallIndicator(color, speedMode);
            default:
                return new SpinLineIndicator(color, speedMode);
        }
    }

    private Drawable getBgDrawable(int bgColor, float bgRadius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(bgColor);
        drawable.setCornerRadius(bgRadius);
        return drawable;
    }

    public void startIndicatorAnim() {
        if (mLoadingView == null) return;
        mLoadingView.startIndicatorAnim();
    }

    public void stopIndicatorAnim() {
        if (mLoadingView == null) return;
        mLoadingView.stopIndicatorAnim();
    }

    public void updateLoadingMsg(String msg) {
        if (mLoadingView == null) return;
        mLoadingView.loadingMsg(msg);
    }

    public enum IndicatorStyle {
        SPIN_LINE,
        SPIN_BALL,
        CIRCLE;
    }

    public static class Builder {
        // container
        private boolean horizontalMode;
        private @ColorInt int bgColor = Color.TRANSPARENT;
        private final int[] padding = new int[4];
        private float bgRadius;
        private int intervalMargin;
        // msg
        private String msg;
        private float msgTextSize = 15f;
        private @ColorInt int msgTextColor = Color.BLACK;
        // indicator
        private IndicatorStyle indicatorStyle = IndicatorStyle.SPIN_LINE;
        private int indicatorSize;
        private @ColorInt int indicatorColor;
        private @UFOLoadingDrawable.SpeedMode int speedMode = UFOLoadingDrawable.SpeedMode.NORMAL;

        public Builder horizontalMode(boolean horizontal) {
            this.horizontalMode = horizontal;
            return this;
        }

        public Builder intervalMargin(int intervalMargin) {
            this.intervalMargin = intervalMargin;
            return this;
        }

        public Builder padding(int left, int top, int right, int bottom) {
            padding[0] = left;
            padding[1] = top;
            padding[2] = right;
            padding[3] = bottom;
            return this;
        }

        public Builder padding(int samePadding) {
            padding[0] = samePadding;
            padding[1] = samePadding;
            padding[2] = samePadding;
            padding[3] = samePadding;
            return this;
        }

        public Builder bgColor(int bgColor) {
            this.bgColor = bgColor;
            return this;
        }

        public Builder bgRadius(float bgRadius) {
            this.bgRadius = bgRadius;
            return this;
        }

        public Builder indicatorSize(int indicatorSize) {
            this.indicatorSize = indicatorSize;
            return this;
        }

        public Builder indicatorStyle(IndicatorStyle indicatorStyle) {
            this.indicatorStyle = indicatorStyle;
            return this;
        }

        public Builder indicatorColor(@ColorInt int color) {
            this.indicatorColor = color;
            return this;
        }

        public Builder indicatorNormalRotate() {
            this.speedMode = UFOLoadingDrawable.SpeedMode.NORMAL;
            return this;
        }

        public Builder indicatorSlowRotate() {
            this.speedMode = UFOLoadingDrawable.SpeedMode.SLOW;
            return this;
        }

        public Builder indicatorFastRotate() {
            this.speedMode = UFOLoadingDrawable.SpeedMode.FAST;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder msgTextSize(float sp) {
            this.msgTextSize = sp;
            return this;
        }

        public Builder msgTextColor(@ColorInt int color) {
            this.msgTextColor = color;
            return this;
        }

        public UFOLoadingController build() {
           return new UFOLoadingController(this);
        }
    }
}
