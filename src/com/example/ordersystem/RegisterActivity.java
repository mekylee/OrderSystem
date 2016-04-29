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
                	Toast.makeText(RegisterActivity.this, "�û�������ӦΪ1-20λ",Toast.LENGTH_SHORT).show();
                }else if(password.length()<6&&password.length()>16){
                	Toast.makeText(RegisterActivity.this, "����������ӦΪ6-16λ",Toast.LENGTH_SHORT).show();
                }else if(confirmPassword.equals(password)){
                	Toast.makeText(RegisterActivity.this, "�����������벻һ��",Toast.LENGTH_SHORT).show();
                }else {
                	signUp(username,password,null);
                }
			
              }
       });
     }
     //ע��
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
					//ע��ɹ�
					Toast.makeText(RegisterActivity.this, "ע��ɹ�", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
				    startActivity(intent);
				}else{
					//ʧ�ܵ�ԭ��
					log.e("tag", "�û������Ѵ���");
					//���ע�᲻�ɹ�������һ�·��صĴ���������п��ܵ�����ǣ��û���������ʼ��Ѿ�����һ���û�ע�ᣬ��ʱ������ʾ�û������ò�ͬ���û�������ע�ᣬҲ����Ҫ���û��� Email ��Ϊ�û���ע�ᡣ
				}
			}
		});
     }

}
    