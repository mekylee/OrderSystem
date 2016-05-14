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
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SearchActivity extends Activity implements OnQueryTextListener{
	private NetworkReceiver networkReceiver;
	private SearchView sv;
	private ListView lv;
	private ArrayAdapter<String> adapter;
	private String[] data;
   @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_search);
	sv=(SearchView)findViewById(R.id.search);
	lv=(ListView)findViewById(R.id.lv);
	data=new String[]{"aaa","bbb","cc","dd"};
	//设置自动匹配功能
    adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, data);
	lv.setAdapter(adapter);
	lv.setTextFilterEnabled(true);
	sv.setSubmitButtonEnabled(true);
	//为该SearchView组件设置事件监听器
	sv.setOnQueryTextListener(this);
		
	
	
	
}
   @Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
	  // Toast.makeText(SearchActivity.this, "您的选择是："+query,Toast.LENGTH_SHORT).show();
		return false;
	}
	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		//Toast.makeText(SearchActivity.this, "文本改变"+newText, 1).show();
		if(newText.length()!=0){
			lv.setFilterText(newText);
			
		}else{
			lv.clearTextFilter();
			}
			return false;
			
		
	}
   @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
	    MenuInflater inflater=getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    /*MenuItem searchItem =menu.findItem(R.id.action_serach);
	    SearchView searchVew=(SearchView)searchItem.getActionView();*/
	    //Todo:配置SearchView的的属性
	   return true;
	}
   
   /*
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
   }*/
	
}
