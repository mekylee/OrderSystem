package com.example.ordersystem.fragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ordersystem.R;
import com.example.ordersystem.adapter.ListViewAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class FragmentOrderDishes extends Fragment {
	private View view;
	private ListView menu_lv,cusine_lv;
	private ArrayAdapter<String> menu_adapter;
	private SimpleAdapter  cusine_adapter;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	if(view==null){
    	super.onCreateView(inflater, container, savedInstanceState);
    	view =inflater.inflate(R.layout.fragment_order_dishes,container,false);
    	intial(view,inflater);
    	}else{
    		
    	}
    	return view; 
    }
    
    public void intial(View view,LayoutInflater inflater){
    	menu_lv=(ListView)view.findViewById(R.id.menu_listview);
    	cusine_lv=(ListView)view.findViewById(R.id.cusine_listview);
        menu_adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getMenu());
        menu_lv.setAdapter(menu_adapter);
        cusine_adapter=new SimpleAdapter(getContext(), getCusine(), R.layout.main_cusine_listview_item, 
        		new String[]{"cusine_image","price","cusine_name","add_to_cart"}, 
        		new int[]{R.id.cusine_image,R.id.price_tv,R.id.cusine_name_tv,R.id.add_to_cart_image});
      cusine_lv.setAdapter(cusine_adapter);
      //  cusine_lv.setAdapter(new ListViewAdapter(getCusine(), getContext(), inflater));
    }
    
    public String[] getMenu(){
    	String [] menu=new String[] {"∞«¿‡","≈˚»¯","‘¡≤À","œÊ≤À","¥®≤À","–°≥‘","“˚¡œ"};
    	return menu;
    }
    
    public List<Map<String ,Object>> getCusine(){
    	
    	List<Map<String,Object>> cusine_list=new ArrayList<Map<String,Object>>();
    	  for(int i=0;i<10;i++){
    		  Map<String,Object> map=new HashMap<String, Object>();
    		  map.put("cusine_image",R.drawable.app_icon);
    		  map.put("price","£§30");
    		  map.put("cusine_name","∫Ï…’”„");
    		  map.put("add_to_cart",R.drawable.add_to_cart);
    		  cusine_list.add(map);
    	  }
    	 return cusine_list;
    	
    	
    	
    }
}
