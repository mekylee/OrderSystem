package com.example.ordersystem.entity;

import com.avos.avoscloud.AVObject;

public class Comment extends AVObject {
    private User user;  //评论人
    private String content;//评论内容
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
    
}
