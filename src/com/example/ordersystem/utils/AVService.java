package com.example.ordersystem.utils;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVDeleteOption;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.GetDataCallback;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.search.AVSearchQuery;
import com.example.ordersystem.constants.LeanCloudConf;
import com.example.ordersystem.entity.Comment;
import com.example.ordersystem.entity.Cusine;
import com.example.ordersystem.entity.Menu;
import com.example.ordersystem.entity.Order;
import com.example.ordersystem.entity.Restaurant;
import com.example.ordersystem.entity.User;

public class AVService {
	public static void AVInit(Context context){
		//注册子类
		 AVObject.registerSubclass(Menu.class);
    	 AVObject.registerSubclass(Cusine.class);
    	 AVObject.registerSubclass(Order.class);
    	 AVObject.registerSubclass(Comment.class);
    	 AVObject.registerSubclass(Restaurant.class);
    	 AVOSCloud.setDebugLogEnabled(true);
    	// 初始化应用Id和应用 Key
     	AVOSCloud.initialize(context,LeanCloudConf.APP_ID, LeanCloudConf.APP_Key);
     	 // 启用崩溃错误报告
        AVAnalytics.enableCrashReport(context, true);
        AVOSCloud.setLastModifyEnabled(true);
	}
     
	/*
	 *  对数据库中的菜单的增删查
	 */
	
	//通过菜单objectId获取菜单
	public static void fetchMenuById(String objectId,GetCallback<AVObject> getCallback){
		AVObject menu=new AVObject("Menu");
		menu.setObjectId(objectId);
		//通过Fetch获取菜单
		menu.fetchInBackground(getCallback);
	}
	
	//查询菜单列表，返回菜单对象列�?
		public static  List<AVObject> findMenu(){
			//查询当前Menu列表
			AVQuery<AVObject> query=new AVQuery<AVObject>("Menu") ;
			//按照更新时间降序排序
			query.orderByDescending("createdAt");
			//最多返回1000条数据
			query.limit(1000);
			try{
				return query.find();
			}
			catch(AVException exception){
				log.e("tag", "查询菜单失败.",exception);
				return Collections.emptyList();
			}
			
		}
	
	/*
	 * 对数据库中用户的增删查改
	 */

	//根据ObjectId来获取用�?
	public static void fetchUserById(String objectId,GetCallback<AVObject> getCallback){
		User user=new User();
		user.setObjectId(objectId);
		//通过fetch获取用户的邮箱地�?��用户类型
		user.fetchInBackground(getCallback);
	}
	
	//查询用户，返回用户对象列�?
		public static List<User> findUsers(){
			//查询当前Menu列表
			AVQuery<User> query=AVQuery.getQuery(User.class);
			//按照更新时间降序排序
			query.orderByDescending("updatedAt");
			//�?��返回1000�?
			query.limit(1000);
			try{
				return query.find();
					}
			catch(AVException exception){
				log.e("tag", "查询用户失败.",exception);
				return Collections.emptyList();
					}
			
		}
		
	
	
	
	/*
	 * 对数据库中的餐厅进行增删查改
	 */
	
	//查询餐厅信息，返回餐厅对象列�?
	public static List<AVObject> findRestaurants(){
		//查询当前Restaurant列表
				AVQuery<AVObject> query=new AVQuery<AVObject>("Restaurant");
				//按照更新时间降序排序
				query.orderByDescending("createdAt");
				//返回1000�?
				query.limit(1000);
				try{
					return query.find();
						}
				catch(AVException exception){
					log.e("tag", "查询餐厅失败.",exception);
					return Collections.emptyList();
						}
				
	}
	
	/*
	 * 对数据中的订单进行增删查�?
	 */
	//查询订单，返回订单列�?
	public static  List<Order> findOrders(){
		//查询当前的订单列�?
		AVQuery<Order> query=AVQuery.getQuery(Order.class);
		//按照更新时间降序排序
		query.orderByDescending("updatedAt");
		//�?��返回1000�?
		query.limit(1000);
		try{
			return query.find();
				}
		catch(AVException exception){
			log.e("tag", "查询订单失败.",exception);
			return Collections.emptyList();
				}
	}
	
	//创建订单
	public static void createOrder(User user, Integer number_of_person,
	   SaveCallback saveCallback) {
		Order order=new Order();
		order.setNumberOfPerson(number_of_person);
		order.setOrderUser(user);
		// 异步保存
		order.saveInBackground(saveCallback);

	}

