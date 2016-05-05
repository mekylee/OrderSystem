package com.example.ordersystem.fragment;

import com.example.ordersystem.R;
import com.example.ordersystem.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment{
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//layout�ļ�ת����View����
		  /* resource:Fragment��Ҫ���ص��Լ��Ĳ����ļ�
		    * root:����layout�ĸ�ViewGroup
		    * attachToRoot:false�������ظ�ViewGroup
		   */
		if(view==null){
		   view=inflater.inflate(R.layout.fragment_home, null);
		   TextView tv=(TextView)view.findViewById(R.id.home_tv);
		   tv.setText("Welcome to home ��");
		}
		ViewGroup parent = (ViewGroup) view.getParent();
		if(parent != null){
			parent.removeView(view);
		}
		return view;
	}
}
