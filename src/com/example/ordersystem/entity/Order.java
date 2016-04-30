package com.example.ordersystem.entity;

import com.avos.avoscloud.AVObject;

public class Order extends AVObject {
   private String oId;
   private User user;
   private Menu menu;
   private int numberOfPerson;
   private int total_price;
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public int getNumberOfPerson() {
		return numberOfPerson;
	}
	public void setNumberOfPerson(int numberOfPerson) {
		this.numberOfPerson = numberOfPerson;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
   
   
   
}
