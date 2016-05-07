package com.iboxpay.android.intentservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        //经测试，Service里面是不能进行耗时的操作的，必须要手动开启一个工作线程来处理耗时操作
        System.out.println("onStart");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("睡眠结束");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}