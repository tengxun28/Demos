/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 *
 * author:   wangyaohui
 * email:    wangyaohui@iboxpay.com
 * date:     2016.6
 */

package com.iboxpay.android.myasynctask;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity {
    public static final String TAG = "xx";
    private Button btn;
    private TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fun(View v) {
        Log.e(TAG, "onClick");
        update();
    }
    public void fun2(View v) {
        Log.e(TAG, "onClick");
//        if(updateTextTask != null) {
            updateTextTask.cancel(true);
//            updateTextTask = null;
//        }
    }

    UpdateTextTask updateTextTask = null;
    private void update() {
//        if(updateTextTask == null) {
            updateTextTask = new UpdateTextTask(this);
            updateTextTask.execute();
//        }
    }

    class UpdateTextTask extends AsyncTask<Void, Float, Long> {
        private Context context;

        UpdateTextTask(Context context) {
            this.context = context;
        }

        /**
         * 运行在UI线程中，在调用doInBackground()之前执行
         */
        @Override
        protected void onPreExecute() {
           Log.e(TAG, "开始执行");
        }

        /**
         * 后台运行的方法，可以运行非UI线程，可以执行耗时的方法
         */
        @Override
        protected Long doInBackground(Void... params) {
            int i = 0;
            while (i < 4) {
                i++;
                publishProgress((float)i);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
            }
            return null;
        }

        /**
         * 运行在ui线程中，在doInBackground()执行完毕后执行
         */
        @Override
        protected void onPostExecute(Long integer) {
            Log.e(TAG, "执行完毕");
        }

        /**
         * 在publishProgress()被调用以后执行，publishProgress()用于更新进度
         */
        @Override
        protected void onProgressUpdate(Float... values) {
            Log.e(TAG,"" +values[0]);
        }
    }
}
