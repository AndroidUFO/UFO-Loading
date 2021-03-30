package com.androidufo.loading.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.androidufo.loading.drawable.UFOLoadingDrawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @ClassName UFOLoadingImageView
 * @Description TODO
 * @Author admin
 * @Date 2021/3/29 14:12
 */
public class UFOIndicatorImageView extends AppCompatImageView {

    public UFOIndicatorImageView(@NonNull Context context) {
        super(context);
    }

    public UFOIndicatorImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UFOIndicatorImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
    }

    public void startAnim() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (drawable instanceof UFOLoadingDrawable) {
            ((UFOLoadingDrawable) drawable).start();
        }
    }

    public void stopAnim() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (drawable instanceof UFOLoadingDrawable) {
            ((UFOLoadingDrawable) drawable).stop();
        }
    }
}
