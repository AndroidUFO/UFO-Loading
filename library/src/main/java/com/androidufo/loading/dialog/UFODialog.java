package com.androidufo.loading.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ClassName UFODialog
 * @Description TODO
 * @Author admin
 * @Date 2021/3/29 16:53
 */
public abstract class UFODialog extends Dialog {

    private static final int NOT_SET_ANIMATION_STYLE = -1;

    public UFODialog(@NonNull Context context) {
        super(context);
    }

    public UFODialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected UFODialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createView(getContext()));
        setToUFODialog();
    }

    private void setToUFODialog() {
        Window window = getWindow();
        if (window != null) {
            // 清除Dialog默认的背景
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            WindowManager.LayoutParams attrs = window.getAttributes();
            // 设置默认的dim值
            attrs.dimAmount = DimVal.LIGHT.getVal();
            // 设置Gravity
            attrs.gravity = showGravity();
            // 设置展示动画
            if (showAnimationStyle() != NOT_SET_ANIMATION_STYLE) {
                attrs.windowAnimations = showAnimationStyle();
            }
            window.setAttributes(attrs);
        }
    }

    protected int showGravity() {
        return Gravity.CENTER;
    }

    protected int showAnimationStyle() {
        return NOT_SET_ANIMATION_STYLE;
    }

    public void setDimVal(DimVal dimVal) {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attrs = window.getAttributes();
            attrs.dimAmount = dimVal.getVal();
            window.setAttributes(attrs);
        }
    }

    protected abstract View createView(Context context);

    public enum DimVal {
        NONE(0f),
        LIGHT(0.3f),
        DARK(0.5f);
        DimVal(float val) {
            this.val = val;
        }
        private float val;

        public float getVal() {
            return val;
        }
    }
}
