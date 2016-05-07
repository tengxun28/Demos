package com.iboxpay.android.helloworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class BubbleTextView extends TextView {
    private static final String TAG = "BubbleTextView2" ;

    private static final int CORNER_RADIUS = 10;
    private static final int PADDING_H = 15;
    private static final int PADDING_V = 5;

    private final RectF mRect = new RectF();

    private Paint mPaint;

    public BubbleTextView(Context context) {
        super(context);
        init();
    }

    public BubbleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BubbleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setFocusable(true);
        // We need extra padding below to prevent the bubble being cut.
        //setPadding(PADDING_H, 0, PADDING_H, PADDING_V);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getContext().getResources().getColor(R.color.bubble_dark_background));
      //  mPaint.setColor(0xffff8847);
    }
    @Override
    protected void drawableStateChanged() {
        invalidate();
        super.drawableStateChanged();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        layout();
        return super.onTouchEvent(event);
    }

    @Override
    public void draw(Canvas canvas) {
        Log.e(TAG, "draw " + canvas);
        drawTextContainer(canvas);
        drawClip(canvas);
        drawClip2(canvas);
        super.draw(canvas);

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        drawTextContainer(canvas);
        Log.e(TAG, "onDraw: " + canvas);
        super.onDraw(canvas);
    }

    /*

    */
    public void drawClip(Canvas canvas) {
        canvas.save();

        Rect rect = new Rect(0, 0, 200, 200);
        canvas.clipRect(rect);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.bubble_dark_background));
        paint.setColor(0xff00ff00);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect, paint);


        canvas.restore();
    }

    public void drawClip2(Canvas canvas) {
       canvas.save();
        Paint paint = new Paint();
        Rect clip = new Rect(100, 100, 300, 300);
        canvas.clipRect(clip, Region.Op.INTERSECT);
        paint.setColor(Color.BLUE);
        canvas.drawRect(clip, paint);
        canvas.restore();
    }


    public void drawTextContainer(Canvas canvas) {
        final Layout layout = getLayout();
        final RectF rect = mRect;
        final int left = getCompoundPaddingLeft();
        final int top = getExtendedPaddingTop();

        float mLeft = left + layout.getLineLeft(0) - PADDING_H;
        float mTop = top + layout.getLineTop(0) - PADDING_V;
        float mRight = Math.min(left + layout.getLineRight(0) + PADDING_H, getScrollX() + getRight() - getLeft());
        float mBottom = top + layout.getLineBottom(0) + PADDING_V;
        rect.set(mLeft, mTop, mRight, mBottom);


        Log.e(TAG, "mLeft:" + mLeft + ",mTop:" + mTop + ",mRight:" + mRight + ",mBottom:" + mBottom);
        canvas.drawRoundRect(rect, CORNER_RADIUS, CORNER_RADIUS, mPaint);
        Log.e(TAG, "getRight:" + getRight() + ",  getLeft:" + getLeft() + ", getBottom:" + getBottom() + ", getTop:" + getTop()
                + ", widht:" + (getRight() - getLeft())
                + ", height:" + (getBottom() - getTop()));

        /*   final int right = getCompoundPaddingRight();
        final int bottom = getExtendedPaddingBottom();
        Log.e("draw",left +  "  " +  top + "    " + "   " + right + "    " + bottom);
        Log.e("draw","[" + getScrollX()  + "," + getScrollY() + "]");
        rect.set(left,top,right,bottom);
        canvas.drawRoundRect(rect,10,10,mPaint);*/

    }
}
