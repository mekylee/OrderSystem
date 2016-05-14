package com.example.ordersystem.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import com.avos.avoscloud.LogUtil.log;
import com.example.ordersystem.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
    private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
    private Context context;
    private LayoutInflater inflater;
    public final class ListItemView{
    	public ImageView image;
    	public TextView price;
    	public TextView cusine_name;
    	public ImageView add_to_cart;
    }
	public ListViewAdapter(List<Map<String, Object>> data, Context context,
			LayoutInflater inflater) {
		super();
		this.data = data;
		this.context = context;
		this.inflater = inflater;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.i("method","getView");
		ListItemView  list_item_view=null;
		if(convertView==null){
			convertView=inflater.inflate(R.layout.fragment_order_dishes, null);
			list_item_view.image=(ImageView)convertView.findViewById(R.id.cusine_image);
			list_item_view.add_to_cart=(ImageView)convertView.findViewById(R.id.add_to_cart_image);
			list_item_view.cusine_name=(TextView)convertView.findViewById(R.id.cusine_name_tv);
			list_item_view.price=(TextView)convertView.findViewById(R.id.price_tv);
			//ÉèÖÃ¿Ø¼þµ½convertView
			convertView.setTag(list_item_view);
		}else{
			list_item_view=(ListItemView)convertView.getTag();
		}
		list_item_view.image.setBackgroundResource((Integer)data.get(position).get("image"));
		list_item_view.add_to_cart.setBackgroundResource((Integer)data.get(position).get("add_to_cart"));
		list_item_view.cusine_name.setText((String)data.get(position).get("cusine_name"));
		list_item_view.price.setText((String)data.get(position).get("price"));
		return convertView;
	}

}
