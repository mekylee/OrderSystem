package com.example.ordersystem.entity;

import com.avos.avoscloud.AVObject;

	public class Menu extends AVObject {
	   private String mId;
	   private String menName;
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getMenName() {
		return menName;
	}
	public void setMenName(String menName) {
		this.menName = menName;
	}
	   
}
