
package com.example.ordersystem.fragment;

import com.example.ordersystem.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentOrderStatus extends Fragment {
	private TextView orderstatus_tv;
	   @Override
	   public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		   View view =inflater.inflate(R.layout.fragment_orderstatus_tab,container, false);
		   initial(view);
		return view;
		
	   }
	   
	   public void initial(View view){
		   orderstatus_tv=(TextView)view.findViewById(R.id.orderstatus_tv);
		   
				   
	   }
}
