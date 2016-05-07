package com.iboxpay.android.viewpagerdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by wangyaohui on 15-9-12.
 */
public class CcReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e("dddd","ooeee " + action);

        if(action.equals(Intent.ACTION_SHUTDOWN)) {
            Log.e("dddd", "shutdown intent");
            PackageManager pm = context.getPackageManager();
            //ComponentName name = new ComponentName("com.android.iboxpay.viewpagerdemo", "com.android.iboxpay.viewpagerdemo.MainActivity");
            //pm.setComponentEnabledSetting(name, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
            //        PackageManager.DONT_KILL_APP);
        }
    }
}
