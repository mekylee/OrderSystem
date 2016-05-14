package com.example.ordersystem.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ordersystem.R;
import com.example.ordersystem.broadcast.NetworkReceiver;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyCommentActivity extends Activity {
	private NetworkReceiver networkReceiver;
	private ListView mycomment_listview;
	private Button back_btn;
   @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mycomemnt);
		mycomment_listview=(ListView)findViewById(R.id.mycomment_listview);
    	mycomment_listview.setAdapter(new SimpleAdapter(this, getData(), R.layout.mycomment_lsitview_item,
    			new String[]{"comment_time","content"}, new int []{R.id.mycomment_time,R.id.mycomment_content}));
	   back_btn=(Button)findViewById(R.id.mycomment_back_btn);
	   back_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});
	   
   }
   
   public List<Map<String,Object>> getData(){
   	List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
   	for(int i=0;i<10;i++){
   	   Map<String,Object> map=new HashMap<String, Object>();
   	   map.put("comment_time","2016-05-07 11:00");
   	   map.put("content", "味道不错，下次再来");
   	   data.add(map);
   	}
   	return data;
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
