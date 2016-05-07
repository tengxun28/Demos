package com.iboxpay.android.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by wangyaohui on 15-9-23.
 */
public class MyReceiver extends BroadcastReceiver {
    public static final String TAG = "wangyaohui";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e(TAG, "MyReceiver action:" + action);

//        System.Global.

    }
}
