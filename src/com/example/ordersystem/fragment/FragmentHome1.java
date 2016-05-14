package com.example.ordersystem.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avos.avoscloud.LogUtil.log;
import com.example.ordersystem.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class FragmentHome1 extends Fragment {
	private ListView home_listview;
	private List<Map<String,Object>> data;
    private TextView cusine_name,price;
    private ImageView cusine_img,add_to_cart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	View view=inflater.inflate(R.layout.fragment_home1, container,false);
    	init(view);
    	Log.i("tag", "完成视图");
    	return view;
    	
    }
    
    public void init(View view){
    	Log.i("tag","加载视图");
    	home_listview=(ListView)view.findViewById(R.id.home_listview);
    	Log.i("tag","找到listview"+home_listview.getId());
        home_listview.setAdapter(new SimpleAdapter(getContext(), getData(), R.layout.home_list__view_item, 
        		new String[]{"cusine_img","price","cusine_name","add_img"},new int[]{R.id.cusine_img,R.id.price_textview,R.id.cusine_listview ,R.id.add_img}));
        
       Log.i("tag","加载适配器");
    }
    
    public List<Map<String,Object>> getData(){
    	List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
    	for(int i=0;i<10;i++){
    		Map<String,Object> map=new HashMap<String,Object>();
    		map.put("cusine_img", R.drawable.app_icon);
    		
    		map.put("price", "$20");
    	    map.put("cusine_name", "酸菜鱼");
    	    map.put("add_img",R.drawable.add_to_cart);
    	    data.add(map);
    	}
    	Log.i("tag", "数据加载完成");
    	return data;
    }
}
