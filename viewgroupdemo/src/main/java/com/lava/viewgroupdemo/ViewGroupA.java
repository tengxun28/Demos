package com.lava.viewgroupdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by wangyaohui on 16-9-7.
 */
public class ViewGroupA extends LinearLayout {

    public ViewGroupA(Context context) {
        super(context, (AttributeSet)null);
    }

    public ViewGroupA(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ViewGroupA(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
//
//    @Override
//    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
//
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result =  super.dispatchTouchEvent(ev);
        Log.e("result:" + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean  result = super.onInterceptTouchEvent(ev);
        Log.e("result:" + result);
        return  result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("event " + event);
        boolean result = super.onTouchEvent(event);
        Log.e("result:" + result);
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }
}
