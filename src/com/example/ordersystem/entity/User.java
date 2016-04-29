package com.example.ordersystem.entity;

import com.avos.avoscloud.AVUser;

public class User extends AVUser {
    private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
}
