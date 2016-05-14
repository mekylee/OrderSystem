package com.example.ordersystem.activity;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.example.ordersystem.constants.LeanCloudConf;


import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
    	// TODO Auto-generated method stub
    	super.onCreate();
    	

    
    	 // 三个参数为 this, AppId, AppKey
        AVOSCloud.initialize(this,LeanCloudConf.APP_ID,LeanCloudConf.APP_Key);
    }
} 
