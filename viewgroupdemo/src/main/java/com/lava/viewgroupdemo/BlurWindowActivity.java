package com.lava.viewgroupdemo;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;


//sleep 1.5s 然后模糊 再sleep2.0s 再去除模糊
public class BlurWindowActivity extends Activity {
    /** Called when the activity is first created. */
    BlurWindow window;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        window = new BlurWindow(this);
        //startActivity(new Intent("com.test"));
        new Thread() {

            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                window.startBlur();
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                window.stopBLur();

            };
        }.start();

    }

}

