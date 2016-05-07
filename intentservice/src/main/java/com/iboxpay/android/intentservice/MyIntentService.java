package com.iboxpay.android.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
    final static String TAG = "robin";

    public MyIntentService() {
        super("com.lenovo.robin.test.MyIntentService");
        Log.e(TAG, this + " is constructed");
    }

    @Override
    protected void onHandleIntent(Intent arg0) {
        Log.e(TAG, "begin onHandleIntent() in " + this);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "end onHandleIntent() in " + this);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, this + " is destroy");
    }
}