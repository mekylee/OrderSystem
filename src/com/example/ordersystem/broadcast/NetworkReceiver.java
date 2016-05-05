package com.example.ordersystem.broadcast;

import com.avos.avoscloud.LogUtil.log;
import com.example.ordersystem.activity.Actvitity_No_NetWork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NetworkReceiver extends BroadcastReceiver {
    /**
     * 判断网络状态
     */
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
       ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo wifiNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
       NetworkInfo mobNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
       if(!wifiNetInfo.isConnected()&& ! mobNetInfo.isConnected()){
    	   Log.i("tag", "网络断开，请检查网络连接");
    	   Toast.makeText(context, "网络断开，请检查网络连接", Toast.LENGTH_SHORT).show();
           Intent i=new Intent();
           i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           i.setClass(context, Actvitity_No_NetWork.class);
           context.startActivity(i);
            
       }
       else {
    	   Log.i("tag", "网络正常"+intent.toString());
       }
     /*  if(activeNetInfo!=null){
    	   Toast.makeText(context, "网络连接类型为:"+activeNetInfo.getTypeName(), Toast.LENGTH_SHORT).show();
    	   
       }
       if(mobNetInfo!=null){
    	   Toast.makeText(context, "网络连接类型为:"+mobNetInfo.getTypeName(), Toast.LENGTH_SHORT).show();

       }
		Log.i("tag","网络断开"+intent.toString())*/;
	}

}
