package com.example.ordersystem.activity;

import com.example.ordersystem.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCommentActivity extends Activity {
	private Button commit_btn,back_btn;
	private EditText comment_ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_addcomment);
    	comment_ed=(EditText)findViewById(R.id.comment_content);
    	back_btn=(Button)findViewById(R.id.myorder_back_btn);
    	commit_btn=(Button)findViewById(R.id.commit_btn);
    	commit_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				String content=comment_ed.getText().toString();
				if(content.isEmpty()){
					Toast.makeText(AddCommentActivity.this, "请输入评价内容再提交哦", 1000).show();
				}else{
					//在我的评价页面新增一条评价记录，并且在首页的评价页面中显示最新的评价
					Toast.makeText(AddCommentActivity.this, "您输入的内容是"+content,1000).show();
					//跳转到我的评价页面
					Intent i=new Intent(AddCommentActivity.this,MyCommentActivity.class);
					startActivity(i);
				}
			}
		});
    	back_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
    }
}
