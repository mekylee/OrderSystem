package com.example.ordersystem.entity;

import java.util.Date;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVRelation;

@AVClassName(Comment.COMMENT_CLASS)
public class Comment extends AVObject {
	static final String COMMENT_CLASS="Comment";

    public Comment(){
    	
    }
    //获取评论内容
	public String getContent() {
		return getString("content");
	}
	//设置评论内容
	public void setContent(String content) {
		put("content",content);
	}
	
	//获取评论时间
	public Date getCommentDate(){
		return getDate(CREATED_AT);
	}
	
	//获取评论�?
    public User getAuthor(){
    
    	return getAVObject("author");
    }
    //设置评论�?
    public void setAuthor(User user){
    	put("author",user);
    	
    }
    
    
}
