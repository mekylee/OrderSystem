package com.example.ordersystem.utils;

import com.avos.avoscloud.AVACL;
import com.avos.avoscloud.AVRole;
import com.avos.avoscloud.AVUser;

public class Authority {
    public void createACL(){
    	// �½�һ����Խ�ɫ����� ACL
    	AVACL roleACL=new AVACL();
    	roleACL.setPublicReadAccess(true);
    	roleACL.setWriteAccess(AVUser.getCurrentUser(),true);
    	 // �½�һ����ɫ������Ϊ��ǰ�û�����ý�ɫ
        AVRole admin=new AVRole("Admin",roleACL); //�½���ɫ
        admin.getUsers().add(AVUser.getCurrentUser());//Ϊ��ǰ�û�����ý�ɫ
        admin.saveInBackground();//���浽�ƶ�
    }
    
    public void createCustomer(){
    	AVACL customerRoleACL=new AVACL();
    	customerRoleACL.setPublicReadAccess(true);
    	customerRoleACL.setWriteAccess
    	
    	
    	(AVUser.getCurrentUser(),false);
    }
    
    public void createWaiter(){
    	//�½�һ����Է���Ա��ɫ�����ACL
    	AVACL waiterRoleACL=new AVACL();
    	waiterRoleACL.setPublicReadAccess(true);
    	waiterRoleACL.setWriteAccess(AVUser.getCurrentUser(),true);
    }
}
