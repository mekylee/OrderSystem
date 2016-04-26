package com.example.ordersystem;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.SignUpCallback;

import android.app.Activity;
import android.os.Bundle;

public class register extends Activity {
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    }
     
     //注册
     private void signUp(){
    	 AVUser user=new AVUser();
    	 user.setUsername("Tom");
    	 user.setPassword("ad1");
    	 user.setEmail("tom@124.com");
    	 user.signUpInBackground(new SignUpCallback() {
			
			@Override
			public void done(AVException arg0) {
				// TODO Auto-generated method stub
				if(arg0==null){
					//注册成功
				}else{
					//失败的原因
					log.e("tag", "用户可能已存在");
					//如果注册不成功，请检查一下返回的错误对象。最有可能的情况是，用户名或电子邮件已经被另一个用户注册，此时可以提示用户尝试用不同的用户名进行注册，也可以要求用户用 Email 做为用户名注册。
				}
			}
		});
     }
}
