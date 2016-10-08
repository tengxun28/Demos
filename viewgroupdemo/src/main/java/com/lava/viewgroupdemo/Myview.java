package com.lava.viewgroupdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wangyaohui on 16-9-7.
 */
public class Myview extends View {
    public Myview(Context context) {
        super(context);
    }

    public Myview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Myview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Myview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        Log.e("result:" + result);
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        boolean result = super.dispatchTouchEvent(event);
        Log.e("result:" + result);
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    public int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.EXACTLY) {
            result = specSize;
            Log.e("xx 1");
        } else {
            Log.e("xx 2");
            result = 200;

            if(specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result,specSize);
                Log.e("xx 3");
            }
        }
        Log.e("specMode:" + specMode + ", specSize:" + specSize + ", return: " + result);
        return  result;
    }

    public int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 400;

            if(specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result,specSize);
            }
        }

        Log.e("specMode:" + specMode + ", specSize:" + specSize + ", return: " + result);

        return  result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("onDraw");
//        invalidate();
          //or
//        postInvalidateDelayed(1000);
        super.onDraw(canvas);
    }
}
