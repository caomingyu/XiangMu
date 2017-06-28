package com.msun.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msun.entity.cms_category;


/**
 * 删除文章分类
 * 
 *
 */
public class delete_category extends  HttpServlet {
	
	static final String USER = "root";
	static final String PASS = "root";
	static final String DB_URL = "jdbc:mysql://localhost:3306/news?useUnicode=true&characterEncoding=utf-8";
	public  Connection conn;
	
	
	private String userName;
	
	private String psw;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("销毁方法");

	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		System.out.println("获得参数");
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("初始化方法");
		System.out.println("获得参数name"+arg0.getInitParameter("name")); 

	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException ,IOException {
		
		String id =request.getParameter("id");
		List<cms_category> result=new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			Statement st= conn.createStatement();
			String sql="delete from cms_category where id='"+id+"'";
			boolean rs=st.execute(sql);
			if(rs){
				//如果删除成功
				request.getRequestDispatcher("/News").forward(request, resp);
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/News").forward(request, resp);
		return ;
		
	}


}