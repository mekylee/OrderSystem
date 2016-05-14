package com.example.ordersystem.fragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogUtil.log;
import com.example.ordersystem.R;
import com.example.ordersystem.R.id;
import com.example.ordersystem.R.layout;
import com.example.ordersystem.activity.MyOrderActivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingCartFragment extends Fragment {
	private View view;
	private Button commit_btn;
//	private CheckBox edit_btn;
	private TextView total_price_tv;
	private ListView cart_listview;
	private FragmentManager manager;
	private FragmentTransaction tran;
   @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	//layout�ļ�ת����View����
	   /*resource:Fragment��Ҫ���ص��Լ��Ĳ����ļ�
	    * root:����layout�ĸ�ViewGroup
	    * attachToRoot:false�������ظ�ViewGroup
	    */
	   if(view==null){
	      view=inflater.inflate(R.layout.fragment_shoppingcart,null);
	      intialView(view);
	   }
	   ViewGroup parent = (ViewGroup) view.getParent();
		if(parent != null){
			parent.removeView(view);
		}
		return view;
	}
      
   
   public void intialView(View view){
//	   edit_btn=(CheckBox)view.findViewById(R.id.checkBox);
	   commit_btn=(Button)view.findViewById(R.id.commit_btn);
	   total_price_tv=(TextView)view.findViewById(R.id.total_price_tv);
	   manager=getActivity().getSupportFragmentManager();
	   tran=manager.beginTransaction();
	  /* edit_btn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			// TODO Auto-generated method stub
//			String backgoundColor="#1E90FF";
//		ackgound	int color=b
//			commit_btn.setBackgroundColor(#1E90FF);
		}
	});*/
	   commit_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			/**
			 * �ж��û��ĵ�¼״̬������û��Ѿ���¼������ת����������ҳ�棬
			 * ����û�δ��¼������ת����ʾ�û���¼��ҳ��
			 */
	            // ��ת������ҳ��
	        	Intent intent=new Intent(getActivity(),MyOrderActivity.class);
				startActivity(intent);
				Toast.makeText(getActivity(), "�����ύ�ɹ��������ĵȴ�ȷ��", Toast.LENGTH_SHORT).show();
	       }
	   });
	   
	   cart_listview=(ListView)view.findViewById(R.id.cart_listview);
	   cart_listview.setAdapter(new SimpleAdapter(getContext(), getData(), R.layout.cart_listview_item, 
                                  new String[]{"dish_img","dish_price","numer_of_dishes","add_btn","reduce_btn","detele_img"} ,
                                  new int []{R.id.dish_img,R.id.price_tv,R.id.number_of_dished_tv,R.id.add_btn,R.id.reduce_btn,R.id.delete_img}));
        }
   
   public List<Map<String,Object>> getData(){
	   List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
	   for(int i=0;i<10;i++){
		   Map<String,Object> map=new HashMap<String, Object>();
		  
		   map.put("dish_img",R.drawable.app_icon);
		   map.put("dish_price", "��30");
		   map.put("dish_name", "�Ǵ��Ź�");
		   map.put("numer_of_dishes","3");
		   map.put("add_btn", "+");
		   map.put("reduce_btn","-");
		   map.put("detele_img", R.drawable.delete);
		   data.add(map);
	   }
	   return data;
   }


}
