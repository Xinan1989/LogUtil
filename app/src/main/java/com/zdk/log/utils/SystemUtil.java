package com.zdk.log.utils;

/**
 * Des:
 * autour: wangxa
 * date: 5/1/2019 17:07
 */
public class SystemUtil {
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }
}
