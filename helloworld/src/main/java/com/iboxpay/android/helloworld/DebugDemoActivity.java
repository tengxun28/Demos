package com.iboxpay.android.helloworld;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wangyaohui on 16-9-1.
 */
public class DebugDemoActivity extends AppCompatActivity {
    public static final String TAG = "xxxx";

    int m;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m = 1;
    }

    @Override
    protected void onResume() {
        super.onResume();

        fun1();
        int tt = 3;
        fun2();


    }

    public int fun1() {
//        Log.e(TAG,"fun1");
        m++;
        return m;
    }

    public void fun2() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            m++;
//            Log.e(TAG,"m " + m);
        }
    }

    public int fun3(int i) {
        return i* 2;
    }
}
