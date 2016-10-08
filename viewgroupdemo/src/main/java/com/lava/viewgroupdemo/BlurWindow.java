package com.lava.viewgroupdemo;
import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class BlurWindow {
    private Window mWindow;
    private WindowManager mWindowManager;
    private View mEmptyView;
    public static final int START_BLUR = 0;
    public static final int STOP_BLUR = 1;
    private static final int EMPTY_SIZE = 0;
    private Handler mHandler = new Handler() {
        private boolean isAdd = false;

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case START_BLUR:
                    if (!isAdd) {
                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        lp.flags |= (WindowManager.LayoutParams.FLAG_BLUR_BEHIND /*| ViewGroup.LayoutParams.FLAG_NOT_FOCUSABLE*/);
                        lp.format = PixelFormat.TRANSPARENT;
                        lp.width = EMPTY_SIZE;
                        lp.height = EMPTY_SIZE;
                        lp.type = WindowManager.LayoutParams.TYPE_APPLICATION;
                        mWindowManager.addView(mEmptyView, lp);
                        isAdd = true;
                    }

                    break;
                case STOP_BLUR:
                    mWindowManager.removeView(mEmptyView);
                    isAdd = false;
                    break;
                default:
                    break;
            }

        };
    };

    public BlurWindow(Activity activity) {
        mWindow = activity.getWindow();
        mWindowManager = mWindow.getWindowManager();
        mEmptyView = new View(activity);
    }

    public void startBlur() {
        mHandler.sendEmptyMessage(START_BLUR);

    }

    public void stopBLur() {
        mHandler.sendEmptyMessage(STOP_BLUR);

    }

}