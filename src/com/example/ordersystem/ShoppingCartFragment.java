package com.example.ordersystem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ShoppingCartFragment extends Fragment{
  @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	//layout�ļ�ת����View����
	   /*resource:Fragment��Ҫ���ص��Լ��Ĳ����ļ�
	    * root:����layout�ĸ�ViewGroup
	    * attachToRoot:false�������ظ�ViewGroup
	    */
	   View view=inflater.inflate(R.layout.fragment_shoppingcart, container, true);
	   TextView textview =(TextView)view.findViewById(R.id.textView1);
	   textview.setText("���ﳵ");
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
