package com.example.ordersystem.entity;

import com.avos.avoscloud.AVObject;

public class Comment extends AVObject {
    private User user;  //������
    private String content;//��������
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
    
}
