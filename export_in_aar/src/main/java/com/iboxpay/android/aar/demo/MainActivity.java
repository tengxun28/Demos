package com.iboxpay.android.aar.demo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_main_sdk);
        Log.e("xxxx", "onCreate");
//        Drawable drawable = (Drawable)getResources().getDrawable(R.drawable.unibank);
        Drawable drawable = (Drawable)getResources().getDrawable(R.drawable.bank_logo_default);


        Intent i = new Intent(MainActivity.this,com.iboxpay.android.library.aar.MainActivity2.class);
//        Intent i = new Intent(MainActivity.this,com.iboxpay.android.aar.exploreout.MainActivity2.class);
        startActivity(i);

//        Drawable r = getResources().getDrawable(com.iboxpay.android.library.aar.MainActivity2.R.)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("xxxx","onDestroy");
    }
}
