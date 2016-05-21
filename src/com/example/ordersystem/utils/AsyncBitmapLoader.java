package com.example.ordersystem.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class AsyncBitmapLoader {
     public Map<String,SoftReference<Bitmap>> imageCache=null;
     private static final String imageFilePath="/data/data/cusineimage/";
     public AsyncBitmapLoader(){
    	 imageCache=new HashMap<String,SoftReference<Bitmap>>();
     }
     
     //从图片的url中获取bitmap
     public Bitmap getBitmapFromUrl(final ImageView imageView,final String urlString,final ImageCallback imageCallback){
  	    if(imageCache.containsKey(urlString)){
  	    	SoftReference<Bitmap> softReference =imageCache.get(urlString);
  	    	Bitmap bitmap=softReference.get();
  	    	if(bitmap!=null){
  	    		return bitmap;
  	    	}
  	    }else{
  	    	String bitmapName=urlString.substring(urlString.lastIndexOf("/")+1);
  	    	File cacehDir=new File(Environment.getExternalStorageDirectory(),imageFilePath);
  	    	File[] cacheFiles=cacehDir.listFiles();
  	    	int i=0;
  	    	if(cacheFiles!=null){
  	    		for(;i<cacheFiles.length;i++){
  	    			if(bitmapName.equals(cacheFiles[i].getName())){
  	    				break;
  	    			}
  	    		}
  	    		if(i<cacheFiles.length){
  	    			return BitmapFactory.decodeFile(imageFilePath+bitmapName);
  	    		}
  	    	}
  	    }
  	    final Handler handler=new Handler(){
  	    	public void handleMessage(android.os.Message msg) {
  	    		imageCallback.imageLoad(imageView,(Bitmap)msg.obj);
  	    	}
  	    };
  	    //如果不在内存缓存中，也不在本地，则开启线程下载图片
  	    new Thread(){
  	    	public void run() {
  	    	    InputStream is=HttpUtils.getStreamFromURL(urlString);
  	  		    Bitmap bitmap=BitmapFactory.decodeStream(is);
  	  		    imageCache.put(urlString, new SoftReference<Bitmap>(bitmap));
  	  		    Message msg=handler.obtainMessage(0, bitmap);
  	  		    handler.sendMessage(msg);
  	  		    File dir=new File(imageFilePath);
  	  		    if(!dir.exists()){
  	  		    	dir.mkdirs();
  	  		    }
  	  		    File bitmapFile=new File(imageFilePath+urlString.substring(urlString.lastIndexOf("/")+1));
  	  		    if(!bitmapFile.exists()){
  	  		    	try{
  	  		    		bitmapFile.createNewFile();
  	  		    	}catch(IOException e){
  	  		    		e.printStackTrace();
  	  		    	}
  	  		    }
  	  		    FileOutputStream fos;
  	  		    try{
  	  		    	fos=new FileOutputStream(bitmapFile);
  	  		    	bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
  	  		        fos.close();
  	  		    }catch(FileNotFoundException e){
  	  		    	e.printStackTrace();
  	  		    }catch (IOException e) {
				     e.printStackTrace();
				}
  	    	}
  	    }.start();
  	    return null;
     }
    	
  	  public interface ImageCallback{
  		  public void imageLoad(ImageView imageView,Bitmap bitmap);
  	  }
}
    

