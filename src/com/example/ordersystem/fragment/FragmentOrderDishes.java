package com.example.ordersystem.fragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avos.avoscloud.AVObject;
import com.example.ordersystem.R;
import com.example.ordersystem.activity.MainActivity;
import com.example.ordersystem.adapter.CusineAdapter;
import com.example.ordersystem.adapter.HomeDishesTestAdapter;
import com.example.ordersystem.adapter.MenuAdapter;
import com.example.ordersystem.entity.Cusine;
import com.example.ordersystem.entity.Menu;
import com.example.ordersystem.utils.AVService;
import com.example.ordersystem.utils.DBHelper;

import android.R.menu;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentOrderDishes extends Fragment {
	private View view;
	private ListView menu_lv,cusine_lv;
	private ArrayAdapter<String> menuAdapter;  //���Բ˵�������
	private MenuAdapter menu_adapter;  //�˵�������
	private CusineAdapter  cusine_adapter;      //��Ʒ����
	private HomeDishesTestAdapter dishes_adapter;   //���Բ�Ʒ������
	private int count=0;//��¼��Ʒ����Ĵ���
	private List<Map<String,Object>> data;  //���Բ�Ʒ����
	private static List<Cusine> cusines;   //��Ʒ��Ϣ
	private List<AVObject> menu;  //�˵���Ϣ
	private List<AVObject> cusine; //��ȡ��Ʒ��Ϣ
	private ProgressDialog dialog;
	private ImageView add_to_cart;
	private FragmentManager fragmentManager;
//	private DBHelper helper=new DBHelper(getContext().getApplicationContext());

	
	//�첽��ȡ�˵�����
	  public class RemoteMenuData  extends AsyncTask<Void, Integer, List<AVObject>>{

		@Override
		protected List<AVObject> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			menu= AVService.findMenu();
			return menu;
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
//			dialog=new ProgressDialog(getActivity());
//			dialog.setTitle("��ʾ");
//			dialog.setMessage("���ڼ���");
//			dialog.setProgress(0);
//			dialog.show();
		}
		
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
//			Log.i("tag", "���ݼ��صĽ���Ϊ��"+values[0]);
//			if(values[0]==100){
//				dialog.dismiss();
//			}else{
//				dialog.show();
//			}
		}
		
		@Override
		protected void onPostExecute(List<AVObject> result) {
			// TODO Auto-generated method stub
			Log.i("tag",menu.toString());
			//չ��Listview
			menu_adapter=new MenuAdapter(getContext(), menu);
			menu_lv.setAdapter(menu_adapter);
			super.onPostExecute(result);
		}
	     
	  }
	  
	  //�첽��ȡ��Ʒ��Ϣ
	  public class RemoteCusineData  extends AsyncTask<Void, Void, List<AVObject>>{

			@Override
			protected List<AVObject> doInBackground(Void... params) {
				// TODO Auto-generated method stub
				cusine= AVService.findCusines();
				return cusine;
			}
			
			@Override
			protected void onPostExecute(List<AVObject> result) {
				// TODO Auto-generated method stub
				Log.i("tag",cusine.toString());
				//չ��Listview
				cusine_adapter=new CusineAdapter(getContext(), cusine);
				cusine_lv.setAdapter(cusine_adapter);
				super.onPostExecute(result);
			}
		     
		  }
	  @Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		new RemoteMenuData().execute();
		new RemoteCusineData().execute();
	}
	
	  @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		fragmentManager=getChildFragmentManager();
//		fragmentManager=getFragmentManager();
	}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	if(view==null){
	    	super.onCreateView(inflater, container, savedInstanceState);
	    	view =inflater.inflate(R.layout.fragment_order_dishes,container,false);
	    	intial(view,inflater);
    	}
    	
    
    
    	return view; 
    }
    
    public void intial(View view,LayoutInflater inflater){
    	menu_lv=(ListView)view.findViewById(R.id.menu_listview);
    	cusine_lv=(ListView)view.findViewById(R.id.cusine_listview);
    	add_to_cart=(ImageView)view.findViewById(R.id.add_to_cart_image);
    /*	menu_adapter=new MenuAdapter(getContext(), menu);
    	menu_lv.setAdapter(menu_adapter);*/
    	/*
    	 * ��������
    	 */
//        menuAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getMenuName());
//        menu_lv.setAdapter(menuAdapter);
//        cusine_adapter=new CusineAdapter(getContext(), cusines);
//        cusine_lv.setAdapter(cusine_adapter);
     /*   data=getCusine();
        dishes_adapter=new HomeDishesTestAdapter(data, getContext(), this);
        cusine_lv.setAdapter(dishes_adapter);
       */
      
    }
    
    private void initMenu(){
//    	menuAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getMenu());
//          menu_lv.setAdapter(menuAdapter);
//          
//          cusines=new ArrayList<Cusine>();
//          cusines.amenuAdapterddAll(menus.get(0).getList("cusine"));
//          cusine_adapter=new CusineAdapter(getContext(), cusines,menu_adapter);
          
          
    }
    
  /*  private void initCusine(){
    	cusine_adapter=new CusineAdapter(getContext(), cusines);
    	cusine_lv.setAdapter(cusine_adapter);
    }
    */
/*    //��������listview������
    private  void addListener(){
    	menu_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				cusines.clear();
				cusines.addAll(menus.get(position).getCusine());
				cusine_adapter.notifyDataSetChanged();
				
			}
		});
    }
    */
    /*//��sqlite���ݿ��л�ȡ�˵�����
    public List<String> getMenuName(){
    	  List<String> menuString=new ArrayList<String>();
    	  //��ѯ���ݿ�
    	  Cursor c=helper.query("menus");
    	  if(c.moveToFirst()){
    		  //�����α�
    		  for(int i=0;i<c.getCount();i++){
    			  //��ȡ�˵���
    			  String menuName=c.getString(2);
    		      menuString.add(menuName);
    		      Log.i("tag", "��ȡ���Ĳ˵���Ϊ"+menuName);
    		  }
    	  }
    	  return menuString;
    }*/
    //�˵���������
    public String[] getMenu(){
    	String [] menu=new String[] {"����","����","����","���","����","С��","����"};
    	return menu;
    }
//    public List<String> getMenus(List<Menu> menus){
//    	//��ȡ���ݿ�
//        
//    	//return menuName;
//    }
    //��Ʒ��������
    public List<Map<String ,Object>> getCusine(){
    	
    	List<Map<String,Object>> cusine_list=new ArrayList<Map<String,Object>>();
    	  for(int i=0;i<10;i++){
    		  Map<String,Object> map=new HashMap<String, Object>();
    		  map.put("cusine_image",R.drawable.app_icon);
    		  map.put("price","��30");
    		  map.put("cusine_name","������");
    		  map.put("add_to_cart",R.drawable.add_to_cart);
    		  cusine_list.add(map);
    	  }
    	 return cusine_list;
    }

	
	

	
    
    
}
