package com.example.ordersystem.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
   public static InputStream getStreamFromURL(String urlString){
	   InputStream is=null;
	   try{
		   URL url=new URL(urlString);
		   HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	  	   is=new BufferedInputStream(conn.getInputStream());
	  	
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   return is;
   }
}
