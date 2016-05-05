package com.example.ordersystem.activity;

import com.example.ordersystem.R;
import com.example.ordersystem.broadcast.NetworkReceiver;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Window;

public class MyCommentActivity extends Activity {
	private NetworkReceiver networkReceiver;
   @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mycomemnt);
	}
   
   @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		networkReceiver =new NetworkReceiver();
		registerReceiver(networkReceiver, filter);
	}
	
    @Override
   protected void onPause() {
   	// TODO Auto-generated method stub
   	super.onPause();
   	unregisterReceiver(networkReceiver);  
   }
}
