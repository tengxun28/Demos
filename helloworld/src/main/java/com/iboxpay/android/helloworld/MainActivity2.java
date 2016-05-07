package com.iboxpay.android.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import java.util.List;


public class MainActivity2 extends AppCompatActivity {
    public static final String TAG = "wangyaohui";
    public LocationClient mLocationClient = null;
    private SeekBar sound;
    private AudioManager audioManager;
    private MyVolumeReceiver mVolumeReceiver;

    public BDLocationListener myListener = new MyLocationListener();

    String[] list = new String[]{"hello"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView)findViewById(R.id.tv);
       // tv.setBackgroundResource(R.drawable.icontext_bg);
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE) ;
        sound = (SeekBar) findViewById(R.id.sb_sound);
        myRegisterReceiver();

        BroadcastReceiver b = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.e(TAG, "action:" + action);
            }
        };
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        IntentFilter filter = new IntentFilter("android.intent.action.ROMACTION");

        registerReceiver(b,filter);

        //View v = new View(this);
        //v.setBackgroundColor();

        Color c = null;


       /* mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监
        initLocation();
        mLocationClient.start();*/

       /* LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        List<String> list = lm.getAllProviders();
        for(String i : list) {
            Log.e(TAG, "provider:" + i);
        }

        LocationListener ll = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };*/

        //lm.requestLocationUpdates("gps",0,0,ll);
        Log.e(TAG, "getStatusBarHeight():" + getStatusBarHeight());

    }

    //  statusbar height:  1280x720   50px,25dp
    private int getStatusBarHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.e(TAG, "Status height:" + height);
        return height;
    }




    /**
     * 注册当音量发生变化时接收的广播
     */
    private void myRegisterReceiver(){
        mVolumeReceiver = new MyVolumeReceiver() ;
        IntentFilter filter = new IntentFilter() ;
        filter.addAction("android.media.VOLUME_CHANGED_ACTION") ;
        registerReceiver(mVolumeReceiver, filter) ;
    }

    private void unregisterReceiver() {
        unregisterReceiver(mVolumeReceiver);
    }

    /**
     * 处理音量变化时的界面显示
     * @author long
     */
    private class MyVolumeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //如果音量发生变化则更改seekbar的位置
            if(intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")){
                Log.e(TAG, "android.media.VOLUME_CHANGED_ACTION");
                int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) ;// 当前的媒体音量
                Log.e(TAG, "currVolume:" + currVolume);
                if(sound != null) {
                    sound.setProgress(currVolume);
                }
            }
        }
    }

 /*   @Override
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
    }*/


    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.e("BaiduLocationApiDem", sb.toString());
        }
    }
        private void initLocation(){
            LocationClientOption option = new LocationClientOption();
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
            );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
            int span=1000;
            option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
            option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            option.setOpenGps(true);//可选，默认false,设置是否使用gps
            option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            option.setIgnoreKillProcess(false);//可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
            option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
            option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
            mLocationClient.setLocOption(option);
        }
}
