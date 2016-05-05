package com.example.ordersystem.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.RequestPasswordResetCallback;
import com.example.ordersystem.R;
import com.example.ordersystem.R.id;
import com.example.ordersystem.R.layout;
import com.example.ordersystem.broadcast.NetworkReceiver;
import com.example.ordersystem.customview.CleanableEditText;
import com.example.ordersystem.utils.util;;
public class ResetPassActivity extends Activity implements OnClickListener{
	private Button reset_btn,back_btn;
	private CleanableEditText email;
	private NetworkReceiver networkReceiver;
	util util=new util();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_resetpass);
		email=(CleanableEditText)findViewById(R.id.reset_txt);
		reset_btn=(Button)findViewById(R.id.reset_btn);
		back_btn=(Button)findViewById(R.id.back_btn);
		back_btn.setOnClickListener(this);
		reset_btn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		   case R.id.back_btn:
			   this.finish();
			   break;
		   case R.id.reset_btn:
				//发送重置密码邮件到邮箱中
				sendResetEmail(email.getText().toString());
				break;
				default:
					break;
		}
	}
	private void sendResetEmail(String email){
		//检查Email的格式是否正确
		if(util.isEmail(email)==false||email.isEmpty()==true){
			Toast.makeText(ResetPassActivity.this, "邮箱地址不正确", 1000).show();
		}else{
			AVUser.requestPasswordResetInBackground(email.trim(), new RequestPasswordResetCallback() {

				@Override
				public void done(AVException arg0) {
					// TODO Auto-generated method stub
					if(arg0==null){
						Toast.makeText(ResetPassActivity.this, "请到邮箱重置密码", 1000).show();
						Log.i("tag","发送密码重置邮件成功");
						
					}
					else{
						Toast.makeText(ResetPassActivity.this, "发送邮件失败，请检查网络", 1000).show();
						Log.i("tag", arg0.toString());
					}
				}
			});
		}
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
	

}
