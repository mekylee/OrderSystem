package com.example.ordersystem.fragment;

import com.example.ordersystem.R;
import com.example.ordersystem.activity.LoginActivity;
import com.example.ordersystem.activity.RegisterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserFragmentNoLogin extends Fragment implements OnClickListener{
	private View view;
	private TextView login_tv,register_tv;
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	 view=inflater.inflate(R.layout.fragment_profile_no_login, container,false);
    	 login_tv=(TextView)view.findViewById(R.id.login_tv);
    	 register_tv=(TextView)view.findViewById(R.id.register_tv);
    	 login_tv.setOnClickListener(this);
    	 register_tv.setOnClickListener(this);
    	return view;
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.login_tv:
			Intent intent=new Intent(getActivity(),LoginActivity.class);
			startActivity(intent);
			break;
		case R.id.register_tv:
			Intent intent1=new Intent(getActivity(),RegisterActivity.class);
			startActivity(intent1);
			break;
		}
	}
}
