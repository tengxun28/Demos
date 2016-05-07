package com.iboxpay.android.helloworld;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity7 extends Activity {
    public static final String TAG = "wangyaohui";
        private static final String[] strs = new String[] {
        "first", "second", "third", "fourth", "fifth","six","李明","ｘ神","阿蔡","李总","first", "second", "third", "fourth", "fifth","six","李明","ｘ神","阿蔡","李总"
        };//定义一个String数组用来显示ListView的内容private ListView lv;/** Called when the activity is first created. */

    private ArrayList<HashMap<String,Object>> items = new ArrayList<HashMap<String,Object>>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.main7);

            ListView lv = (ListView) findViewById(android.R.id.list);//得到ListView对象的引用 /*为ListView设置Adapter来绑定数据*/

            addData();

            lv.setAdapter(new MyAdapter(this,items, R.layout.list_item,
                            new String[]{KEY_TITLE,KEY_SUMMARY1,KEY_SUMMARY2},
                            new int[]{R.id.title,R.id.summary1,R.id.summary2}
                        ));
    }

    public static final String KEY_TITLE = "title";
    public static final String KEY_SUMMARY1 = "summary1";
    public static final String KEY_SUMMARY2 = "summary2";
    public static final String KEY_STATE = "state";

    public void addData() {
        for(int i = 0; i < strs.length; i++) {
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put(KEY_TITLE, strs[i]);
            map.put(KEY_SUMMARY1, "版本号：" + i + "-->" + " i");
            map.put(KEY_SUMMARY2, "这是版本这是版本这是版本这是版本这是版本这是版本这是版本这是版本这是版本这是版本这是版本这是版本这是版本这是版本这是版本发布说明：" + i);
            map.put(KEY_STATE, new Boolean(false));
            items.add(map);
        }
    }



    class MyAdapter extends SimpleAdapter {

        private LayoutInflater mInflater;
        List<? extends Map<String,Object >> mMap = null;

        public MyAdapter(Context context, List<? extends Map<String, Object>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            mMap = data;
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mMap.size();
        }

      /*  @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }*/

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e(TAG, "getView " + position);
            ViewHolder vh = null;
            if(convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item,parent,false);
                vh = new ViewHolder();
                vh.icon = (ImageView)convertView.findViewById(R.id.icon);
                vh.title = (TextView)convertView.findViewById(R.id.title);
                vh.summary1 = (TextView)convertView.findViewById(R.id.summary1);
                vh.button = (Button)convertView.findViewById(R.id.button);
                vh.summary2 = (TextView)convertView.findViewById(R.id.summary2);
                vh.more = (ImageView)convertView.findViewById(R.id.more);
                vh.state = SHRINK_UP_STATE;
                convertView.setTag(vh);

            } else {
                vh = (ViewHolder)convertView.getTag();
            }

            vh.icon.setBackgroundResource(R.drawable.dd);
            Map<String,Object> map = mMap.get(position);
            vh.title.setText((String)map.get(KEY_TITLE));
            vh.summary1.setText((String) map.get(KEY_SUMMARY1));
            vh.button.setText(getResources().getString(R.string.update_app));

            Boolean buttonState = (Boolean)map.get(KEY_STATE);
            boolean state = buttonState.booleanValue();
            if(!state) {
                Log.e(TAG, "收缩");
                vh.summary2.setMaxLines(3);
                vh.more.setBackgroundDrawable(getResources().getDrawable(R.drawable.dd));
            } else {
                Log.e(TAG, "展开");
                vh.summary2.setMaxLines(1000);
                vh.more.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
            }

            vh.summary2.setText((String) map.get(KEY_SUMMARY2));

            final Map<String,Object> mapTemp = map;
            vh.more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "onClick");
                    Boolean buttonState = (Boolean)mapTemp.get(KEY_STATE);
                    boolean state = buttonState.booleanValue();
                    mapTemp.put(KEY_STATE,new Boolean(!state));
                    notifyDataSetChanged();

                }
            });

            return convertView;
        }

    }


    static  class ViewHolder {
        ImageView icon;
        TextView title;
        TextView summary1;
        Button button;

        TextView summary2;
        ImageView more;

        int state;
    }

    private static final int VIDEO_CONTENT_DESC_MAX_LINE = 3;// 默认展示最大行数3行
    private static final int SHOW_CONTENT_NONE_STATE = 0;// 扩充
    private static final int SHRINK_UP_STATE = 1;// 收起状态
    private static final int SPREAD_STATE = 2;// 展开状态
    private static int mState = SHRINK_UP_STATE;//默认收起状态
}