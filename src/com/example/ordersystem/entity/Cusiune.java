package com.example.ordersystem.entity;

import com.avos.avoscloud.AVObject;

public class Cusiune extends AVObject {
   private Menu menu;  //��Ʒ��������
   private String cid;  //��Ʒ���
   private String cName; //��Ʒ����
   private String price; //��Ʒ����
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
