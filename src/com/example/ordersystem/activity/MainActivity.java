package com.example.ordersystem.activity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SearchView;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogUtil.log;
import com.example.ordersystem.R;
import com.example.ordersystem.R.id;
import com.example.ordersystem.R.layout;
import com.example.ordersystem.R.menu;
import com.example.ordersystem.adapter.FragmentTabAdapter;
import com.example.ordersystem.broadcast.NetworkReceiver;
import com.example.ordersystem.constants.LeanCloudConf;
import com.example.ordersystem.entity.Menu;
import com.example.ordersystem.fragment.CartFragmentNoLogin;
import com.example.ordersystem.fragment.FragmentHome1;
import com.example.ordersystem.fragment.UserFragmentNoLogin;
import com.example.ordersystem.fragment.HomeFragment;
import com.example.ordersystem.fragment.PersonalInfoFragment;
import com.example.ordersystem.fragment.ShoppingCartFragment;
import com.example.ordersystem.utils.AVService;
import com.example.ordersystem.utils.DBHelper;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener{
  private RadioGroup radiogroup;
  private NetworkReceiver networkReceiver;
  private FragmentManager manager;
  private FragmentTransaction tran;
  private LeanCloudConf conf;
  private AVUser currentUser=AVUser.getCurrentUser();  //��ǰ�û�
  private HomeFragment home;
  private ShoppingCartFragment cartFragment;
  private PersonalInfoFragment mine;
  private CartFragmentNoLogin fragment;
  private UserFragmentNoLogin  userFragmentNoLogin;
  private List<Menu> menus;
  private List<Fragment> fragments=new ArrayList<Fragment>();
  private List<AVObject> menu;
  private List<AVObject> cusine;
  private List<AVObject> restaurant;
  private Map<String,List<AVObject>> data=new HashMap<String,List<AVObject>>();
  private SQLiteDatabase db;
 //�첽��ȡ��������
  public class RemoteData  extends AsyncTask<Void, Void,Map<String,List<AVObject>>>{

	@Override
	protected Map<String,List<AVObject>> doInBackground(Void... params) {
		// TODO Auto-generated method stub
		 menu=AVService.findMenu();
		 cusine=AVService.findCusines();
		 restaurant=AVService.findRestaurants();
	    data.put("�˵�",menu);
	    data.put("��Ʒ", cusine);
	    data.put("������Ϣ", restaurant);
	    
		return data;
	}
	
	@Override
	protected void onPostExecute(Map<String,List<AVObject>> result) {
			// TODO Auto-generated method stub
			data = result;
			menu = data.get("�˵�");
			cusine = data.get("��Ʒ");
			restaurant = data.get("������Ϣ");
			// �������ݿ�
			DBHelper helper = new DBHelper(getApplicationContext());
		
			Cursor c=helper.query("menus");
			ContentValues values = new ContentValues(menu.size());
			for (int i = 0; i < menu.size(); i++) {
				values.put("name", menu.get(i).getString("menu_name"));
				Log.i("���������Ϊ:", values.getAsString("name"));
			}
			// ���˵����������
			helper.insert(values, "menus");
			//��ѯ�˵���
		//	Cursor c=helper.query("menus");
			if(c.moveToFirst()){
				for(int i=0;i<c.getCount();i++){
					c.move(i);
					Log.i("�Ӳ˵����ѯ�õ�������Ϊ", c.getString(c.getColumnIndex("name")));
				}
			}
			c.close();
//			for (int i = 0; i < restaurant.size(); i++) {
//				values.put("rest_name", restaurant.get(i).getString("restName"));
//				values.put("rest_address",
//						restaurant.get(i).getString("restAddress"));
//				values.put("rest_phone",
//						restaurant.get(i).getString("restPhone"));
//				Log.i("Ҫ�������ϢΪ��",
//						values.get("rest_name") + " "
//								+ values.get("rest_address") + " "
//								+ values.get("rest_phone"));
//			}
//            //���������������
//			helper.insert(values, "restaurant");
//			//��ѯ������
//			
//			//��װ��Ʒ����
//			for(int i=0;i<cusine.size();i++){
//				String imageUrlString=cusine.get(i).getAVFile("cusine_image").getUrl();
//				int price= cusine.get(i).getInt("cusine_price");
//				String name=cusine.get(i).getString("cusine_name");
//				String type=cusine.get(i).getAVObject("cusine_type").getString("menus_name");
//				values.put("cusine_image",imageUrlString); //����Ƭ��URL���������ݿ���
//				values.put("cusine_name", name);
//				values.put("cusine_price", price);
//				values.put("cusine_type", type);
//			    Log.i("tag","�����Ʒ�����ϢΪ:"+imageUrlString+" "+name+" "+price+" "+type);
//			}
//			
//			
			helper.close(); //�ر����ݿ�
		super.onPostExecute(result);
	}
    }   
  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		fragments.add(new HomeFragment());
		if(currentUser!=null){
		  fragments.add(new ShoppingCartFragment());
		  fragments.add(new PersonalInfoFragment());
		}else{
			
			fragments.add(new CartFragmentNoLogin());
			fragments.add(new UserFragmentNoLogin());
		}
		 radiogroup=(RadioGroup)findViewById(R.id.radioGroup1);
		 FragmentTabAdapter tabAdapter=new FragmentTabAdapter(this, fragments, R.id.main_content, radiogroup);

//		new RemoteData().execute();
		
	
	}
  
   private void initiaView(){
	   
	   manager=getSupportFragmentManager();
	   tran=manager.beginTransaction();
	   tran.replace(R.id.main_content, new HomeFragment());
//	   tran.replace(R.id.main_content, new FragmentHome1());
	   tran.commit();
	   radiogroup=(RadioGroup)findViewById(R.id.radioGroup1);
	   radiogroup.setOnCheckedChangeListener(this);
	  
   }

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		tran=manager.beginTransaction();
		switch(arg1){
		case R.id.home: //��ҳ
			HomeFragment home=new HomeFragment();	
//			FragmentHome1 home=new FragmentHome1();
			
			tran.replace(R.id.main_content,home);
			Log.i("tag","������ҳ");
			break;
		case R.id.cart:  //���ﳵ
			/**
			 * �жϹ��ﳵ�Ƿ�Ϊ�գ�����û��Ѿ���¼������ת����������ҳ��
			 * ����û�δ��¼������ת����ʾ�û���¼��ҳ��
			 */
			currentUser=AVUser.getCurrentUser();
	        if (currentUser != null) {
			
	           ShoppingCartFragment cartFragment=new  ShoppingCartFragment();
	           tran.replace(R.id.main_content,cartFragment);
	           Log.i("tag","���ڹ��ﳵ");
	        }else{
	        	CartFragmentNoLogin  fragment=new CartFragmentNoLogin();
	        	tran.replace(R.id.main_content, fragment);
	        }
	        break;
		case R.id.profile: //��������
			/**
			 * �ж��û��ĵ�¼״̬������û��Ѿ���¼������ת����������ҳ�棬
			 * ����û�δ��¼������ת����ʾ�û���¼��ҳ��
			 */
			currentUser=AVUser.getCurrentUser();
	        if (currentUser != null) {
	            // ��ת����������ҳ��
	        	PersonalInfoFragment mine=new PersonalInfoFragment();
				tran.replace(R.id.main_content,mine);
				 Log.i("tag","��������");
	        } else {
	            //�����û�����Ϊ��ʱ���ɴ��û�ע����桭
	        	UserFragmentNoLogin  fragment=new UserFragmentNoLogin();
	        	tran.replace(R.id.main_content, userFragmentNoLogin);
	        }
			
			 break;
		}
		tran.commit();
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
