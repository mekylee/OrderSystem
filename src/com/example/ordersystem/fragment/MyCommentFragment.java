package com.example.ordersystem.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ordersystem.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyCommentFragment extends Fragment {
	private ListView mycomment_lsitview;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	View view=inflater.inflate(R.layout.comment_listview_item, container,false);
    	mycomment_lsitview=(ListView)view.findViewById(R.id.comment_listview);
    	mycomment_lsitview.setAdapter(new SimpleAdapter(getContext(), getData(), R.layout.mycomment_lsitview_item,
    			new String[]{"content","comment_time"}, new int []{R.id.mycomment_content,R.id.mycomment_time}));
    	return view;
    }
    
    public List<Map<String,Object>> getData(){
    	List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
    	for(int i=0;i<10;i++){
    	   Map<String,Object> map=new HashMap<String, Object>();
    	   map.put("content", "味道不错，下次再来。味道不错，下次再来。味道不错，下次再来。味道不错，下次再来");

    	   map.put("comment_time","2016-05-07 11:00");
    	   data.add(map);
    	}
    	return data;
    }
}
