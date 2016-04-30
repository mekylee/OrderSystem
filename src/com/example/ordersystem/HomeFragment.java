package com.example.ordersystem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment{
	@Override
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//layout文件转换成View对象
		   /*resource:Fragment需要加载的自己的布局文件
		    * root:加载layout的父ViewGroup
		    * attachToRoot:false，不返回父ViewGroup
		    */
		   View view=inflater.inflate(R.layout.fragment_home, container, false);
		   TextView textview =(TextView)view.findViewById(R.id.textView1);
		   textview.setText("这里是首页");
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
