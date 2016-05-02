package com.example.ordersystem;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.avos.avoscloud.LogUtil.log;
import com.example.ordersystem.HomeFragment;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener{
  private RadioGroup radiogroup;
  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	}
  
   private void initiaView(){
	   radiogroup=(RadioGroup)findViewById(R.id.radioGroup1);
	   HomeFragment home=new HomeFragment();
	   getSupportFragmentManager().beginTransaction().replace(R.id.main_content,home).commit();
	   radiogroup.setOnCheckedChangeListener(this);
   }

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch(arg1){
		case R.id.home: //主页
			HomeFragment home=new HomeFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.main_content,home).commit();
			Log.i("tag","处于主页");
			break;
		case R.id.cart:  //购物车
	        ShoppingCartFragment cartFragment=new  ShoppingCartFragment();
	        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,cartFragment).commit();
	        Log.i("tag","处于主页");
	        break;
		case R.id.profile: //个人中心
			PersonalInfoFragment mine=new PersonalInfoFragment();
			 getSupportFragmentManager().beginTransaction().replace(R.id.main_content,mine).commit();
			 Log.i("tag","处于主页");
			 break;
			default:
				break;
		}
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
