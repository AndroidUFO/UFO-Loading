package com.androidufo.loadingsample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.androidufo.loading.UFOLoadingController;
import com.androidufo.loading.UFOLoadingDialog;
import com.androidufo.loading.view.UFOLoadingView;

public class MainActivity extends AppCompatActivity {

    private UFOLoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showLoadingView();
        showUFODialog();
    }

    private void showLoadingView() {
        FrameLayout container = findViewById(R.id.container);
        mLoadingView = createController(
                "我是View，正在加载...",
                Color.TRANSPARENT,
                Color.DKGRAY,
                Color.DKGRAY,
                UFOLoadingController.IndicatorStyle.SPIN_BALL,
                true
        ).createLoadingView(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.TOP | Gravity.CENTER;
        lp.topMargin = DisplayUtils.dp2px(this, 50);
        container.addView(mLoadingView, lp);
        mLoadingView.startIndicatorAnim();
    }

    private void showUFODialog() {
        UFOLoadingDialog dialog = createController(
                "我是Dialog，正在加载...",
                Color.WHITE,
                Color.RED,
                Color.RED,
                UFOLoadingController.IndicatorStyle.SPIN_LINE,
                false
        ).createDialog(this);
        dialog.setCancelable(false);
        dialog.show();
//        dialog.setDimVal(UFODialog.DimVal.NONE);
    }

    private UFOLoadingController createController(String msg,
                                                  int bgColor,
                                                  int msgColor,
                                                  int indicatorColor,
                                                  UFOLoadingController.IndicatorStyle style,
                                                  boolean horizontal) {
        return new UFOLoadingController.Builder()
                .bgColor(bgColor)
                .bgRadius(DisplayUtils.dp2px(this, 3))
                .padding(DisplayUtils.dp2px(this, 6))
                .horizontalMode(horizontal)
                .intervalMargin(10)
                .msg(msg)
                .msgTextColor(msgColor)
                .msgTextSize(14)
                .indicatorColor(indicatorColor)
                .indicatorStyle(style)
//                .indicatorSize(DisplayUtils.dp2px(this, 20))
                .indicatorNormalRotate()
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoadingView.stopIndicatorAnim();
    }
}