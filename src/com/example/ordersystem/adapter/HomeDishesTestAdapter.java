package com.example.ordersystem.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;











import com.avos.avoscloud.LogUtil.log;
import com.example.ordersystem.R;
import com.example.ordersystem.entity.Cusine;
import com.example.ordersystem.fragment.FragmentOrderDishes;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeDishesTestAdapter extends BaseAdapter implements OnClickListener{
    private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
    private List<Cusine> cusines; 
    private Context context;
    private LayoutInflater layoutInflater;
    private int count =0;//��¼������빺�ﳵ��ť�Ĵ���
    private ClickCallback callback;
    public interface ClickCallback{
    	public void click(View v);
    }
    public final class ListItemView{
    	public ImageView image;
    	public TextView price;
    	public TextView cusine_name;
    	public ImageView add_to_cart;
    }
	public HomeDishesTestAdapter(List<Map<String, Object>> data, Context context,ClickCallback callback) {
		super();
		this.data = data;
		this.context = context;
	    this.layoutInflater=LayoutInflater.from(context);  
        this.callback=callback;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.i("method","getView");
		ListItemView  list_item_view=null;
		if(convertView==null){
			list_item_view=new ListItemView();
			convertView=layoutInflater.inflate(R.layout.main_cusine_listview_item, null);
			list_item_view.image=(ImageView)convertView.findViewById(R.id.cusine_image);
			list_item_view.add_to_cart=(ImageView)convertView.findViewById(R.id.add_to_cart_image);
			list_item_view.cusine_name=(TextView)convertView.findViewById(R.id.cusine_name_tv);
			list_item_view.price=(TextView)convertView.findViewById(R.id.price_tv);
			//���ÿؼ���convertView
			convertView.setTag(list_item_view);
		}else{
			list_item_view=(ListItemView)convertView.getTag();
		}
		//Cusine cusine=cusines.get(position);
		Log.i("tag", (String) data.get(position).get("cusine_name"));
		Log.i("tag", (String) data.get(position).get("price"));
		//������
/*		list_item_view.image.setImageResource(R.drawable.app_icon);//����Ĭ�ϵĲ�Ʒͼʾ
		list_item_view.add_to_cart.setImageResource(R.drawable.add_to_cart);
		list_item_view.cusine_name.setText(cusine.getCusineName());
		list_item_view.price.setText(Integer.toString(cusine.getInt("price")));
		list_item_view.add_to_cart.setOnClickListener(this);*/
	//	list_item_view.image.setBackgroundResource((Integer)data.get(position).get("cusine_image"));
		list_item_view.add_to_cart.setBackgroundResource((Integer)data.get(position).get("add_to_cart"));
		list_item_view.cusine_name.setText(data.get(position).get("cusine_name").toString());
		list_item_view.price.setText(data.get(position).get("price").toString());
	
		return convertView;
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		callback.click(v);
	}
	
	/*
	public class MyListener implements OnClickListener{
        int mpostion;
        public MyListener(int position) {
			// TODO Auto-generated constructor stub
        	mpostion=position;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.add_to_cart_image:
				 count++;  //ÿ���һ�μ�1
				 Log.i("tag", "�����"+count+"��");
				//����λ�õĲ�Ʒ��Ϣ����ΪShoppingCartFragment
				Log.i("tag", "�ɹ����빺�ﳵ");
				Toast.makeText(context, "�ɹ����빺�ﳵ",1000).show();
				Bundle bundle=new Bundle();//����Ʒ���ݷ�װ��bunlde
				//��ȡ��item�Ĳ˵�����
				Cusine cusine=cusines.get(mpostion);
				String cusineString=cusine.toString();//���л�Cusine����
	            bundle.putString("�˵�����", cusineString);
				//��ȡ��item�Ĳ�Ʒ��Ϣ
				String name=cusine.getCusineName();
				bundle.putString("�˵�����", name);
				int price=cusine.getInt("cusine_price");
				bundle.putInt("�˵��۸�", price);
				//���ݵ���˹�������ť�Ĵ���
				bundle.putInt("��Ʒ����", count);
				FragmentOrderDishes fragmentOrderDishes=new FragmentOrderDishes();
				fragmentOrderDishes.setArguments(bundle);
				break;
			
			}
		}
		
	}*/

}
