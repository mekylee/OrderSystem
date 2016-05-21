package com.example.ordersystem.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ordersystem.R;
import com.example.ordersystem.adapter.MyOrderAdapter;
import com.example.ordersystem.entity.Order;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

public class MyOrderActivity extends Activity implements OnItemClickListener{
	//private RelativeLayout re1;
	private ListView myoder_listview;
	private List<Map<String,Object>> data;
	private List<Order> orders;
	private Button back_btn,make_comment_btn;
	private MyOrderAdapter myoderAdaper;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.activity_myorder);
	init();
	/*re1=(RelativeLayout)findViewById(R.id.re1);
	re1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MyOrderActivity.this,OrderActivity.class);
			startActivity(intent);
		}
	});*/

   }
	private void init() {
		myoder_listview=(ListView)findViewById(R.id.myorder_listview);
		/*myoder_listview.setAdapter(new SimpleAdapter(this, getData(), R.layout.myorder_listview_item, 
				new String[]{"number_of_dishes","status_of_order","dish_img","dish_name","dish_per_price","dish_total_price","commnet_btn"},
				new int[]{R.id.number_of_dished_tv,R.id.status_order_tv,R.id.myorder_img,R.id.dish_name_tv,R.id.dish_price_tv,R.id.total_price_tv,R.id.make_comment_btn}));
				*/
		data=getData();
		myoderAdaper=new MyOrderAdapter(this, data);
		myoder_listview.setAdapter(myoderAdaper);
	   myoder_listview.setOnItemClickListener(this);
		back_btn=(Button)findViewById(R.id.myorder_back_btn);
		back_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		/*myoderAdaper=new MyOrderAdapter(this, orders, this);
		myoder_listview.setAdapter(myoderAdaper);*/
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(MyOrderActivity.this,OrderActivity.class);
		startActivity(intent);
	}
	   
   public List<Map<String,Object>> getData(){
   	List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
   	for(int i=0;i<10;i++){
   		Map<String,Object> map=new HashMap<String,Object>();
   		map.put("dish_img", R.drawable.app_icon);
   		map.put("number_of_dishes", "共2件菜品");
   		map.put("dish_per_price", "￥20");
   	    map.put("dish_name", "酸菜鱼");
   	    map.put("comment_btn","去评价");
   	    map.put("status_of_order","已完成");
   	    map.put("dish_total_price", "总共： ￥100");
   	    data.add(map);
   	}
   	Log.i("tag", "数据加载完成");
   	return data;
   }

   
    
   
}
