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
	    //����ͳ��Ӧ�� �Ĵ����
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
	 * �ֻ���һ����¼
	 */
	private void phoneNumberLogin(){
		//1.���÷�����֤��Ľӿ�
		AVOSCloud.requestSMSCodeInBackground("13826473629", new RequestMobileCodeCallback() {
			
			@Override
			public void done(AVException arg0) {
				// TODO Auto-generated method stub
				//����ʧ�ܿ��Բ鿴arg0�ṩ����Ϣ
				Log.e("tag", arg0.toString());
			}
		});
		//2.��UI�ϸ����û�������֤���������û������¼��ʱ��������½ӿ�:
		AVUser.signUpOrLoginByMobilePhoneInBackground("13826473629", "123456",new LogInCallback<AVUser>() {

			@Override
			public void done(AVUser arg0, AVException arg1) {
				// TODO Auto-generated method stub
				//���arg1Ϊ�վͱ�ʾ��¼�ɹ��ˣ�����User�Ǹ�ȫ�µ��û�
			}
		});
	}
	
	private void createObject(){
		//��������
		final AVObject userFolder=new AVObject("UserFolder");
		
		AVObject user1=new AVObject("User");
		user1.put("uid","001��");
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
		
		//��������
		AVRelation<AVObject> relation =userFolder.getRelation("����USer");
		relation.add(user1);
		relation.add(user2);
		relation.add(user3);
		//���浽�����
		userFolder.saveInBackground(new SaveCallback() {   
			
			@Override
			public void done(AVException arg0) {
				// TODO Auto-generated method stub
				if(arg0==null){
					//�洢�ɹ�,objectId���Զ��ӷ���˼��ص�����
					Log.e("tag", "�½���user������Ϊ"+userFolder
							
							
							.getObjectId());
				}else{
					//ʧ�ܵĻ����������绷���Լ�sdk�����Ƿ���ȷ
					log.e("createObject","�洢ʧ�ܣ��������绷���Լ�sdk�Ƿ�������ȷ");
				}
			}
		});
		
		
		
	}
	
	private void insertObject(){
		//ִ��CQL���ʵ������һ��User����
		AVQuery.doCloudQueryInBackground("insert into User(uid,account,password)values('0002',13826473629,12345)",
				new CloudQueryCallback<AVCloudQueryResult>() {
					
					@Override
					public void done(AVCloudQueryResult arg0, AVException arg1) {
						// TODO Auto-generated method stub
						if(arg1==null){
							//�洢�ɹ�
							
						}else{
							//ʧ�ܵĻ����������绷���Լ�sdk�����Ƿ���ȷ
							log.e("createObject","�洢ʧ�ܣ��������绷���Լ�sdk�Ƿ�������ȷ");
						}	
					}
				});
	}
	
	private void modifyObject(){
		AVObject avobject=new AVObject();
		//��ȡ�û����˺�
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
					Log.d("tag", "�޷������޸ģ��˻������ѱ������˸ı�");
					
				}
			}
		});
	}
	
	//��ѯ����
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
	
	//ͬ������
	private void synchronizationObject(){
		//������֪��ObjectID���������µķ�ʽ����һ��AVObject
		AVObject anotherUser=AVObject.createWithoutData("User", "dsdssddssd");
		//Ȼ�����ˢ�µķ����������ݴӷ������������
		anotherUser.fetchIfNeededInBackground(new GetCallback<AVObject>() {
			
			@Override
			public void done(AVObject arg0, AVException arg1) {
				// TODO Auto-generated method stub
				//����fetchIfNeededInBackground��refreshInBackgroundЧ����һ����
			}
		});
		
		//�����Ҫ�ڱ�������֮���ñ��������Զ����ƶ˱���һ�£�����ʹ�ñ���ѡ��fetchWhenSave
		anotherUser.setFetchWhenSave(true);
		anotherUser.saveInBackground();
		
		//ͬ��ָ������
		final AVObject avObject=new AVObject();
		AVObject theUser=AVObject.createWithoutData("User", "11wew");
		String keys="account,password";//�ƶ�ˢ�µ�key�ַ���
		theUser.fetchInBackground(keys, new GetCallback<AVObject>() {
			
			@Override
			public void done(AVObject arg0, AVException arg1) {
				// TODO Auto-generated method stub
				//theUser��account��password���Ե�ֵ����������һ�µ�
				String account=avObject.getString("account");
				String password=avObject.getString("password");
			}
		});
	}
	
	//���¶���.LeanStorage�ĸ��¶�������Ե���������ȷ�������ObjectId��Ϊ��ʱ�ſ��Ը��¶����ƶ˸�����ûObjectId������һ������ʱ�������Ǹ���
	private void updateObject(){
		final AVObject user=new AVObject("User");
		user.put("uid","�û����");
		user.put("account","�û��˺�");
		user.put("password","�û�����");
		//���浽�����
		user.saveInBackground(new SaveCallback() {   
			
			@Override
			public void done(AVException arg0) {
				// TODO Auto-generated method stub
				if(arg0==null){
					//�洢�ɹ�,objectId���Զ��ӷ���˼��ص�����
					Log.e("tag", "�½���user������Ϊ"+user.getObjectId());
				}else{
					//ʧ�ܵĻ����������绷���Լ�sdk�����Ƿ���ȷ
					log.e("createObject","�洢ʧ�ܣ��������绷���Լ�sdk�Ƿ�������ȷ");
				}
			}
		});
		
		//ʹ��CQL�﷨�����¶���
		AVQuery.doCloudQueryInBackground("update User set uid=10002 where obejctId='s13826473629'", new CloudQueryCallback<AVCloudQueryResult>() {
			
			@Override
			public void done(AVCloudQueryResult arg0, AVException arg1) {
				// TODO Auto-generated method stub
				if(arg1==null){
					//�洢�ɹ�
					
				}
			}
		});
	}
	/**
	 * ԭ�Ӳ��������¼���������������
	 */
	//���¼�����
	private void updateCounter(){
		final AVObject user=AVObject.createWithoutData("User","sdsddsds");
		user.put("uname", "hello world");
		user.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(AVException arg0) {
				// TODO Auto-generated method stub
				//ԭ�����Ӳ鿴����
				user.increment("uname");
				user.setFetchWhenSave(true);
				user.saveInBackground();
				//Ҳ����ʹ��increamentKey:byAmount������Number�����ֶ��ۼ�һ���ض���ֵ
				user.increment("uname",5);
				user.saveInBackground();
				//��saveInBackground����֮������ɹ��Ļ�������ļ������ֶ��ǵ�ǰϵͳ����ֵ
			}
		});
		
		
	}
	/*//��������
	private void updateArray(){
		//��������
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
    
	//ɾ������
	private void deleteObject(){
		AVObject user=AVObject.createWithoutData("User","sdsddsds");
	    user.deleteInBackground();
	    //ʹ��CQL�﷨ɾ������
	    AVQuery.doCloudQueryInBackground("delete fron User where objectId='2ewew'", new CloudQueryCallback<AVCloudQueryResult>() {

			@Override
			public void done(AVCloudQueryResult arg0, AVException arg1) {
				// TODO Auto-generated method stub
				if(arg1==null){
					//����ɹ�
				}
			}
		});
	   
	}
	
	/*
	 * ��������
	
	//��������������
	saveAll();
	saveAllInBackground()
	//����ɾ��
	deleteAll();
	deleteAllBackground();
	//������ȡ
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
							//���ִ���
						}else{
							//����ɹ�
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
