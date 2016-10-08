/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.iboxpay.android.myasynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/*
*   同一个 task　只能调用一次 execute(),否则允许报错
*   不同的　task　都调用execute，会被加入工作队列依次执行，不会同时执行
*
*   3个参数类型，第一个参数类型传给　doInBackground
*   　　　　　　　第二个参数类型: onProgressUpdate  和　 publishProgress　需要一致
*   　　　　　　 第三个参数类型:doInBackground 和　onPostExecute的参数，前者的返回值作为后者的参数传入
*
*   除了　doInBackground　在后台线程执行，其它的都是在　ui线程执行的. execute　应该在主线程里调用.
*
*   使用AsyncTask注意事项:
    1 必须在UI线程中创建AsyncTask的实例
    2 必须在UI线程中调用AysncTask的execute()
    3 AsysncTask各种回调方法不能手动调用，只能由系统调用
    4 每个AsyncTask只能执行一次。
* */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "xx";
    private MyAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task = new MyAsyncTask(this, "");

        byte [] b=new byte[2];
        b[0]=13;
        b[1]=(byte)0xae;
        String s="";
        for(int i=0;i<b.length;i++){
            s+=b[i];
        }
        System.out.println(s);
        Log.e(TAG,s);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        task = new MyAsyncTask(this, "");
//        task.execute();
    }

    public void fun(View v) {
        Log.e(TAG, "onClick　isCancelled:" + task.isCancelled());
//        task = new MyAsyncTask(this, "");
        task.execute();
    }

    public void fun2(View v) {
        Log.e(TAG, "onClick2  cancel return:" + task.cancel(true));
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Integer> {
        private Context mContext;

        public MyAsyncTask(Context mContext, String mName) {
            this.mContext = mContext;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            Log.e(TAG, "doInBackground");
            int i = 0;
            try {
                while (i < 10) {
                    Thread.sleep(1000);
                    i++;
                    publishProgress(i);
                }
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "onPreExecute");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.e(TAG, "onPostExecute ");
            Toast.makeText(MainActivity.this, "onPostExecute", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            for (Integer tt : values) {
                Log.e(TAG, "onProgressUpdate " + tt);
            }
        }

        @Override
        protected void onCancelled(Integer integer) {
            super.onCancelled(integer);
            Log.e(TAG, "onCancelled");
        }

    }
}
