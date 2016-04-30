package com.example.ordersystem.entity;

import com.avos.avoscloud.AVObject;

public class Cusiune extends AVObject {
   private Menu menu;  //菜品所属类型
   private String cid;  //菜品编号
   private String cName; //菜品名字
   private String price; //菜品单价
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
   
}
