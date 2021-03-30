package com.androidufo.loading.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidufo.loading.drawable.UFOLoadingDrawable;

import androidx.annotation.ColorInt;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @ClassName UFOLoadingView
 * @Description TODO
 * @Author admin
 * @Date 2021/3/29 16:50
 */
public class UFOLoadingView extends RelativeLayout {
    private static final int ID_INDICATOR = -11;

    private UFOIndicatorImageView mIndicatorView;
    private TextView mLoadingMsgView;

    private boolean mHorizontalMode = false;
    private int mIntervalMargin;

    public UFOLoadingView(Context context) {
        this(context, null);
    }

    public UFOLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UFOLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createChildView(context);
    }

    private void createChildView(Context context) {
        // 创建indicatorImageView
        createIndicatorView(context);
        // 创建loadingMsgView
        createLoadingMsgView(context);
    }

    private void createIndicatorView(Context context) {
        mIndicatorView = new UFOIndicatorImageView(context);
        mIndicatorView.setId(ID_INDICATOR);
        setIndicatorViewLayoutParams();
        addView(mIndicatorView);
    }

    private void setIndicatorViewLayoutParams() {
        LayoutParams lp = (LayoutParams) mIndicatorView.getLayoutParams();
        LayoutParams newLp;
        if (lp == null) {
            newLp = getChildWrapLayoutParams();
        } else {
            newLp = new LayoutParams(lp.width, lp.height);
        }
        if (mHorizontalMode) {
            newLp.addRule(RelativeLayout.CENTER_VERTICAL);
        } else {
            newLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        }
        mIndicatorView.setLayoutParams(newLp);
    }

    private void createLoadingMsgView(Context context) {
        mLoadingMsgView = new AppCompatTextView(context);
        mLoadingMsgView.setGravity(Gravity.CENTER);
        setLoadingMsgViewLayoutParams();
        addView(mLoadingMsgView);
    }

    private LayoutParams getChildWrapLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    private void setLoadingMsgViewLayoutParams() {
        LayoutParams lp = (LayoutParams) mLoadingMsgView.getLayoutParams();
        LayoutParams newLp;
        if (lp == null) {
            newLp = getChildWrapLayoutParams();
        } else {
            newLp = new LayoutParams(lp.width, lp.height);
        }
        if (mHorizontalMode) {
            newLp.addRule(RelativeLayout.CENTER_VERTICAL);
            newLp.addRule(RelativeLayout.RIGHT_OF, ID_INDICATOR);
            newLp.leftMargin = mIntervalMargin;
        } else {
            newLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            newLp.addRule(RelativeLayout.BELOW, ID_INDICATOR);
            newLp.topMargin = mIntervalMargin;
        }
        mLoadingMsgView.setLayoutParams(newLp);
    }

    public UFOLoadingView horizontal(boolean horizontalMode) {
        if (mHorizontalMode == horizontalMode) {
            return this;
        }
        mHorizontalMode = horizontalMode;
        setIndicatorViewLayoutParams();
        setLoadingMsgViewLayoutParams();
        return this;
    }

    public UFOLoadingView intervalMargin(int intervalMargin) {
        if (mIntervalMargin == intervalMargin) {
            return this;
        }
        mIntervalMargin = intervalMargin;
        setLoadingMsgViewLayoutParams();
        return this;
    }

    public UFOLoadingView bgDrawable(Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    public UFOLoadingView padding(int[] padding) {
        setPadding(padding[0], padding[1], padding[2], padding[3]);
        return this;
    }

    public UFOLoadingView loadingMsg(String msg) {
        mLoadingMsgView.setText(msg);
        mLoadingMsgView.setVisibility(TextUtils.isEmpty(msg) ? GONE : VISIBLE);
        return this;
    }

    public UFOLoadingView loadingMsgColor(@ColorInt int msgColor) {
        mLoadingMsgView.setTextColor(msgColor);
        return this;
    }

    public UFOLoadingView loadingMsgSize(float msgSize) {
        mLoadingMsgView.setTextSize(msgSize);
        return this;
    }

    public UFOLoadingView indicatorDrawable(UFOLoadingDrawable drawable) {
        mIndicatorView.setImageDrawable(drawable);
        return this;
    }

    public UFOLoadingView indicatorSize(int size) {
        if (size <= 0) {
            return this;
        }
        LayoutParams layoutParams = (LayoutParams) mIndicatorView.getLayoutParams();
        layoutParams.width = size;
        layoutParams.height = size;
        mIndicatorView.setLayoutParams(layoutParams);
        return this;
    }

    public void startIndicatorAnim() {
        mIndicatorView.startAnim();
    }

    public void stopIndicatorAnim() {
        mIndicatorView.stopAnim();
    }
}
