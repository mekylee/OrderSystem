package com.example.ordersystem.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.ordersystem.R;
import com.example.ordersystem.activity.RestaurantActivity;
import com.example.ordersystem.adapter.FragmentAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

public class HomeFragment extends Fragment{
	private View view;
	private ViewPager viewPager;
	private List<Fragment> frag_list=new ArrayList<Fragment>();
	private FragmentOrderDishes order_dished_frag;  
	private FragmentComment comment_frag;  
	private FragmentAdapter fragment_adapter;
	private TextView order_dishes_tv,comment_tv;  //tab����ʾ����
	private LinearLayout order_dishes_layout,comment_layout;
	private int current_index ;   //��ǰtabͣ����ҳ��
	private ImageButton rest_logo_ibtn;	
	private SearchView searchView;
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
		   findId(view);
		   init();
		}
		ViewGroup parent = (ViewGroup) view.getParent();
		if(parent != null){
			parent.removeView(view);
		}
		return view;
	}
	
	public void findId(View view){
		viewPager=(ViewPager)view.findViewById(R.id.home_viewpager);
		order_dishes_tv=(TextView)view.findViewById(R.id.order_dishes_tv);
		comment_tv=(TextView)view.findViewById(R.id.comment_tv);
		comment_layout=(LinearLayout)view.findViewById(R.id.comment_layout);
		order_dishes_layout=(LinearLayout)view.findViewById(R.id.order_dished_layout);
		rest_logo_ibtn = (ImageButton) view.findViewById(R.id.rest_logo_btn);
		searchView = (SearchView)view.findViewById(R.id.search_view);
	}
	
	public void init(){
		order_dished_frag=new FragmentOrderDishes(); //������ҳչʾ����ˡ���Fragment����
		Log.i("tag","����fragment:"+order_dished_frag.toString());
		comment_frag=new FragmentComment(); //������ҳչʾ�����ۡ���Fragment����
		Log.i("tag","����fragment:"+comment_frag.toString());
		frag_list.add(order_dished_frag);
		frag_list.add(comment_frag);
		//����fragment��������
		fragment_adapter=new FragmentAdapter(getActivity().getSupportFragmentManager(), frag_list);  
		Log.i("tag","����fragment������:"+fragment_adapter.toString());
		viewPager.setAdapter(fragment_adapter);//��fragment����������ʵ��fragment���л�
		Log.i("tag","��������");
		viewPager.setCurrentItem(0);
   	    viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				resetBackground();
			    switch(arg0){
			    case 0:
			    	//���ѡ�񡰵�ˡ����򡰵��"�ı�����Ϊ��ɫ
			        order_dishes_layout.setBackgroundColor(getResources().getColor(R.color.hulanse));
			        order_dishes_tv.setTextColor(getResources().getColor(R.color.white));
			        break;
			    case 1:
			    	//���ѡ�����ۡ���������"�ı�����Ϊ��ɫ
			        comment_layout.setBackgroundColor(getResources().getColor(R.color.hulanse));
			        comment_tv.setTextColor(getResources().getColor(R.color.white));
			        break;
			    }
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
	               
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
   	 
   	 rest_logo_ibtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				  Intent i=new Intent(getActivity(),RestaurantActivity.class);
				   startActivity(i);
			}
		});
   	  
   	  searchView.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Log.d("tag", "��ת��SearchView Activity");
			Intent i=new Intent(getActivity(),SearchView.class);
			startActivity(i);
		}
	});
	}
	
	public void resetBackground(){
		order_dishes_tv.setTextColor(getResources().getColor(R.color.black));
		order_dishes_layout.setBackgroundColor(getResources().getColor(R.color.white));
		comment_layout.setBackgroundColor(getResources().getColor(R.color.white));
	    comment_tv.setTextColor(getResources().getColor(R.color.black));
	}
	
}
