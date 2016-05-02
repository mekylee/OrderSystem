package com.example.ordersystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SearchActivity extends Activity {
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
}
