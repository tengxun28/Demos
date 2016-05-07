package com.iboxpay.android.helloworld;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.SeekBar;

import com.baidu.location.LocationClient;

import java.lang.reflect.Field;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {
    public static final String TAG = "wangyaohui";
    public LocationClient mLocationClient = null;
    private SeekBar sound;
    private AudioManager audioManager;


    String[] list = new String[]{"hello"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bubble_main);
        fun();
//        Log.e(TAG, "运营商信息：" + getInfo());
//        Log.e(TAG,"屏幕尺寸：" + getScreenPhysicalSize(this));
//        startApkActivity(this, "com.android.iboxpay.helloworld");
    }

    // 获取某个类的 R.java 里的资源文件
    public void fun() {
        Class<?> c = null;

            try {
                String packageName =  MainActivity1.class.getPackage().getName();
                Log.e("wangyaohui", "#packageName: " + packageName);
                //String temp = packageName + ".R$layout";
                String temp = "com.android.settings" + ".R$layout";
                Log.e("wangyaohui", "temp:" + temp);
                c = Class.forName(temp);
            } catch(Exception e) {
                e.printStackTrace();
                Log.e("wangyaohui", "e:" + e);
            }

            if(c != null) {
                Field[] fields = c.getDeclaredFields();
                for (Field f : fields) {
                    f.setAccessible(true);
                    Log.e("wangyaohui", "#FieldName: " + f.getName());
                }
            }
    }

    public String getInfo() {
        String result = null;
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        result = tm.getSimOperatorName();
        return result;
    }
    // 获得屏幕尺寸,单位是寸
    public static double getScreenPhysicalSize(Activity ctx) {
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
        double diagonalPixels = Math.sqrt(Math.pow(dm.widthPixels, 2) + Math.pow(dm.heightPixels, 2));
        return diagonalPixels / (160 * dm.density);
    }

    //启动主ａｃｔｉｖｉｔｙ
    public static void startApkActivity(final Context ctx, String packageName) {
        PackageManager pm = ctx.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(packageName, 0);
            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setPackage(pi.packageName);

            List<ResolveInfo> apps = pm.queryIntentActivities(intent, 0);

            ResolveInfo ri = apps.iterator().next();
            if (ri != null) {
                String className = ri.activityInfo.name;
                intent.setComponent(new ComponentName(packageName, className));
                ctx.startActivity(intent);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("startActivity", e.toString());
        }
    }

}
