package com.example.ordersystem.utils;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.example.ordersystem.constants.LeanCloudConf;



import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
    	// TODO Auto-generated method stub
    	super.onCreate();
    	 // ��������Ϊ this, AppId, AppKey
        AVOSCloud.initialize(this,LeanCloudConf.APP_ID,LeanCloudConf.APP_Key);
       
    }
} 
