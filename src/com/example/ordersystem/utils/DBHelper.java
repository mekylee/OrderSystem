package com.example.ordersystem.utils;

import java.io.File;
import java.util.List;

import com.avos.avoscloud.AVObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
	private SQLiteDatabase db;
	private static final String DATABASE_NAME="temp.db";
	private static final int DATABASE_VERISON=1;
	private static final String CUSINE_TABLE_NAME="cusines";
	private static final String MENU_TABLE_NAME="menus";
	private static final String ORDER_TABLE_NAME="orders";
	private static final String CARTS_TABLE_NAME="carts";
	private static final String REST_TABLE_NAME="restaurant";
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERISON);
		}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		this.db=db;
		Log.i("创建的数据库路径为：", db.getPath().toString());
		db.execSQL("create table "+CUSINE_TABLE_NAME+" (id integer primary key autoincrement,image text,name text,price integer,type text)");
        Log.i("创建菜品表成功--->", CUSINE_TABLE_NAME); 
		db.execSQL("create table "+MENU_TABLE_NAME+"(id integer primary key autoincrement,name text)");
		Log.i("创建菜单表成功--->", MENU_TABLE_NAME);
		db.execSQL("create table "+REST_TABLE_NAME+"(id integer primary key autoincrement,name text,address text,phone integer)");
		Log.i("创建餐厅表成功--->", REST_TABLE_NAME);
        //  db.execSQL("create table"+ORDER_TABLE_NAME+"(id integer primary key autoincrement,ordernumber text,number_of_person integer)");
        //db.execSQL("create table"+CARTS_TABLE_NAME+"(id integer primary key autoincrement,name text,price text,int count)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
      db.execSQL("drop table if exites"+CUSINE_TABLE_NAME);
      db.execSQL("drop table if exites"+MENU_TABLE_NAME);
      db.execSQL("drop table if exites"+CARTS_TABLE_NAME);
      db.execSQL("drop table if exites"+ORDER_TABLE_NAME);
	}
	
	public void insert(ContentValues values,String table_name){
		SQLiteDatabase db=getWritableDatabase();
		  switch(table_name){
		    case MENU_TABLE_NAME:
				db.insert(MENU_TABLE_NAME, null, values);
		    	break;
		    case CUSINE_TABLE_NAME:
		    	db.insert(CUSINE_TABLE_NAME, null, values);
		    	break;
		    case CARTS_TABLE_NAME:
		    	db.insert(CARTS_TABLE_NAME, null, values);
		    	break;
		    case ORDER_TABLE_NAME:
		    	db.insert(ORDER_TABLE_NAME, null, values);
		}
	
		db.close();
	}
	
	
	//查询方法
	public Cursor query(String table_name){
		SQLiteDatabase db=getReadableDatabase();
		//查询获得Cursor
		Cursor c=db.query(table_name, null, null, null, null, null, null);
		return c;
	}
	
	//删除方法
	public void delete(String table_name,int id){
		if(db==null){
		SQLiteDatabase db=getWritableDatabase();
		db.delete(table_name, "id=?", new String[]{String.valueOf(id)});
		}
	}
	
	//关闭数据库
	public void close(){
		if(db!=null){
			db.close();
		}
	}
	
	
	
	


}
