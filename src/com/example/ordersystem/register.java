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
     
     //ע��
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
					//ע��ɹ�
				}else{
					//ʧ�ܵ�ԭ��
					log.e("tag", "�û������Ѵ���");
					//���ע�᲻�ɹ�������һ�·��صĴ���������п��ܵ�����ǣ��û���������ʼ��Ѿ�����һ���û�ע�ᣬ��ʱ������ʾ�û������ò�ͬ���û�������ע�ᣬҲ����Ҫ���û��� Email ��Ϊ�û���ע�ᡣ
				}
			}
		});
     }
}
