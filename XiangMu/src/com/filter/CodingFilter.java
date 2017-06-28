package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CodingFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		
		String method=request.getMethod();
		
		String params=request.getQueryString();
		if(method.equalsIgnoreCase("post")){
			request.setCharacterEncoding("utf-8");
		}else{
			if(params!=null){
				if(params.indexOf("&")==-1){
					String[] str=params.split("=");
					String param=new String(request.getParameter(str[0]).
							getBytes("ISO-8859-1"), "UTF-8");
					request.setAttribute(str[0], param);
				}else{
					String[] strs = params.split("&");
					for (int i = 0; i < strs.length; i++) {
						String str = strs[i];
						String[] s = str.split("=");
						String param = new String(request.getParameter(s[0])
								.getBytes("iso-8859-1"), "utf-8");
						System.out.println(param);
						request.setAttribute(s[0], param);
					}
				}
			}
		}
		arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
