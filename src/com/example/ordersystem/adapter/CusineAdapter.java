package com.example.ordersystem.adapter;

import java.util.List;

import org.w3c.dom.Text;

import com.avos.avoscloud.AVObject;
import com.example.ordersystem.R;
import com.example.ordersystem.entity.Cusine;
import com.example.ordersystem.fragment.FragmentOrderDishes;
import com.example.ordersystem.fragment.ShoppingCartFragment;
import com.example.ordersystem.utils.AsyncBitmapLoader;
import com.example.ordersystem.utils.AsyncBitmapLoader.ImageCallback;
import com.example.ordersystem.utils.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CusineAdapter extends BaseAdapter {
    private Context context;
    private List<AVObject> cusines;
    private ArrayAdapter<String> menuAdapter;
    private int count=0; 
    private ShoppingCartFragment cartFragment;
    public CusineAdapter(Context context,List<AVObject> cusines) {
		// TODO Auto-generated constructor stub
    	this.context=context;
    	this.cusines=cusines;
	}
    public CusineAdapter(Context context,List<Cusine> cusinesList,ArrayAdapter<String > menuAdapter) {
		// TODO Auto-generated constructor stub
    	this.context=context;
    	this.cusines=cusines;
    	this.menuAdapter=menuAdapter;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		VieHolder viewHolder=null;
	
		if(convertView==null){
			viewHolder=new VieHolder();
			
			convertView=LayoutInflater.from(context).inflate(R.layout.main_cusine_listview_item, null);
			viewHolder.cusineName=(TextView)convertView.findViewById(R.id.cusine_name_tv);
			viewHolder.cusinePrice=(TextView)convertView.findViewById(R.id.price_tv);
			viewHolder.addToCartImage=(ImageView)convertView.findViewById(R.id.add_to_cart_image);
			viewHolder.cusineImage=(ImageView)convertView.findViewById(R.id.cusine_image);
			convertView.setTag(viewHolder);
			
		}else{
			viewHolder=(VieHolder) convertView.getTag();
			//初始化绑定数据
			viewHolder.cusineName.setText(cusines.get(position).getString("cusine_name"));
			viewHolder.cusinePrice.setText("￥"+Integer.toString(cusines.get(position).getInt("cusine_price")));
			viewHolder.cusineImage.setTag(cusines.get(position).getAVFile("cusine_image").getUrl());
	//		new ImageLoader().showImageByThread(viewHolder.cusineImage, cusines.get(position).getAVFile("cusine_image").getUrl());
		    String urlString=cusines.get(position).getAVFile("cusine_image").getUrl();
			Bitmap bitmap=new AsyncBitmapLoader().getBitmapFromUrl(viewHolder.cusineImage,urlString, new ImageCallback() {
				
				@Override
				public void imageLoad(ImageView imageView, Bitmap bitmap) {
					// TODO Auto-generated method stub
					imageView.setImageBitmap(bitmap);
				}
			});
			if(bitmap==null){
				viewHolder.cusineImage.setImageResource(R.drawable.app_icon);
			}else{
				viewHolder.cusineImage.setImageBitmap(bitmap);
			}
			viewHolder.addToCartImage.setTag(position);
			viewHolder.addToCartImage.setOnClickListener(new MyListener(position));
//			viewHolder.addToCartImage.setOnClickListener(new View.OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					count++;//记录加入购物车的数量，每点击一次，购物车+1
//	                Log.i("tag", "成功加入购物车"+(count++)+"次");
//	                Toast toast=Toast.makeText(context, "成功加入购物车", 1000);
//	                toast.setGravity(Gravity.CENTER,0,200);
//	                toast.show();
//					//将该item的数据传递给ShoppingCartFragment
//					Bundle bundle=new Bundle();
//					bundle.putInt("菜品数量", count);
//					bundle.putString("菜品名称", cusines.get(position).getString("cusine_name"));
//					bundle.putString("菜品价格",Integer.toString(cusines.get(position).getInt("cusine_price")));
//				}
//			});
		}
		
		return convertView;
	}
	
	
	private class VieHolder{
		private TextView cusineName,cusinePrice;
		private ImageView cusineImage,addToCartImage,addOneImage;
	}
	
	public class MyListener implements OnClickListener{
        int mposition;
         public MyListener(int position) {
			// TODO Auto-generated constructor stub
        	 mposition=position;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			count++;
		    Log.i("tag", "成功加入购物车"+count+"次");
            Toast toast=Toast.makeText(context, "成功加入购物车", 1000);
            toast.setGravity(Gravity.CENTER,0,200);
            toast.show();
          //将该item的数据传递给ShoppingCartFragment
			Bundle bundle=new Bundle();
			bundle.putInt("菜品数量", count);
			bundle.putString("菜品名称", cusines.get(mposition).getString("cusine_name"));
			bundle.putString("菜品价格",Integer.toString(cusines.get(mposition).getInt("cusine_price")));
			ShoppingCartFragment fragment =ShoppingCartFragment.newInstance(bundle);
			Log.i("tag", "将菜品数据传递给ShoppingCart");
			
		}
		
	}

	
}
