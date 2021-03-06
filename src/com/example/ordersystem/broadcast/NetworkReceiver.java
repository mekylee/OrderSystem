package com.example.ordersystem.broadcast;

import com.avos.avoscloud.LogUtil.log;
import com.example.ordersystem.R;
import com.example.ordersystem.activity.Actvitity_No_NetWork;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class NetworkReceiver extends BroadcastReceiver {
    /**
     * �ж�����״̬
     */
	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub
       ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo wifiNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
       NetworkInfo mobNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
       if(!wifiNetInfo.isConnected()&& ! mobNetInfo.isConnected()){
    	   Log.i("tag", "����Ͽ���������������");
    	   Toast.makeText(context, "����Ͽ���������������", Toast.LENGTH_SHORT).show();
          /* Intent i=new Intent();
           i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           i.setClass(context, Actvitity_No_NetWork.class);
           context.startActivity(i);*/
    	   AlertDialog.Builder builder=new AlertDialog.Builder(context);
           builder.setIcon(R.drawable.wifi);
           builder.setTitle("������ʾ");
           builder.setMessage("����Ͽ�����������״̬");
           builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			             dialog.dismiss();	
			}
		});
           builder.setPositiveButton("����", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				 Intent i=new Intent(Settings.ACTION_WIFI_SETTINGS);
				 context.startActivity(i);
				
			}
		});
           builder.create().show();
            
       }
       else {
    	   Log.i("tag", "��������"+intent.toString());
       }
     
	}

}
