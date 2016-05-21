package com.example.ordersystem.adapter;

import java.util.List;

import com.example.ordersystem.R;
import com.example.ordersystem.entity.Cusine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ShoppingCartAdapter extends BaseAdapter {
    private Context context;
    private List<Cusine> cusines;
    private View.OnClickListener onAddNum,onSubNum,deleteImg; //���ýӿ�
    //���ýӿڷ���
    public void setonAddNum(View.OnClickListener onAddNum){
    	this.onAddNum=onAddNum;
    }
    public void setonSubNum(View.OnClickListener onSubNum){
    	this.onSubNum=onSubNum;
    }
    
    public void setDeletImg(View.OnClickListener deleteImg){
    	this.deleteImg=deleteImg;
    }
    public ShoppingCartAdapter(Context context,List<Cusine> cusines) {
		// TODO Auto-generated constructor stub
    	this.context=context;
    	this.cusines=cusines;
	}
	@Override 
	public int getCount() {
		// TODO Auto-generated method stub
		return cusines.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return cusines.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=LayoutInflater.from(context).inflate(R.layout.cart_listview_item, null);
			viewHolder.addBtn=(Button)convertView.findViewById(R.id.add_btn);
			viewHolder.reduceBtn=(Button)convertView.findViewById(R.id.reduce_btn);
			viewHolder.dishImage=(ImageView)convertView.findViewById(R.id.dish_img);
			viewHolder.cusineName=(TextView)convertView.findViewById(R.id.dish_name_tv);
			viewHolder.cusinePerPrice=(TextView)convertView.findViewById(R.id.price_tv);
			viewHolder.numberOfCusine=(EditText)convertView.findViewById(R.id.number_et);
			viewHolder.deleteImage=(ImageView)convertView.findViewById(R.id.delete_img);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder)convertView.getTag();
		}
		
		//������
		Cusine cusine=cusines.get(position);
		viewHolder.dishImage.setImageResource(R.drawable.app_icon);
		viewHolder.deleteImage.setImageResource(R.drawable.delete);
		viewHolder.cusineName.setText("������");
		viewHolder.cusinePerPrice.setText("��20");
		viewHolder.numberOfCusine.setText("2");
		
		//����Tag�������ж��û���ǰ���������һ���б���İ�ť
		viewHolder.addBtn.setTag(position);
		viewHolder.deleteImage.setTag(position);
		viewHolder.reduceBtn.setTag(position);
		return convertView;
	}
	
	public static class ViewHolder{
		private ImageView dishImage,deleteImage;
		private Button addBtn,reduceBtn;
		private TextView cusineName,cusinePerPrice;
		private EditText numberOfCusine;
		
	}
	
	
/*	public class MyListener implements OnClickListener{
        int mposition;
        public MyListener(int position) {
			// TODO Auto-generated constructor stub
        	mposition=position;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Cusine cusine=cusines.get(mposition);
			switch(v.getId()){
			case R.id.add_btn:
				
				break;
			case R.id.reduce_btn:
				break;
			case R.id.delete_img:
				cusines.remove(mposition);
				notifyDataSetChanged();
				break;
			
			}
		}
		
	}*/

}
