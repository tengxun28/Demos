package com.iboxpay.android.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by wangyaohui on 15-12-15.
 */
public class NewsActivity extends Activity {
    private TextView message = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_activity_main);
        message = (TextView)findViewById(R.id.news_message);
        message.setText("    " + getNewsContent());
    }

    public String getNewsContent() {
        return getResources().getString(R.string.news_message);
    }
}
