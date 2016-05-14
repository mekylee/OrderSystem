package com.example.ordersystem.activity;

import com.avos.avoscloud.LogUtil.log;
import com.example.ordersystem.R;
import com.example.ordersystem.broadcast.NetworkReceiver;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Actvitity_No_NetWork extends Activity {
	private Button refresh;
	private NetworkReceiver networkReceiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_no_network);
		refresh=(Button)findViewById(R.id.refresh_btn);
		refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			    Log.i("tag","ˢ������");
			    Toast.makeText(Actvitity_No_NetWork.this, "ˢ��ҳ��", Toast.LENGTH_SHORT).show();
			    Intent i=new Intent();
			    ComponentName componentName=new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
			    i.setComponent(componentName);
			    i.setAction("android.intent.anction.VIEW");
			    startActivity(i);
			    /*
			     * Todo�����ʵ�ֵ��ˢ�°�ť���ص�֮ǰ��Actvty
			     * �������1�������ť����ȡ����
			     * ����2��handler������ui����
			     */
		}
		});
	}
	
}
