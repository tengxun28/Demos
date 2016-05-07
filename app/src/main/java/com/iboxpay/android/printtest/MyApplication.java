package com.iboxpay.android.printtest;

import android.app.Application;
import android.util.Log;

import com.taobao.android.dexposed.DexposedBridge;

/**
 * Created by wangyaohui on 16-5-7.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (DexposedBridge.canDexposed(this)) {
            // Use Dexposed to kick off AOP stuffs.
            Log.e("xxxx","MyApplication onCreate");
        }

    }
}
