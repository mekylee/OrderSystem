package com.example.ordersystem.activity;

import com.example.ordersystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;

public class MyOrderActivity extends Activity {
	private RelativeLayout re1;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.activity_myorder);
	re1=(RelativeLayout)findViewById(R.id.re1);
	re1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MyOrderActivity.this,OrderActivity.class);
			startActivity(intent);
		}
	});
	
   }
}
