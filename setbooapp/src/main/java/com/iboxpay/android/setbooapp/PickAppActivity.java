package com.iboxpay.android.setbooapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PickAppActivity extends Activity {

    public static final String TAG = "PickApp";

    public static final String KEY_ICON = "icon";
    public static final String KEY_APPNAME = "appname";
    public static final String KEY_CLASSNAME = "classname";
    public static final String KEY_PKGNAME = "pkgname";
    private static final int WAIT_DLG = 1;

    ListView mList;
    AppsListAdapter mAdapter;
    private ProgressDialog mWaitDialog;
    private Context mContext;
    private PackageManager mPackageManager;

    ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boot_app_main);
        mContext = this;
        mList = (ListView)findViewById(android.R.id.list);
        //mList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        mList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.e(TAG, "clicked position: " + position);
            }
        });

        loadAppItem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class AppsListAdapter extends SimpleAdapter {
        private final LayoutInflater mInflater;
        private int mLastSelected = -1;
        Activity activity;

        private int[] appTo;
        private String[] appFrom;

        private List<? extends Map<String, ?>>  appData;
        private int appResource;
        private LayoutInflater appInflater;
        private int mSelected = -1;
        private String mPickResult = null;

        public AppsListAdapter(Context context, List<? extends Map<String, ?>> data,
                               int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
            appData = data;
            appResource = resource;
            appFrom = from;
            appTo = to;
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            activity = (Activity)context;
        }

        public int getCount() {
            return appData.size();
        }

        public Object getItem(int position) {
            return appData.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item, parent, false);
                holder = new ViewHolder();
                holder.mTitle = (TextView)convertView.findViewById(android.R.id.title);
                holder.mSummary = (TextView)convertView.findViewById(android.R.id.summary);
                holder.mIcon=(ImageView)convertView.findViewById(android.R.id.icon);
                holder.mRadioButton=(RadioButton)convertView.findViewById(R.id.bt);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Object o =  appData.get(position).get(KEY_ICON);
            if(o instanceof Drawable) {
                holder.mIcon.setImageDrawable((Drawable) o);
            }

            holder.mTitle.setText(appData.get(position).get(KEY_APPNAME).toString());

            holder.mRadioButton.setId(position);
            holder.mRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.e(TAG, "onCheckedChanged,mLastSelected:" + mLastSelected);
                    if (isChecked) {
                        if (mLastSelected != -1) {
                            RadioButton tempButton = (RadioButton) activity.findViewById(mLastSelected);
                            if (tempButton != null) {
                                tempButton.setChecked(false);
                            }
                        }
                        mLastSelected = buttonView.getId();
                    }
                }
            });


            if (position == mLastSelected) {
                holder.mRadioButton.setChecked(true);
            } else {
                holder.mRadioButton.setChecked(false);
            }

            return convertView;
        }
    }

    static class ViewHolder {
        ImageView mIcon;
        TextView mTitle;
        TextView mSummary;
        RadioButton mRadioButton;
    }

    protected Dialog onCreateDialog(int id) {
        if(mWaitDialog == null){
            mWaitDialog =  new ProgressDialog(this);
            mWaitDialog.setMessage(getResources().getString(R.string.loading));
            mWaitDialog.setCancelable(false);

        }
        return mWaitDialog;
    }

    private void loadAppItem(){

        showDialog(WAIT_DLG);

        new Thread() {
            public void run() {

                Log.e(TAG,"run begin");
                List<ResolveInfo> allApps;
                mPackageManager = getPackageManager();
                Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                allApps = mPackageManager.queryIntentActivities(mainIntent, 0);

                Log.e(TAG,"AppSize:" + allApps.size());
                for (ResolveInfo res : allApps) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    Drawable icon = res.loadIcon(mPackageManager);
                    String title = res.loadLabel(mPackageManager).toString();
                    String pkg = res.activityInfo.packageName;
                    String cls = res.activityInfo.name;
                    Log.e(TAG,"" + title + ", " + pkg + ", "+ cls);
                    map.put(KEY_ICON, icon);
                    map.put(KEY_APPNAME, title);
                    map.put(KEY_CLASSNAME,cls );
                    map.put(KEY_PKGNAME,pkg);
                    items.add(map);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new AppsListAdapter(mContext,items, R.layout.list_item,
                                new String[]{KEY_ICON,KEY_APPNAME,KEY_CLASSNAME},
                                new int[]{android.R.id.icon, android.R.id.title,android.R.id.summary});

                        Message msg = Message.obtain();
                        msg.obj = mAdapter;
                        mHandler.sendMessage(msg);

                    }
                });

                Log.e(TAG,"run end");
            }
        }.start();

    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            final AppsListAdapter adapter = (AppsListAdapter)msg.obj;
            removeDialog(WAIT_DLG);
            mList.setAdapter(adapter);
        }
    };
}
