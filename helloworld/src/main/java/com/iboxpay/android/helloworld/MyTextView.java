package com.iboxpay.android.helloworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class MyTextView extends TextView {
    private static final String TAG = "BubbleTextView2" ;

    private static final int CORNER_RADIUS = 10;
    private static final int PADDING_H = 15;
    private static final int PADDING_V = 5;

    private final RectF mRect = new RectF();

    private Paint mPaint;

    public MyTextView(Context context) {
        super(context);
        //init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //init();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //init();
    }

    RectF src = new RectF(10f, 0f, 500f, 200f);
    Paint p = new Paint();
    String str1, str2, str3;

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        //TextPaint tp = getPaint() ;

        if(str1 != null){
            src.left = this.getTextSize() * str1.length() / 2;
        }
        if(str2 != null){
            src.right = src.left + getTextSize() * str2.length() / 2;
        }
        p.setColor(Color.BLUE);
        canvas.drawRoundRect(src, 6.0f, 6.0f, p);
        Log.d("lxy", "src = " + src);

        super.onDraw(canvas);
    }

    public void setText(String s1, String s2, String s3){
        str1 = s1;
        str2 = s2;
        str3 = s3;
        setText(str1 + str2 + str3);
    }
}
