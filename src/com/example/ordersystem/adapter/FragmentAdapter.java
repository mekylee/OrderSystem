package com.example.ordersystem.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
    List<Fragment>  fragment_list=new ArrayList<Fragment>();
	public FragmentAdapter(FragmentManager fm,List<Fragment> fragment_list) {
		super(fm);
		this.fragment_list=fragment_list;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return fragment_list.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragment_list.size();
	}

}