	//更新订单
	public static void createOrUpdateOrderInfo(String objectId,User user, Integer number_of_person,int order_type,SaveCallback saveCallback){
		final Order order=new Order();
		if(!TextUtils.isEmpty(objectId)){
			//如果存在objectId,保存会变成更新操�?
			order.setObjectId(objectId);
		}
		order.setOrderUser(user);
		order.setNumberOfPerson(number_of_person);
		order.setOrderType(order_type);
		//异步保存
		order.saveInBackground(saveCallback);
	}
	//根据objectId删除订单
	public static void deleteOrderById(String objectId) {
		final Order order = new Order();
		if (!TextUtils.isEmpty(objectId)) {
			// 如果存在objectId,保存会变成更新操�?
			order.setObjectId(objectId);
			order.deleteInBackground(new DeleteCallback() {

				@Override
				public void done(AVException arg0) {
					// TODO Auto-generated method stub
					if (arg0 == null) {
						Log.d("tag", "成功删除订单" + order.getOrderUser().toJSONObject());
					} else {
						Log.d("tag", "删除订单失败?" + arg0.toString());
					}
				}
			});
		}
	}
	
	
	/*
	 * 对数据库中的菜品进行增删查改
	 */
	
	//查询菜品，返回菜品对象列�?
	public static List<AVObject> findCusines(){
		AVQuery<AVObject> query=new AVQuery<AVObject>("Cusine");
		//按照更新时间降序排序
		query.orderByDescending("createdAt");
		//�?��返回1000�?
		query.limit(1000);
		try{
			return query.find();
				}
		catch(AVException exception){
			log.e("tag", "查询菜品失败.",exception);
			return Collections.emptyList();
				}
	}

	
	
	
		 //上传图片到LeanCloud
    public static AVFile upLoadImage(String image_name,String local_path){
		try {
			final AVFile image = AVFile.withAbsoluteLocalPath(image_name,
					Environment.getExternalStorageDirectory() + local_path);
			image.saveInBackground(new SaveCallback() {

				@Override
				public void done(AVException arg0) {
					// TODO Auto-generated method stub
					if (arg0 == null) {
						// 上传成功，提�?
						Log.i("tag", "图片上传成功�?"+image.getUrl());
						
					} else {
						Log.e("error", arg0.toString());
					}
				}
			}, new ProgressCallback() { // 进度

						@Override
						public void done(Integer arg0) {
							// TODO Auto-generated method stub
							Log.i("tag", "图片上传进度�?" + arg0);
						}
					});
			return image;
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.i("tag",e.toString());
			e.printStackTrace();
		}
		
		return null;
	
		
	}
    
    //下载图片
    public static void downloadImage(AVFile image){
    	image.getDataInBackground(new GetDataCallback() {
			
			@Override
			public void done(byte[] arg0, AVException arg1) {
				// TODO Auto-generated method stub
				//bytes就是文件的数据流
			}
		}, new ProgressCallback() {
			
			@Override
			public void done(Integer arg0) {
				// TODO Auto-generated method stub
				//下载进度
				Log.i("tag", "下载进度为："+arg0);
			}
		});
    }
		
	/*
	 * 对数据库洪的评论进行增删查改
	 */
		//查询评论，返回评论对象列�?
		public static List<Comment> findComments(){
			AVQuery<Comment> query=AVQuery.getQuery(Comment.class);
			//按照更新时间降序排序
			query.orderByDescending("updatedAt");
			//�?��返回1000�?
			query.limit(1000);
			try{
				return query.find();
					}
			catch(AVException exception){
				log.e("tag", "查询评论失败.",exception);
				return Collections.emptyList();
					}
		}
		//创建或更新评�?
			public static void createOrUpdateComment(String objectId,User user,String content,SaveCallback saveCallback){
				final Comment comment=new Comment();
				if(!TextUtils.isEmpty(objectId)){
					//如果存在objectId,保存会变成更新操�?
					comment.setObjectId(objectId);
				}
				comment.setAuthor(user);
				comment.setContent(content);
				//异步保存
				comment.saveInBackground(saveCallback);
			}
		
		//根据objectId删除评论
			public static void deleteCommentById(String objectId) {
				final Comment comment = new Comment();
				if (!TextUtils.isEmpty(objectId)) {
					// 如果存在objectId,保存会变成更新操�?
					comment.setObjectId(objectId);
					comment.deleteInBackground(new DeleteCallback() {

						@Override
						public void done(AVException arg0) {
							// TODO Auto-generated method stub
							if (arg0 == null) {
								Log.d("tag", "成功删除评论" + comment.getContent());
							} else {
								Log.d("tag", "删除评论失败�?" + arg0.toString());
							}
						}
					});
				}
			}	
	
	//在整个应用搜�?
	public static void searchQuery(String inputSearch){
		AVSearchQuery searchQuery = new AVSearchQuery(inputSearch);
	    searchQuery.search();
	}
	

	
	
}
