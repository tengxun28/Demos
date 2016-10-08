package com.lava.viewgroupdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by wangyaohui on 16-9-7.
 */
public class ViewGroupB extends LinearLayout {
    public ViewGroupB(Context context) {
        super(context, (AttributeSet) null);
    }
//
//    public ViewGroupB(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public ViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
//        this(context, attrs, defStyleAttr, 0);
//    }
//
//    public ViewGroupB(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
//
//    @Override
//    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
//
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        Log.e("result:" + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        Log.e("result:" + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        Log.e("result:" + result);
        return  result;
    }
}