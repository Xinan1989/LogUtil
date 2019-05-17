package com.zdk.log;

import android.app.Application;

import com.zdk.log.log.CrashHandler;

/**
 * Des:
 * autour: wangxa
 * date: 5/1/2019 16:23
 */
public class CustomApplication extends Application {
    public static CustomApplication instance=null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        //捕获异常机制
        CrashHandler.getInstance().init(getApplicationContext());
    }

    public static CustomApplication getInstance(){
        return instance;
    }
}
