package com.lava.tracedemo;

import android.os.Debug;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "xxxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long t1 = System.currentTimeMillis();
        if (true) {
            setContentView(R.layout.activity_main);
        } else {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            setContentView(tv);
        }
        Log.d("xxxx", "end - begin(ms): " + (System.currentTimeMillis() - t1));

//        writeFileToSD();
    }

    public void onClick(View v) {
        long t1 = System.currentTimeMillis();
        Log.d("xxxx", "onclick begin");
//        fun(40);
        fun2(40);
        Log.d("xxxx", "onclick end - begin(ms): " + (System.currentTimeMillis() - t1));
    }

    private void a() {
        int a = 3000;
        for (int i = 0; i < a; i++) {
            Log.e("xx", "adfafaf");
        }
    }

    private void b() {
        Debug.startMethodTracing("");
        Debug.stopMethodTracing();

    }

    private void c() {
    }

    private void d() {
    }

    private int fun(int i) {
        if (i == 1 || i == 2) return i;
        return fun(i - 1) + fun(i - 2);
    }

    private long fun2(int n) {
        {
            if(n > 1) {
                long a = 0, b = 1;
                do {
                    long tmp = b;
                    b += a;
                    a = tmp;
                } while (--n > 1);
                return b;
            }

            return n;
        }
    }

    private void writeFileToSD() {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            Log.d("TestFile", "SD card is not avaiable/writeable right now.");
            return;
        }
        try {
            String pathName = "/sdcard/test/";
            String fileName = "file.txt";
            File path = new File(pathName);
//            File file = new File(pathName + fileName);
            File file = getFilePath(pathName, fileName);
            if (!path.exists()) {
                Log.d("TestFile", "Create the path:" + pathName);
                path.mkdir();
            }
            if (!file.exists()) {
                Log.d("TestFile", "Create the file:" + fileName);
                file.createNewFile();
            }
            FileOutputStream stream = new FileOutputStream(file);
            String s = "this is a test string writing to file.";
            byte[] buf = s.getBytes();
            stream.write(buf);
            stream.close();

        } catch (Exception e) {
            Log.e("TestFile", "Error on writeFilToSD.");
            e.printStackTrace();
        }
    }

    public static File getFilePath(String filePath,
                                   String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return file;
    }

    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {

        }
    }


}
