package com.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class CopyPropertyUtil {
	public static Object copyPropertiesFromRequest(HttpServletRequest request,Class<?> cls){
		try {
			Object o = Class.forName(cls.getName()).newInstance();
			BeanUtils.populate(o, request.getParameterMap());
			return o;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
