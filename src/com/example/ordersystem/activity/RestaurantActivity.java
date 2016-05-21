package com.example.ordersystem.activity;

import java.util.List;

import com.avos.avoscloud.AVObject;
import com.example.ordersystem.R;
import com.example.ordersystem.utils.AVService;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class RestaurantActivity extends Activity {
	private Button back_btn;
	private TextView rest_name,address,phone;
	private List<AVObject> restaurant;
	public  class RemoteGetRestInfo extends AsyncTask<Void, Void, List<AVObject>>{

		@Override
		protected List<AVObject> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			restaurant=AVService.findRestaurants();
			Log.i("tag", restaurant.toString());
			Log.i("tag", restaurant.get(0).getString("restName"));
			return restaurant;
		}
		
		@Override
		protected void onPostExecute(List<AVObject> result) {
			// TODO Auto-generated method stub
			restaurant=result;
			//在页面展现信息
			rest_name.setText(restaurant.get(0).getString("restName"));
			address.setText(restaurant.get(0).getString("restAddress"));
			phone.setText(restaurant.get(0).getString("restPhone"));

			super.onPostExecute(result);
		}
		
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.anctivity_restaurant_info);
		rest_name=(TextView)findViewById(R.id.rest_name);
		address=(TextView)findViewById(R.id.address);
		phone=(TextView)findViewById(R.id.phone);
        
		back_btn=(Button)findViewById(R.id.restar_back_btn);
		back_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		new RemoteGetRestInfo().execute();
		
    }
}
