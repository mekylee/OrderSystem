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


import com.example.ordersystem.adapter.ShoppingCartAdapter;
import com.example.ordersystem.entity.Cusine;
import com.example.ordersystem.entity.Menu;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingCartFragment extends Fragment implements OnClickListener{
	private View view;
	private Button commit_btn;
//	private CheckBox edit_btn;
	private TextView total_price_tv;
	private ListView cart_listview;
	private FragmentManager manager;
	private FragmentTransaction tran;
	private ShoppingCartAdapter cartAdapter;  //用来显示菜品信息的适配器
	private List<Cusine> cusines;  
	//新建一个ShoppingCartFragment的实例，提供给FragmentOrderDishes传递参数
	public static ShoppingCartFragment newInstance(Bundle bundle){
		ShoppingCartFragment fragment=new ShoppingCartFragment();
         fragment.setArguments(bundle);
		return fragment;
	}
	
	
	//创建观察者
	private DataSetObserver sumObserver =new DataSetObserver() {
		//当Adapter的notifyDataSetChanged方法 执行时调用
		public void onChanged() {
			super.onChanged();
			//执行相关操作
			int sum=0;
			//获取
			for(int i=0;i<cusines.size();i++){
				sum+=cusines.get(i).getPrice();
			}
			total_price_tv.setText("总价 ：￥"+sum);
			
		};
		//当Adapter调用notifyDataSetInvalidate方法执行时调用
		public void onInvalidated() {
			super.onInvalidated();
		};	
	};

   @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
	   commit_btn.setOnClickListener(this);
	   cart_listview=(ListView)view.findViewById(R.id.cart_listview);
	  
	/*   cartAdapter=new ShoppingCartAdapter(getContext(),cusines);
	   //注册观察者
	   cartAdapter.registerDataSetObserver(sumObserver);
	   cart_listview.setAdapter(cartAdapter);*/
	  //测试数据
	   cart_listview.setAdapter(new SimpleAdapter(getContext(), getData(), R.layout.cart_listview_item, 
                                  new String[]{"dish_img","dish_price","numer_of_dishes","add_btn","reduce_btn","detele_img"} ,
                                  new int []{R.id.dish_img,R.id.price_tv,R.id.number_of_dished_tv,R.id.add_btn,R.id.reduce_btn,R.id.delete_img}));
       
   
      //执行增加减少菜品数量以及删除操作，增加减少菜品数量的按钮点击事件的接口回调
	  /* cartAdapter.setonAddNum(this);
	   cartAdapter.setonSubNum(this);
	   cartAdapter.setDeletImg(this);
	  */
      }
 
   @Override
   public void onClick(View v) {
   	// TODO Auto-generated method stub
	   Object tag=v.getTag();
	   int position;
   	   switch(v.getId()){
   	     case R.id.add_btn:
   	    	 //获取Adapter中设置的Tag
   	    	 if(tag!=null && tag instanceof Integer){
   	    		 position=(Integer) tag; 
   	    		 //更改集合的数据
   	    		 int num=cusines.get(position).getNum();
   	    		 num++;
   	    		 cusines.get(position).setNum(num);
   	    		 cusines.get(position).setPrice(position*num);
   	    		 cartAdapter.notifyDataSetChanged();
   	    	 }
   	    	 break;
   	     case R.id.reduce_btn:
   	    	 if(tag!=null && tag instanceof Integer){
   	    		 position=(Integer) tag; 
   	    		 //更改集合的数据
   	    		 int num=cusines.get(position).getNum();
   	    		 if(num>0){
   	    			 num--;
   	   	    		 cusines.get(position).setNum(num);
   	   	    		 cusines.get(position).setPrice(position*num);
   	   	    		 cartAdapter.notifyDataSetChanged();
   	    		 }else{
   	    			 Toast.makeText(getContext(), "数量不能为负数", 1000).show();
   	    		 }
   	    		
   	    	 }
   	    	 break;
   	     case R.id.delete_img:
   	    	 if(tag!=null && tag instanceof Integer){
   	    		 position=(Integer) tag; 
   	    		 cusines.remove(position);
   	    		 cartAdapter.notifyDataSetChanged();
   	    	 }
   	    	 break;
   	     case R.id.commit_btn:
			// 点击提交按钮，页面跳转到我的订单页面
			Intent intent = new Intent(getActivity(), MyOrderActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
   	    	 break;
   		   
   	   }
   }
    
	/*   @Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//注销观察者
		cartAdapter.unregisterDataSetObserver(sumObserver);
	}*/
   //获取首页传递过来的菜品的信息
   public List<Cusine> getCusine(){
	   List<Cusine> cusines=new ArrayList<Cusine>();
	   Bundle bundle=getArguments(); //获取从首页传递过来的封装了菜品数据的Bundle
	 /*  String cusineString=bundle.getString("菜品对象");
	   //反序列化cusineString
	   try {
		Cusine cusine=(Cusine) Cusine.parseAVObject(cusineString);
		int number=cusine.getInt("cusine_price");
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	   //从bundle中获取菜品的数量
	   int number=bundle.getInt("菜品数量");
	   //从bundle中获取菜品名称
	   String name=bundle.getString("菜品名称");
	   //从bundle中获取菜品的价格
	   int  price=bundle.getInt("菜品价格");
	   //从bundle中获取菜品的图片
	   Bitmap bitmap=bundle.getParcelable("菜品图片");
	  return cusines;
   }
   
   
   public List<Map<String,Object>> getCusineInfo(){
	   List<Map<String,Object>> cusine=new ArrayList<Map<String,Object>>();
	   Bundle bundle=getArguments(); //获取从首页传递过来的封装了菜品数据的Bundle
	 //从bundle中获取菜品的数量
	   int number=bundle.getInt("菜品数量");
	   //从bundle中获取菜品名称
	   String name=bundle.getString("菜品名称");
	   //从bundle中获取菜品的价格
	   int  price=bundle.getInt("菜品价格");
	   //从bundle中获取菜品的图片bitmap
	   Bitmap bitmap=bundle.getParcelable("菜品图片");
	   for(int i=0;i<10;i++){
		   Map<String,Object> map=new HashMap<String, Object>();
		   map.put("dish_img",bitmap);
		   map.put("dish_price", price);
		   map.put("dish_name", name);
		   map.put("numer_of_dishes",number);
		   map.put("add_btn", "+");
		   map.put("reduce_btn","-");
		   map.put("detele_img", R.drawable.delete);
		   cusine.add(map);
	   }
	   
	   return cusine;
   }
   /*
    * 测试数据
    */
   public List<Map<String,Object>> getData(){
	   List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
	   for(int i=0;i<10;i++){
		   Map<String,Object> map=new HashMap<String, Object>();
		  
		   map.put("dish_img",R.drawable.app_icon);
		   map.put("dish_price", "￥30");
		   map.put("dish_name", "孜然羊肉");
		   map.put("numer_of_dishes","3");
		   map.put("add_btn", "+");
		   map.put("reduce_btn","-");
		   map.put("detele_img", R.drawable.delete);
		   data.add(map);
	   }
	   return data;
   }




}
