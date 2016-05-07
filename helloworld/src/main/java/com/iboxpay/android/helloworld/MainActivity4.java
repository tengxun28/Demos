package com.iboxpay.android.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity4 extends AppCompatActivity {
    public static final String TAG = "wangyaohui";
    String[] list = new String[]{"hello"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

}
