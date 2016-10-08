package com.lava.viewgroupdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int choose = 1;
        switch (choose) {
            case 1:
                setContentView(R.layout.activity_main);
                break;
            case 2:
            {
                final DrawBitmapView drawBitmapView = new DrawBitmapView(this);
                setContentView(drawBitmapView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                drawBitmapView.startTranslate();
                drawBitmapView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
//                Random random = new Random();
//                int startLeft = random.nextInt(200);
//                int startTop = random.nextInt(250);
//                int toLeft = random.nextInt(550) + 200;
//                int toBottom = random.nextInt(1000) + 250;
//                drawBitmapView.startTranslate(startLeft, startTop, toLeft, toBottom, 1000);
                        return true;
                    }
                });
            }
            break;

        }

    }
}
