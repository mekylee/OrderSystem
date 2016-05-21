package com.example.ordersystem.entity;

import java.util.List;

import android.R.menu;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject; 
   /*
    * ��Menu���໯
    */
   @AVClassName(Menu.MENU_CLASS)
	public class Menu extends AVObject {
	static final String MENU_CLASS="Menu";
	private List<Cusine> cusineLsit;
	//��ȡ�˵����?
	public String getMenuName() {
		return getString("menu_name");
	}
    //���ò˵����?
	public void setMenuName(String menuName) {
		this.put("menu_name", menuName);
	}
	/*//获得旗下的菜品
	public List<Cusine> getCusine(){
		return getList("cusine");
	}
	//设置旗下的菜品
	public void setCusine(List<Cusine> cusine){
		this.put("cusine", cusine);
	}
	*/
	   
}
