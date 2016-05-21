package com.example.ordersystem.adapter;

import java.util.List;

import com.avos.avoscloud.AVObject;
import com.example.ordersystem.R;
import com.example.ordersystem.entity.Menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
    private Context context;
    private List<AVObject> menus;
    public MenuAdapter(Context context,List<AVObject> menus) {
		// TODO Auto-generated constructor stub
    	this.context=context;
    	this.menus=menus;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return menus.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return menus.get(position);
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
			convertView=LayoutInflater.from(context).inflate(R.layout.home_menu_listview_item, null);
			viewHolder.menu=(TextView)convertView.findViewById(R.id.menu_tv);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		//°ó¶¨Êý¾Ý
		viewHolder.menu.setText(menus.get(position).getString("menu_name"));
		return convertView;
	}
	
	private class ViewHolder{
		private TextView menu;
		
	}

}
