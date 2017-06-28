package com.test;

import java.lang.reflect.Method;
import java.util.List;
import com.utils.SqlHelper;


public class Test {
	/**
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql = "select * from user";
		List<?> list = new SqlHelper().executeQuery(sql, null);
		Object o = list.get(0);
		Object t =getFieldValueByName("userName",o);
		System.out.println(t);
		
	}
	public static Object getFieldValueByName(String fieldName, Object o)    
	{       
	   try    
	   {       
	       String firstLetter = fieldName.substring(0, 1).toUpperCase();       
	       String getter = "get" + firstLetter + fieldName.substring(1);       
	       Method method = o.getClass().getMethod(getter, new Class[] {});       
	       Object value = method.invoke(o, new Object[] {});       
	       return value;       
	   } catch (Exception e)    
	   {       
	       System.out.println("属性不存在");       
	       return null;       
	   }       
	}
}
