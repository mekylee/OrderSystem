package com.example.ordersystem.fragment;

import com.avos.avoscloud.AVUser;
import com.example.ordersystem.R;
import com.example.ordersystem.activity.MainActivity;
import com.example.ordersystem.activity.MyCommentActivity;
import com.example.ordersystem.activity.MyOrderActivity;
import com.example.ordersystem.activity.ResetPassActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PersonalInfoFragment extends Fragment implements OnClickListener{
   private RelativeLayout myorder_layout,mycomemnt_layout,modifypass_layout;
   private Button logout_btn,modifypass_btn,mycomment_btn,myorder_btn;
   private View view;
   private ImageView user_img;
   private TextView username_tv;
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	   if(view == null){
	       view =inflater.inflate(R.layout.fragment_personalinfo,container,false);
	   initialView(view);
	   Log.i("tag", "初始化完成");
	   }
	   ViewGroup parent = (ViewGroup) view.getParent();
		if(parent != null){
			parent.removeView(view);
		}
	   return view;
	   
	
    }
   
   public void initialView(View view){
	   Log.i("tag","开始初始化视图");
	   myorder_layout=(RelativeLayout)view.findViewById(R.id.myorder_layout);
	   mycomemnt_layout=(RelativeLayout)view.findViewById(R.id.mycomment_layout);
	   modifypass_layout=(RelativeLayout)view.findViewById(R.id.modifypass_layout);
	   logout_btn=(Button)view.findViewById(R.id.logout_btn);
	   modifypass_btn=(Button)view.findViewById(R.id.modifypass_btn);
	   mycomment_btn=(Button)view.findViewById(R.id.mycomment_btn);
	   myorder_btn=(Button)view.findViewById(R.id.myorder_btn);
	   user_img=(ImageView)view.findViewById(R.id.user_image);
	   username_tv=(TextView)view.findViewById(R.id.account_tv);
	   mycomemnt_layout.setOnClickListener(this);
	   mycomment_btn.setOnClickListener(this);
	   modifypass_btn.setOnClickListener(this);
	   modifypass_layout.setOnClickListener(this);
	   logout_btn.setOnClickListener(this);
	   myorder_btn.setOnClickListener(this);
	   myorder_layout.setOnClickListener(this);
	   user_img.setOnClickListener(this);
	   AVUser current_user=AVUser.getCurrentUser();
	   String user_name=current_user.getUsername();
	   username_tv.setText(user_name);
	   
	   
   }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
			case R.id.myorder_btn:case R.id.myorder_layout:
				Intent intent=new Intent();
				intent.setClass(getActivity(), MyOrderActivity.class);
				startActivity(intent);
				break;
			case R.id.mycomment_btn:case R.id.mycomment_layout:
				Intent intent1=new Intent(getActivity(),MyCommentActivity.class);
				startActivity(intent1);
			    break;
			case R.id.modifypass_btn:case R.id.modifypass_layout:
				Intent intent2=new Intent(getActivity(),ResetPassActivity.class);
				startActivity(intent2);
				break;
			case R.id.logout_btn:
				Log.i("tag","登出前，当前用户为"+AVUser.getCurrentUser().getEmail());
				Intent i=new Intent(getActivity(),MainActivity.class);
				startActivity(i);
				AVUser.getCurrentUser().logOut();//清除缓存对象
			  
			case R.id.user_image:
				//Todo：修改头像
				break;
			default:
					break;
				
		    
		}
	}
}
