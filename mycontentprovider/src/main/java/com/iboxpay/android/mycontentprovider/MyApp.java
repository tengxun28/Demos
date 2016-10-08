package com.iboxpay.android.mycontentprovider;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by wangyaohui on 16-6-23.
 */
public class MyApp extends Application {

    private static RefWatcher mRefWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        mRefWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
