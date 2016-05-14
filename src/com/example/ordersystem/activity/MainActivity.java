package com.example.ordersystem.activity;


import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogUtil.log;
import com.example.ordersystem.R;
import com.example.ordersystem.R.id;
import com.example.ordersystem.R.layout;
import com.example.ordersystem.R.menu;
import com.example.ordersystem.broadcast.NetworkReceiver;
import com.example.ordersystem.constants.LeanCloudConf;
import com.example.ordersystem.fragment.CartFragmentNoLogin;
import com.example.ordersystem.fragment.FragmentHome1;
import com.example.ordersystem.fragment.UserFragmentNoLogin;
import com.example.ordersystem.fragment.HomeFragment;
import com.example.ordersystem.fragment.PersonalInfoFragment;
import com.example.ordersystem.fragment.ShoppingCartFragment;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener{
  private RadioGroup radiogroup;
  private NetworkReceiver networkReceiver;
  private FragmentManager manager;
  private FragmentTransaction tran;
  private LeanCloudConf conf;
  private AVUser currentUser;  //当前用户
  private HomeFragment home;
  private ShoppingCartFragment cartFragment;
  private PersonalInfoFragment mine;
  private CartFragmentNoLogin fragment;
  private UserFragmentNoLogin  userFragmentNoLogin;
  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		  cartFragment=new ShoppingCartFragment();
		   home=new HomeFragment();
		   mine=new PersonalInfoFragment();
		   fragment=new CartFragmentNoLogin();
		   userFragmentNoLogin=new UserFragmentNoLogin();
		initiaView();
	}
  
   private void initiaView(){
	   manager=getSupportFragmentManager();
	   tran=manager.beginTransaction();
	   tran.replace(R.id.main_content, new HomeFragment());
//	   tran.replace(R.id.main_content, new FragmentHome1());
	   tran.commit();
	 
	   radiogroup=(RadioGroup)findViewById(R.id.radioGroup1);
	   radiogroup.setOnCheckedChangeListener(this);
	 
   }

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		tran=manager.beginTransaction();
		switch(arg1){
		case R.id.home: //主页
//			HomeFragment home=new HomeFragment();	
//			FragmentHome1 home=new FragmentHome1();
			tran.replace(R.id.main_content,home);
			Log.i("tag","处于主页");
			break;
		case R.id.cart:  //购物车
			/**
			 * 判断购物车是否为空，如果用户已经登录，则跳转到个人中心页面
			 * 如果用户未登录，则跳转到提示用户登录的页面
			 */
			currentUser=AVUser.getCurrentUser();
	        if (currentUser != null) {
			
//	           ShoppingCartFragment cartFragment=new  ShoppingCartFragment();
	           tran.replace(R.id.main_content,cartFragment);
	           Log.i("tag","处于购物车");
	        }else{
//	        	CartFragmentNoLogin  fragment=new CartFragmentNoLogin();
	        	tran.replace(R.id.main_content, fragment);
	        }
	        break;
		case R.id.profile: //个人中心
			/**
			 * 判断用户的登录状态，如果用户已经登录，则跳转到个人中心页面，
			 * 如果用户未登录，则跳转到提示用户登录的页面
			 */
			currentUser=AVUser.getCurrentUser();
	        if (currentUser != null) {
	            // 跳转到个人中心页面
//	        	PersonalInfoFragment mine=new PersonalInfoFragment();
				tran.replace(R.id.main_content,mine);
				 Log.i("tag","个人中心");
	        } else {
	            //缓存用户对象为空时，可打开用户注册界面…
//	        	UserFragmentNoLogin  fragment=new UserFragmentNoLogin();
	        	tran.replace(R.id.main_content, userFragmentNoLogin);
	        }
			
			 break;
			default:
				break;
		}
		tran.commit();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		networkReceiver =new NetworkReceiver();
		registerReceiver(networkReceiver, filter);
	}
	
     @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	unregisterReceiver(networkReceiver);  
    }
	
	//菜单项
			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.main, menu);
				return true;
			}
			
			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,SearchActivity.class);  //跳转到搜索页面
				startActivity(intent);
				
				return super.onOptionsItemSelected(item);
			}
		    
			
}
