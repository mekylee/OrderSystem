package com.example.ordersystem.adapter;

import java.util.List;

import android.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

public class FragmentTabAdapter implements RadioGroup.OnCheckedChangeListener{
     private List<Fragment> fragments;  //һ��tabҳ�����һ��Fragment
     private RadioGroup rgs;//�����л�tab
     private FragmentActivity fragmentActivity;//Fragment������Activity
     private int fragmentContentId;  //Activity��Ҫ���滻��������Id
     private int currentTab;//��ǰTabҳ������
     
     public FragmentTabAdapter(FragmentActivity fragmentActivity,List<Fragment> fragments,int fragmentContentId,RadioGroup rgs){
    	 this.fragments=fragments;
    	 this.rgs=rgs;
    	 this.fragmentActivity=fragmentActivity;
    	 this.fragmentContentId=fragmentContentId;
    	 
         //Ĭ����ʾ��һҳ
         FragmentTransaction ft=fragmentActivity.getSupportFragmentManager().beginTransaction();
         ft.add(fragmentContentId, fragments.get(0));
         ft.commit();
         rgs.setOnCheckedChangeListener(this);
         
     }

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		for(int i=0;i<rgs.getChildCount();i++){
			if(rgs.getChildAt(i).getId()==checkedId){
			Fragment fragment=fragments.get(i);
			FragmentTransaction ft=fragmentActivity.getSupportFragmentManager().beginTransaction();
			getCurrentFragment().onPause();  //��ͣ��ǰ��Tab
			if(fragment.isAdded()){
			   fragment.onResume(); //����Ŀ��Tab
			}else{
				ft.add(fragmentContentId, fragment);
			}
			showTab(i); //��ʾĿ��tab
			ft.commit();
		}
		}
	}
	public int getCurrentTab(){
		return currentTab;
	}
	
	public Fragment getCurrentFragment(){
		return fragments.get(currentTab);
	}
	
	public void showTab(int index){
		for(int i=0;i<fragments.size();i++){
			Fragment fragment=fragments.get(i);
			FragmentTransaction ft=fragmentActivity.getSupportFragmentManager().beginTransaction();
            if(index==i){
            	ft.show(fragment);
            }else{
            	ft.hide(fragment);
            }
            ft.commit();
		}
		currentTab=index;  //����Ŀ��tabΪ��ǰtab
	}
   
     
}
