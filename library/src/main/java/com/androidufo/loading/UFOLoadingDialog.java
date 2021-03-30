package com.androidufo.loading;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.androidufo.loading.dialog.UFODialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ClassName UFOLoadingDialog
 * @Description TODO
 * @Author admin
 * @Date 2021/3/29 17:10
 */
public class UFOLoadingDialog extends UFODialog {

    private UFOLoadingController mLoadingController;

    UFOLoadingDialog(@NonNull Context context,
                     @NonNull UFOLoadingController controller) {
        super(context);
        this.mLoadingController = controller;
    }

    UFOLoadingDialog(@NonNull Context context,
                     int themeResId,
                     @NonNull UFOLoadingController controller) {
        super(context, themeResId);
        this.mLoadingController = controller;
    }

    UFOLoadingDialog(@NonNull Context context,
                     boolean cancelable,
                     @Nullable DialogInterface.OnCancelListener cancelListener,
                     @NonNull UFOLoadingController controller) {
        super(context, cancelable, cancelListener);
        this.mLoadingController = controller;
    }

    @Override
    protected View createView(Context context) {
        return mLoadingController.createView(context);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mLoadingController.startIndicatorAnim();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mLoadingController.stopIndicatorAnim();
    }

    public void updateLoadingMsg(String msg) {
        mLoadingController.updateLoadingMsg(msg);
    }

}
