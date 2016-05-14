package com.example.ordersystem.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.ordersystem.R;
import com.example.ordersystem.adapter.FragmentAdapter;
import com.example.ordersystem.fragment.FragmentOrderDetail;
import com.example.ordersystem.fragment.FragmentOrderStatus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderActivity extends FragmentActivity {
	private List<Fragment> fragment_list =new ArrayList<Fragment>();
	private FragmentAdapter fragmentAdapter;
	private ViewPager viewPager;
	private FragmentOrderDetail detail_fragment;
	private FragmentOrderStatus status_framgment;
	/*
	 * Tab显示的内容
	 */
	private TextView order_status,order_detail;
	/*
	 * tab下面的引导线
	 */
	private ImageView tab_line;
	/*
	 * 屏幕的宽度
	 */
	private  int screen_width;
	/*
	 * 当前tab停靠的位置
	 */
	private int current_index;
     @Override
    protected void onCreate(Bundle arg0) {
    	// TODO Auto-generated method stub
    	super.onCreate(arg0);
    	setContentView(R.layout.activity_order);
    	findById();
    	init();
    	initTabLineWidth();
    }
  
     
     public void findById(){
    	 viewPager=(ViewPager)this.findViewById(R.id.order_viewpager);
    	 order_detail=(TextView)this.findViewById(R.id.orderdetail_tv);
    	 order_status=(TextView)this.findViewById(R.id.orderstatus_tv);
    	 tab_line=(ImageView)this.findViewById(R.id.tab_line);
     }
     
     public void init(){
    	 detail_fragment =new FragmentOrderDetail();
    	 status_framgment=new FragmentOrderStatus();
    	 fragment_list.add(status_framgment);
    	 fragment_list.add(detail_fragment);
    	
    	 fragmentAdapter =new FragmentAdapter(this.getSupportFragmentManager(), fragment_list);
    	 viewPager.setAdapter(fragmentAdapter);
    	 viewPager.setCurrentItem(0);
    	 viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
			   
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				 LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)tab_line.getLayoutParams();  
	                Log.e("offset:", arg1 + "");  
	                /** 
	                 * 利用current_index(当前所在页面)和position(下一个页面)以及offset来 
	                 * 设置tab_line的左边距 滑动场景： 
	                 * 记2个页面, 
	                 * 从左到右分别为0,1
	                 * 0->1; 1->2
	                 */  
	  
	                if (current_index == 0 && arg0 == 0)// 0->1  
	                {  
	                    lp.leftMargin = (int) (arg1 * (screen_width * 1.0 / 2) + current_index * (screen_width / 2));  
	  
	                } else if (current_index == 1 && arg0 == 0) // 1->0  
	                {  
	                    lp.leftMargin = (int) (-(1 - arg1) * (screen_width * 1.0 / 2) + current_index * (screen_width / 2));  
	  
	                } 
	                tab_line.setLayoutParams(lp);  
	                
	               
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    	 
     }
     
     /** 
      * 设置滑动条的宽度为屏幕的1/2(根据Tab的个数而定) 
      */  
     private void initTabLineWidth() {  
         DisplayMetrics dpMetrics = new DisplayMetrics();  
         getWindow().getWindowManager().getDefaultDisplay()  
                 .getMetrics(dpMetrics);  
         screen_width = dpMetrics.widthPixels;  
         LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tab_line  .getLayoutParams();  
         lp.width = screen_width /2;  
         tab_line.setLayoutParams(lp);  
     }  
     
     
}
