package com.lava.blur2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends /*AppCompat*/Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showDialog(1);
    }

    public void fun(View v) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                startActivity(intent);
            }
        };
//        BlurBehind.getInstance().execute(MainActivity.this, runnable);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("title")
                .setMessage("messages")
                .create();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
//                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//        lp.dimAmount=0.0f;
//        dialog.getWindow().setAttributes(lp);
//        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        return dialog;
    }
}
