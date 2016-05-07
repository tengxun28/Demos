package com.iboxpay.android.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.Log;
import android.widget.TextView;


public class MainActivity3 extends AppCompatActivity {
    public static final String TAG = "wangyaohui";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView)findViewById(R.id.tv);

        TextPaint mTextPaint= tv.getPaint();

        int mTextViewWidth=(int)mTextPaint.measureText("要显示的字符");
        Log.e(TAG, "textWidth:" + mTextViewWidth);

        int mTextViewWidth1=(int)mTextPaint.measureText("a");
        Log.e(TAG, "textWidth1:" + mTextViewWidth1);

        int mTextViewWidth2=(int)mTextPaint.measureText("a要");
        Log.e(TAG, "textWidth2:" + mTextViewWidth2);

        int mTextViewWidth3=(int)mTextPaint.measureText("2");
        Log.e(TAG, "textWidth3:" + mTextViewWidth3);

        int mTextViewWidth4=(int)mTextPaint.measureText("1");
        Log.e(TAG, "textWidth4:" + mTextViewWidth4);

        int mTextViewWidth5=(int)mTextPaint.measureText("...");
        Log.e(TAG, "textWidth5:" + mTextViewWidth5);

    }





}
