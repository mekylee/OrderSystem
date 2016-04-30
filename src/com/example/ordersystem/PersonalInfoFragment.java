package com.example.ordersystem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PersonalInfoFragment extends Fragment{
   @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	   View view =inflater.inflate(R.layout.fragment_personalinfo, container, true);
	   TextView text=(TextView)view.findViewById(R.id.textView1);
	   text.setText("个人中心");
	   
	return super.onCreateView(inflater, container, savedInstanceState);
}
}
