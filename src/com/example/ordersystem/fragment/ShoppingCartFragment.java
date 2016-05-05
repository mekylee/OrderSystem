package com.example.ordersystem.fragment;
import com.avos.avoscloud.LogUtil.log;
import com.example.ordersystem.R;
import com.example.ordersystem.R.id;
import com.example.ordersystem.R.layout;
import com.example.ordersystem.activity.MyOrderActivity;
import com.example.ordersystem.entity.Order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingCartFragment extends Fragment {
	private View view;
	private Button commit_btn;
	private RadioButton edit_btn;
	private TextView total_price_tv;
   @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	//layout�ļ�ת����View����
	   /*resource:Fragment��Ҫ���ص��Լ��Ĳ����ļ�
	    * root:����layout�ĸ�ViewGroup
	    * attachToRoot:false�������ظ�ViewGroup
	    */
	   if(view==null){
	      view=inflater.inflate(R.layout.fragment_shoppingcart,null);
	      intialView(view);
	   }
	   ViewGroup parent = (ViewGroup) view.getParent();
		if(parent != null){
			parent.removeView(view);
		}
		return view;
	}
      
   
   public void intialView(View view){
	   edit_btn=(RadioButton)view.findViewById(R.id.radioButton1);
	   commit_btn=(Button)view.findViewById(R.id.commit_btn);
	   total_price_tv=(TextView)view.findViewById(R.id.total_price_tv);
	   edit_btn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			// TODO Auto-generated method stub
//			String backgoundColor="#1E90FF";
//		ackgound	int color=b
//			commit_btn.setBackgroundColor(#1E90FF);
		}
	});
	   commit_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(getActivity(),MyOrderActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "�����ύ�ɹ��������ĵȴ�ȷ��", Toast.LENGTH_SHORT).show();
		
		}
	});
   }


}
