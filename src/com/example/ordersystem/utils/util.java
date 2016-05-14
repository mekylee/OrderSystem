package com.example.ordersystem.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class util {
	
	/*
     * 判断电子邮箱地址是否正确
     */
    public boolean isEmail(String email){
   	 String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
   	 Pattern p = Pattern.compile(str);
   	 Matcher m = p.matcher(email);
   	 return m.matches();
    }
    
    /*
     * 
     */
}
