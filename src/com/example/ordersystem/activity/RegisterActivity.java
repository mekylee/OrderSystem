package com.example.ordersystem.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.SignUpCallback;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ordersystem.R;
import com.example.ordersystem.activity.LoginActivity;
import com.example.ordersystem.broadcast.NetworkReceiver;
import com.example.ordersystem.customview.CleanableEditText;
import com.example.ordersystem.utils.*;
public class RegisterActivity extends Activity{
	private Button reg_btn,back_btn;
	private CleanableEditText  email_text,password_text,confirm_passw_text;
	util  util=new util();
	private NetworkReceiver networkReceiver ;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.acitivity_register);
    	initialView();
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
     
     private void  initialView(){
    	 reg_btn=(Button)findViewById(R.id.reg_btn);
    	 back_btn=(Button)findViewById(R.id.back_btn);
    	 email_text=(CleanableEditText)findViewById(R.id.editText1);
    	 password_text=(CleanableEditText)findViewById(R.id.editText2);
    	 confirm_passw_text=(CleanableEditText)findViewById(R.id.editText3);
    	 back_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				RegisterActivity.this.finish();
			}
		});
    	 reg_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String password=password_text.getText().toString();
				String confirmPassword=confirm_passw_text.getText().toString();
				String email=email_text.getText().toString();
				/**
				 * 判断用户输入的邮箱地址、密码不符合要求，提示
				 */
                if(email.isEmpty()==true||util.isEmail(email)==false){
                	Toast.makeText(RegisterActivity.this, "邮箱地址不正确", 2000).show();
                }
                else if(password.length()<6||password.length()>16){
                	Toast.makeText(RegisterActivity.this, "密码名长度应为6-16位",2000).show();
                }else if(confirmPassword.equals(password)==false){
                	Toast.makeText(RegisterActivity.this, "两次密码输入不一致",2000).show();
                }else {
                	signUp(email,password);
                }
			
              }
       });
				
     }
     /*
      * 注册
      * 必填项：用户名、邮箱地址、密码、确认密码
      */
     public  void signUp(String email,String password){
    	 AVUser User=new AVUser();  //创建一个leadCloud自带的User,需要制定用户名和密码、电子邮件
    	 User.setUsername(email);
    	 User.setEmail(email);
    	 User.setPassword(password);
    	 User.signUpInBackground(new SignUpCallback() {
			
			@Override
			public void done(AVException arg0) {
				// TODO Auto-generated method stub
				if(arg0==null){
					//注册成功
					Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
				    startActivity(intent);
				}else{
					//失败的原因
					Toast.makeText(RegisterActivity.this, arg0.toString(), Toast.LENGTH_SHORT).show();
					log.e("tag",arg0.toString());
					//如果注册不成功，请检查一下返回的错误对象。最有可能的情况是，用户名或电子邮件已经被另一个用户注册，此时可以提示用户尝试用不同的用户名进行注册，也可以要求用户用 Email 做为用户名注册。
				}
			}
		});
		
     }
     
     
     
     
    
}
    