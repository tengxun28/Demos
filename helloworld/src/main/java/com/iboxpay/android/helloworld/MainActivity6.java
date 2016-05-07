package com.iboxpay.android.helloworld;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
//import android.widget.CompoundButton;


public class MainActivity6 extends AppCompatActivity {
    public static final String TAG = "wangyaohui";
    public static final int DIALOG_SETTING = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.dialoglayout);
        //showDialog(1);
        Toast.makeText(this, "xxx", Toast.LENGTH_LONG).show();
       /* try {
            Thread.sleep(1000);
        }catch (Exception e) {

        }*/
       // System.exit(0);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case DIALOG_SETTING:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("xx");
                /*alertDialog.setItems(R.array.xxxx, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "onClick:" + which);
                    }
                });*/
                LayoutInflater inflater = LayoutInflater.from(MainActivity6.this);
                View v = inflater.inflate(R.layout.dialoglayout, null);

                RadioGroup group = (RadioGroup) v.findViewById(R.id.rg);

                for(int i=0; i<10; i++)
                {
                    RadioButton tempButton = new RadioButton(this);
                    //tempButton.setBackgroundResource(R.drawable.xxx);	// 设置RadioButton的背景图片
                    //tempButton.setButtonDrawable(R.drawable.xxx);			// 设置按钮的样式
                    tempButton.setPadding(80, 0, 0, 0);           		// 设置文字距离按钮四周的距离
                    tempButton.setText("按钮 " + i);
                    group.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }

                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // TODO Auto-generated method stub
                        //RadioButton tempButton = (RadioButton) findViewById(checkedId); // 通过RadioGroup的findViewById方法，找到ID为checkedID的RadioButton
                        // 以下就可以对这个RadioButton进行处理了
                        Log.e(TAG, "checkId:" + checkedId);
                    }
                });

                alertDialog.setView(v);

                return alertDialog.create();
            default:
                break;
        }

        return super.onCreateDialog(id);
    }
}
