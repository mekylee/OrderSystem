package com.example.ordersystem.activity;

import com.example.ordersystem.R;
import com.example.ordersystem.R.id;
import com.example.ordersystem.R.layout;
import com.example.ordersystem.R.menu;
import com.example.ordersystem.broadcast.NetworkReceiver;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SearchActivity extends Activity {
	private NetworkReceiver networkReceiver;
	
   @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_search);
}
   @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
	    MenuInflater inflater=getMenuInflater();
	    inflater.inflate(R.menu.search, menu);
	    MenuItem searchItem =menu.findItem(R.id.action_serach);
	    SearchView searchVew=(SearchView)searchItem.getActionView();
	    //Todo:≈‰÷√SearchViewµƒµƒ Ù–‘
	   return super.onCreateOptionsMenu(menu);
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
