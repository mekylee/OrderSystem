package com.example.ordersystem.adapter;

import java.util.List;
import java.util.Map;

import com.avos.avoscloud.AVObject;
import com.example.ordersystem.R;
import com.example.ordersystem.activity.AddCommentActivity;
import com.example.ordersystem.entity.Cusine;
import com.example.ordersystem.entity.Order;
import com.example.ordersystem.fragment.FragmentOrderDishes;
import com.example.ordersystem.fragment.ShoppingCartFragment;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyOrderAdapter extends BaseAdapter{
    private List<Map<String, Object>> orders;
    private Context context;
    
    public  MyOrderAdapter(Context context,List<Map<String,Object>> orders) {
		// TODO Auto-generated constructor stub
    	this.context=context;
    	this.orders=orders;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return orders.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return orders.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.myorder_listview_item, null);
			viewHolder.numberOfCusines=(TextView)convertView.findViewById(R.id.number_of_dished_tv);
			viewHolder.cusineName=(TextView)convertView.findViewById(R.id.dish_name_tv);
			viewHolder.statusOfOrder=(TextView)convertView.findViewById(R.id.status_order_tv);
			viewHolder.totalPrice=(TextView)convertView.findViewById(R.id.total_price_tv);
			viewHolder.cusinePerPrice=(TextView)convertView.findViewById(R.id.dish_price_tv);
			viewHolder.cusineImage=(ImageView)convertView.findViewById(R.id.myorder_img);
			viewHolder.makeCommentBtn=(Button)convertView.findViewById(R.id.make_comment_btn);
		    convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
	//	 order=orders.get(position);
		
		//将控件绑定数据
		viewHolder.cusineImage.setImageResource(R.drawable.app_icon);
		viewHolder.cusineName.setText("宫保鸡丁");
		viewHolder.cusinePerPrice.setText("￥25");
	
	    viewHolder.makeCommentBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(context,AddCommentActivity.class);
				context.startActivity(i);
			}
		});
		
		return convertView;
	}
	
	
	public static class  ViewHolder{
		private TextView numberOfCusines,statusOfOrder,totalPrice,cusineName,cusinePerPrice;
	    private ImageView cusineImage;
		private Button makeCommentBtn;
	}
	
		
	

}
