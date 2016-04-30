package com.example.ordersystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity implements OnCheckedChangeListener{
  private RadioGroup radiogroup;
  private RadioButton home_btn,cart_btn,mine_btn;
  
  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
  
   private void initiaView(){
	   radiogroup=(RadioGroup)findViewById(R.id.radioGroup1);
	   home_btn=(RadioButton)findViewById(R.id.radio0);
	   cart_btn=(RadioButton)findViewById(R.id.radio1);
	   mine_btn=(RadioButton)findViewById(R.id.radio2);
	   radiogroup.setOnCheckedChangeListener(this);
   }

@Override
public void onCheckedChanged(RadioGroup arg0, int arg1) {
	// TODO Auto-generated method stub
	switch(arg1){
	case R.id.radio0:
		Intent intent =new Intent(MainActivity.this,HomeFragment.class);
		startActivity(intent);
		break;
	case R.id.radio1:
		Intent intent1 =new Intent(MainActivity.this,ShoppingCartFragment.class);
		startActivity(intent1);
		break;
	case R.id.radio2:
		Intent intent2 =new Intent(MainActivity.this,PersonalInfoFragment.class);
		startActivity(intent2);
		break;
		default:
			break;
	}
}
}
