package com.example.ordersystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity{
    private Button login_btn,cancle_btn;
    private TextView account_text,password_text;
    private EditText account_editext,password_editext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		inial();
	}
	
	private void inial(){
		login_btn=(Button)findViewById(R.id.login_btn);
		cancle_btn=(Button)findViewById(R.id.cancel_btn);
		account_editext=(EditText)findViewById(R.id.editAccount);
		password_editext=(EditText)findViewById(R.id.editPassword);
		account_text=(TextView)findViewById(R.id.account);
		password_text=(TextView)findViewById(R.id.password);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
