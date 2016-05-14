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
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class FragmentComment extends Fragment {
	private ListView comment_lsitview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	View view =inflater.inflate(R.layout.fragment_comment, container,false);
    	init(view);
    	return view;
    }
    
    public void init(View  view){
    	comment_lsitview=(ListView)view.findViewById(R.id.comment_listview);
    	comment_lsitview.setAdapter(new SimpleAdapter(getContext(), getData(), R.layout.comment_listview_item,
    			new String[]{"comment_img","account","time","content"}, new int[]{R.id.comment_img,R.id.account_tv,R.id.time_tv,R.id.content_tv}));
    	
    }
    
    public List<Map<String,Object>> getData(){
    	List<Map<String,Object>>  data=new ArrayList<Map<String,Object>>();
    	for(int i=0;i<10;i++){
    		Map<String,Object> map=new HashMap<String, Object>();
    		map.put("comment_img",R.drawable.profile_img);
    		map.put("account", "323323@123.com");
    		map.put("time","2016-05-07 11:00");
    		map.put("content","味道很好哦，下次再来");
    		data.add(map);
    	}
    	return data;
    }
}
