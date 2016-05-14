package com.example.ordersystem.utils;

import com.avos.avoscloud.AVACL;
import com.avos.avoscloud.AVRole;
import com.avos.avoscloud.AVUser;

public class Authority {
    public void createACL(){
    	// 新建一个针对角色本身的 ACL
    	AVACL roleACL=new AVACL();
    	roleACL.setPublicReadAccess(true);
    	roleACL.setWriteAccess(AVUser.getCurrentUser(),true);
    	 // 新建一个角色，并把为当前用户赋予该角色
        AVRole admin=new AVRole("Admin",roleACL); //新建角色
        admin.getUsers().add(AVUser.getCurrentUser());//为当前用户赋予该角色
        admin.saveInBackground();//保存到云端
    }
    
    public void createCustomer(){
    	AVACL customerRoleACL=new AVACL();
    	customerRoleACL.setPublicReadAccess(true);
    	customerRoleACL.setWriteAccess
    	
    	
    	(AVUser.getCurrentUser(),false);
    }
    
    public void createWaiter(){
    	//新建一个针对服务员角色本身的ACL
    	AVACL waiterRoleACL=new AVACL();
    	waiterRoleACL.setPublicReadAccess(true);
    	waiterRoleACL.setWriteAccess(AVUser.getCurrentUser(),true);
    }
}
