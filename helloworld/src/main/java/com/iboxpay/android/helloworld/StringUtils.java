package com.iboxpay.android.helloworld;

/**
 * Created by wangyaohui on 15-12-8.
 */
public class StringUtils {

        public static boolean isEmpty(String str) {

            if (str == null || str.length() == 0)
                return true;
            else
                return false;
        }
}
