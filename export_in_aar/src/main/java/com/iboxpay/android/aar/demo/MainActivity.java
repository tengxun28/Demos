package com.iboxpay.android.aar.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("xxxx", "onCreate");
//        Intent i = new Intent(MainActivity.this,com.iboxpay.android.library.aar.MainActivity.class);
//        startActivity(i);

//        Drawable r = getResources().getDrawable(com.iboxpay.android.library.aar.MainActivity.R.)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("xxxx","onDestroy");
    }
}
