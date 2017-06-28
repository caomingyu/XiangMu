package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.po.NewsType;
import com.po.Role;
import com.service.NewsTypeService;
import com.utils.CopyPropertyUtil;
import com.utils.Page;
/**
 * 栏目管理
 * 
 *
 */
public class NewsTypeServlet extends HttpServlet {
	static NewsTypeService typeService;
	static{
		typeService = new NewsTypeService();
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8" );//设置响应格式
		//设置响应头信息
		response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		response.addHeader( "Expires", "0" );
		response.addHeader( "Pragma", "no-cache" );
		String method = request.getParameter("method");
		NewsType type = (NewsType) CopyPropertyUtil.copyPropertiesFromRequest(request,
				NewsType.class);
		PrintWriter out = response.getWriter();
		if("query".equals(method)){
			queryAll(request, response, type);
		}
		if("queryType".equals(method)){
			List list = typeService.findAll(type);
			if(list.size()==0){
				out.println("该栏目名可以使用");  
			}else{
				out.println("该栏目名已存在，请重新输入"); 
			}
		}
		if("add".equals(method)){
			typeService.addType(type);
			type.setTypeName("");
			queryAll(request, response, type);
		}
		if ("delete".equals(method)) {
			String str = request.getParameter("str");
			String box[] = str.split(",");
			for (String temp : box) {
				int typeId = Integer.valueOf(temp);
				type.setTypeId(typeId);
				typeService.delType(type);
			}
			queryAll(request, response, type);
		}
		if ("preUpdate".equals(method)) {
			String id = request.getParameter("id");
			type.setTypeId((Integer.valueOf(id)));
			type = (NewsType) typeService.findAll(type).get(0);
			request.setAttribute("type", type);
			request.getRequestDispatcher("/type/typeUpdate.jsp").forward(
					request, response);
		}
		if ("update".equals(method)) {
			typeService.updateType(type);
			queryAll(request, response, type);
		}		
	}
	public void queryAll(HttpServletRequest request,
			HttpServletResponse response, NewsType type) throws ServletException,
			IOException {
		List<Role> list = typeService.findAll(type);
		request.setAttribute("typeList", list);
		request.setAttribute("type", type);
		request.getRequestDispatcher("/type/typeList.jsp").forward(request,
				response);
	}	
}
