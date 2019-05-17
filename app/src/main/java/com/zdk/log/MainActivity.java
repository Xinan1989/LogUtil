package com.zdk.log;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zdk.log.log.LogUtil;

public class MainActivity extends AppCompatActivity {
   private static final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.i(TAG,"1111");
        LogUtil.d(TAG,"2222");
        LogUtil.w(TAG,"3333");
        LogUtil.e(TAG,"4444");
        LogUtil.i(TAG,1/0+"");
    }
}
