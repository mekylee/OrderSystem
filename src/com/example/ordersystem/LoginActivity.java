package com.example.ordersystem;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVRelation;
import com.avos.avoscloud.AVSaveOption;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.CloudQueryCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.SaveCallback;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity{
    private Button login_btn,cancle_btn;
    private TextView account_text,password_text;
    private EditText account_editext,password_editext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		inial();
		AVOSCloud.initialize(this, "enGm9xuEOUQ7VhAhJy8B0DrR-gzGzoHsz", "ne6tHuAkWvzHbkW62f2Q76Dz");
	    //跟踪统计应用 的打开情况
		AVAnalytics.trackAppOpened(getIntent());
	    AVObject testObject=new AVObject("TestObject");
	    testObject.put("test", "hello world");
	    testObject.saveInBackground();
	    
	}
	
	private void inial(){
		login_btn=(Button)findViewById(R.id.login_btn);
		cancle_btn=(Button)findViewById(R.id.cancel_btn);
		account_editext=(EditText)findViewById(R.id.editAccount);
		password_editext=(EditText)findViewById(R.id.editPassword);
		account_text=(TextView)findViewById(R.id.account);
		password_text=(TextView)findViewById(R.id.password);
	
	}
	
	/**
	 * 手机号一键登录
	 */
	private void phoneNumberLogin(){
		//1.调用发送验证码的接口
		AVOSCloud.requestSMSCodeInBackground("13826473629", new RequestMobileCodeCallback() {
			
			@Override
			public void done(AVException arg0) {
				// TODO Auto-generated method stub
				//发送失败可以查看arg0提供的信息
				Log.e("tag", arg0.toString());
			}
		});
		//2.在UI上给与用户输入验证码的输入框，用户点击登录的时候调用如下接口:
		AVUser.signUpOrLoginByMobilePhoneInBackground("13826473629", "123456",new LogInCallback<AVUser>() {

			@Override
			public void done(AVUser arg0, AVException arg1) {
				// TODO Auto-generated method stub
				//如果arg1为空就表示登录成功了，并且User是个全新的用户
			}
		});
	}
	
	private void createObject(){
		//创建对象
		final AVObject userFolder=new AVObject("UserFolder");
		
		AVObject user1=new AVObject("User");
		user1.put("uid","001号");
		user1.put("account","hellworld");
		user1.put("password","12342");
		
		AVObject user2=new AVObject("User");
		user2.put("uid","002");
		user2.put("account","sfdf");
		user2.put("password","fd223");
		
		AVObject user3=new AVObject("User");
		user3.put("uid","003");
		user3.put("account","13232dd");
		user3.put("password","dji33");
		
		//关联对象
		AVRelation<AVObject> relation =userFolder.getRelation("包含USer");
		relation.add(user1);
		relation.add(user2);
		relation.add(user3);
		//保存到服务端
		userFolder.saveInBackground(new SaveCallback() {   
			
			@Override
			public void done(AVException arg0) {
				// TODO Auto-generated method stub
				if(arg0==null){
					//存储成功,objectId会自动从服务端加载到本地
					Log.e("tag", "新建的user对象编号为"+userFolder
							
							
							.getObjectId());
				}else{
					//失败的话，请检查网络环境以及sdk配置是否正确
					log.e("createObject","存储失败，请检查网络环境以及sdk是否配置正确");
				}
			}
		});
		
		
		
	}
	
	private void insertObject(){
		//执行CQL语句实现新增一个User对象
		AVQuery.doCloudQueryInBackground("insert into User(uid,account,password)values('0002',13826473629,12345)",
				new CloudQueryCallback<AVCloudQueryResult>() {
					
					@Override
					public void done(AVCloudQueryResult arg0, AVException arg1) {
						// TODO Auto-generated method stub
						if(arg1==null){
							//存储成功
							
						}else{
							//失败的话，请检查网络环境以及sdk配置是否正确
							log.e("createObject","存储失败，请检查网络环境以及sdk是否配置正确");
						}	
					}
				});
	}
	
	private void modifyObject(){
		AVObject avobject=new AVObject();
		//获取用户的账号
		String account = avobject.getString("account");
		AVSaveOption avSaveOption=new AVSaveOption();
		AVQuery<AVObject> query=new AVQuery<AVObject>("uname");
		query.whereEqualTo("account", account);
		
		avSaveOption.query(query);
		avobject.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(AVException arg0) {
				// TODO Auto-generated method stub
				if(arg0.getCode()==305){
					Log.d("tag", "无法保存修改，账户名字已被其他人改变");
					
				}
			}
		});
	}
	
	//查询对象
	private void queryObject(){
		AVQuery<AVObject> avquery =new AVQuery<AVObject>("User");
		avquery.getInBackground("sdsdsds", new GetCallback<AVObject>() {
			
			@Override
			public void done(AVObject arg0, AVException arg1) {
				// TODO Auto-generated method stub
				int uid=arg0.getInt("uid");
				String account=arg0.getString("account");
				String password =arg0.getString("password");
				String objectId=arg0.getObjectId();
				Date updateAt=arg0.getUpdatedAt();
				Date createAt=arg0.getCreatedAt();
			}
		});
	}
	
	//同步对象
	private void synchronizationObject(){
		//假如已知了ObjectID可以用如下的方式创建一个AVObject
		AVObject anotherUser=AVObject.createWithoutData("User", "dsdssddssd");
		//然后调用刷新的方法，将数据从服务端拉倒本地
		anotherUser.fetchIfNeededInBackground(new GetCallback<AVObject>() {
			
			@Override
			public void done(AVObject arg0, AVException arg1) {
				// TODO Auto-generated method stub
				//调用fetchIfNeededInBackground和refreshInBackground效果是一样的
			}
		});
		
		//如果需要在保存或更新之后让本地数据自动与云端保持一致，可以使用保存选项fetchWhenSave
		anotherUser.setFetchWhenSave(true);
		anotherUser.saveInBackground();
		
		//同步指定属性
		final AVObject avObject=new AVObject();
		AVObject theUser=AVObject.createWithoutData("User", "11wew");
		String keys="account,password";//制定刷新的key字符串
		theUser.fetchInBackground(keys, new GetCallback<AVObject>() {
			
			@Override
			public void done(AVObject arg0, AVException arg1) {
				// TODO Auto-generated method stub
				//theUser的account和password属性的值就是与服务端一致的
				String account=avObject.getString("account");
				String password=avObject.getString("password");
			}
		});
	}
	
	//更新对象.LeanStorage的更新对象都是针对单个对象，在确保对象的ObjectId不为空时才可以更新对象。云端根据有没ObjectId来决定一个对象时新增还是更新
	private void updateObject(){
		final AVObject user=new AVObject("User");
		user.put("uid","用户编号");
		user.put("account","用户账号");
		user.put("password","用户密码");
		//保存到服务端
		user.saveInBackground(new SaveCallback() {   
			
			@Override
			public void done(AVException arg0) {
				// TODO Auto-generated method stub
				if(arg0==null){
					//存储成功,objectId会自动从服务端加载到本地
					Log.e("tag", "新建的user对象编号为"+user.getObjectId());
				}else{
					//失败的话，请检查网络环境以及sdk配置是否正确
					log.e("createObject","存储失败，请检查网络环境以及sdk是否配置正确");
				}
			}
		});
		
		//使用CQL语法来更新对象
		AVQuery.doCloudQueryInBackground("update User set uid=10002 where obejctId='s13826473629'", new CloudQueryCallback<AVCloudQueryResult>() {
			
			@Override
			public void done(AVCloudQueryResult arg0, AVException arg1) {
				// TODO Auto-generated method stub
				if(arg1==null){
					//存储成功
					
				}
			}
		});
	}
	/**
	 * 原子操作：更新计数器，更新数组
	 */
	//更新计数器
	private void updateCounter(){
		final AVObject user=AVObject.createWithoutData("User","sdsddsds");
		user.put("uname", "hello world");
		user.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(AVException arg0) {
				// TODO Auto-generated method stub
				//原子增加查看次数
				user.increment("uname");
				user.setFetchWhenSave(true);
				user.saveInBackground();
				//也可以使用increamentKey:byAmount：来给Number类型字段累加一个特定数值
				user.increment("uname",5);
				user.saveInBackground();
				//在saveInBackground调用之后，如果成功的话，对象的计数器字段是当前系统最新值
			}
		});
		
		
	}
	/*//更新数组
	private void updateArray(){
		//更新数组
				Date getDateWithDateString(String dateString){
					SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date=dateFormat.parse(dateString);
					return date;
				}
				void addReminders(){
					Date reminder1=getDateWithDateString("2015-11-11 07:10:00");
					Date reminder2=getDateWithDateString("2015-11-11 07:10:00");
					Date reminder3=getDateWithDateString("2015-11-11 07:10:00");
					
					AVObject todo=new AVObject("todo");
					todo.addUnique("reminders", Arrays.asList(reminder1,reminder2,reminder3));
					todo.saveInBackground();
				}													
	}*/
    
	//删除对象
	private void deleteObject(){
		AVObject user=AVObject.createWithoutData("User","sdsddsds");
	    user.deleteInBackground();
	    //使用CQL语法删除对象
	    AVQuery.doCloudQueryInBackground("delete fron User where objectId='2ewew'", new CloudQueryCallback<AVCloudQueryResult>() {

			@Override
			public void done(AVCloudQueryResult arg0, AVException arg1) {
				// TODO Auto-generated method stub
				if(arg1==null){
					//保存成功
				}
			}
		});
	   
	}
	
	/*
	 * 批量操作
	
	//批量创建、更新
	saveAll();
	saveAllInBackground()
	//批量删除
	deleteAll();
	deleteAllBackground();
	//批量获取
	fetchAll();
	fetchAllInBackground();
	 */
	private void setObject(){
		AVQuery<AVObject> query=new AVQuery<AVObject>("User");
		query.findInBackground(new FindCallback<AVObject>() {
			
			@Override
			public void done(List<AVObject> arg0, AVException arg1) {
				// TODO Auto-generated method stub
				ArrayList<AVObject> users=new ArrayList<AVObject>();
				for(AVObject user:users){
					user.put("sex","female");
				}
				AVObject.saveAllInBackground(users, new SaveCallback() {
					
					@Override
					public void done(AVException arg0) {
						// TODO Auto-generated method stub
						if(arg0==null){
							//出现错误
						}else{
							//保存成功
						}
					}
				});
				
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
