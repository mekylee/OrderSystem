package com.example.ordersystem;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.SignUpCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity{
	private Button reg_btn;
	private EditText username_text,email_text,password_text,confirm_passw_text;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.register);
    	initialView();
     }
     
     
     private void  initialView(){
    	 reg_btn=(Button)findViewById(R.id.button1);
    	 username_text=(EditText)findViewById(R.id.editText1);
    	 password_text=(EditText)findViewById(R.id.editText2);
    	 confirm_passw_text=(EditText)findViewById(R.id.editText3);
    	 reg_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AVUser user=new AVUser();
				String username =username_text.getText().toString();
				String password=password_text.getText().toString();
				String confirmPassword=confirm_passw_text.getText().toString();
                if((username.length()<1&&username.length()>20)){
                	Toast.makeText(RegisterActivity.this, "用户名长度应为1-20位",Toast.LENGTH_SHORT).show();
                }else if(password.length()<6&&password.length()>16){
                	Toast.makeText(RegisterActivity.this, "密码名长度应为6-16位",Toast.LENGTH_SHORT).show();
                }else if(confirmPassword.equals(password)){
                	Toast.makeText(RegisterActivity.this, "两次密码输入不一致",Toast.LENGTH_SHORT).show();
                }else {
                	signUp(username,password,null);
                }
			
              }
       });
     }
     //注册
     private void signUp(String username,String password,String email){
    	 AVUser user=new AVUser();
    	 user.setUsername(username);
    	 user.setPassword(password);
    	 user.setEmail(email);
    	 user.signUpInBackground(new SignUpCallback() {
			
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
					log.e("tag", "用户可能已存在");
					//如果注册不成功，请检查一下返回的错误对象。最有可能的情况是，用户名或电子邮件已经被另一个用户注册，此时可以提示用户尝试用不同的用户名进行注册，也可以要求用户用 Email 做为用户名注册。
				}
			}
		});
     }

}
    