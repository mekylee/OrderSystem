package com.example.ordersystem.fragment;


import com.example.ordersystem.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentOrderDetail extends Fragment {
	
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	   super.onCreateView(inflater, container, savedInstanceState); 
	   View view =inflater.inflate(R.layout.fragment_orderdetail_tab,container, false);
	 
	return view;
	
   }
   
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    }
}
