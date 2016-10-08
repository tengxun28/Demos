package com.iboxpay.android.versiondemo;


/**
 * Created by wangyaohui on 16-6-1.
 */
public class JniDemo {
    public native int fun(); //以native 申明JNI函数

    public native int fun2(int a, long b, float c, double d, boolean e, String f, int [] as, float[] bs, byte[] data);

    static {
        System.loadLibrary("jnidemo");
        Log.e("loadLib success!!!");
    }

    public  JniDemo(){
        Log.e("nativeOpen:" + fun()  + ", "  +  fun2(2,1,0.5f,31.0d,true,"hello",new int[]{3,5,7},new float[] {1.1f,2.2f},new byte[]{0x31}));
    }


}
