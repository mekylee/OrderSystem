package com.example.ordersystem;

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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.ordersystem.utils.*;
public class RegisterActivity extends Activity{
	private Button reg_btn;
	private EditText email_text,password_text,confirm_passw_text;
	util  util=new util();
     @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.register);
    	initialView();
     }
     
     
     private void  initialView(){
    	 reg_btn=(Button)findViewById(R.id.button1);
    	 email_text=(EditText)findViewById(R.id.editText1);
    	 password_text=(EditText)findViewById(R.id.editText2);
    	 confirm_passw_text=(EditText)findViewById(R.id.editText3);
    	 reg_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String password=password_text.getText().toString();
				String confirmPassword=confirm_passw_text.getText().toString();
				String email=email_text.getText().toString();
				/**
				 * �ж��û�����������ַ�����벻����Ҫ����ʾ
				 */
                if(email.isEmpty()==true||util.isEmail(email)==false){
                	Toast.makeText(RegisterActivity.this, "�����ַ����ȷ", 3000).show();
                }
                else if(password.length()<6||password.length()>16){
                	Toast.makeText(RegisterActivity.this, "����������ӦΪ6-16λ",3000).show();
                }else if(confirmPassword.equals(password)==false){
                	Toast.makeText(RegisterActivity.this, "�����������벻һ��",3000).show();
                }else {
                	signUp(email,password);
                }
			
              }
       });
				
     }
     /*
      * ע��
      * ������û����������ַ�����롢ȷ������
      */
     public  void signUp(String email,String password){
    	 AVUser User=new AVUser();  //����һ��leadCloud�Դ���User,��Ҫ�ƶ��û��������롢�����ʼ�
    	 User.setUsername(email);
    	 User.setEmail(email);
    	 User.setPassword(password);
    	 User.signUpInBackground(new SignUpCallback() {
			
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
					Toast.makeText(RegisterActivity.this, arg0.toString(), Toast.LENGTH_SHORT).show();
					log.e("tag",arg0.toString());
					//���ע�᲻�ɹ�������һ�·��صĴ���������п��ܵ�����ǣ��û���������ʼ��Ѿ�����һ���û�ע�ᣬ��ʱ������ʾ�û������ò�ͬ���û�������ע�ᣬҲ����Ҫ���û��� Email ��Ϊ�û���ע�ᡣ
				}
			}
		});
		
     }
     
     
     
     
    
}
    