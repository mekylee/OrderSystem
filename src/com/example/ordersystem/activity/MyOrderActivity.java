package com.example.ordersystem.activity;

import com.example.ordersystem.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MyOrderActivity extends Activity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.activity_myorder);
   }
}
