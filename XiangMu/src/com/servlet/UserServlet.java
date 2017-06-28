package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.po.User;
import com.service.UserService;
import com.utils.CopyPropertyUtil;
import com.utils.Page;

public class UserServlet extends HttpServlet {
	static UserService userService;
	static {
		userService = new UserService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType( "text/xml;charset=UTF-8" );//设置响应格式
		//设置响应头信息
		response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		response.addHeader( "Expires", "0" );
		response.addHeader( "Pragma", "no-cache" );
		String method = request.getParameter("method");
		User user = (User) CopyPropertyUtil.copyPropertiesFromRequest(request,
				User.class);
		PrintWriter out = response.getWriter();
		//验证用户名是否存在
		if("queryUser".equals(method)){
			String userName =  request.getParameter("userName");
			user.setUserName(userName);
			user = userService.checkUser(user);
			if(user.getUserName()!=null){
				out.println("用户名已经存在");  
			}else{
				out.println("该用户名可以使用");
			}
			
		}
		
		if ("login".equals(method)) {
			user = userService.checkUser(user);
			if (StringUtils.isNotBlank(user.getUserName())) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				request.getRequestDispatcher("index.html").forward(request,
						response);
			} else {
				request.setAttribute("msg", "用户名或密码错误");
				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			}
		}
		if ("add".equals(method)) {
			userService.addUser(user);
			queryAll(request, response, user);
		}
		if ("delete".equals(method)) {
			String str = request.getParameter("str");
			str = str.substring(0, str.length() - 1);
			String box[] = str.split(",");
			for (String temp : box) {
				int userId = Integer.valueOf(temp);
				user.setUserId(userId);
				userService.delUser(user);
			}
			queryAll(request, response, user);
		}
		if ("preUpdate".equals(method)) {
			String id = request.getParameter("id");
			user.setUserId(Integer.valueOf(id));
			user = userService.findUser(user);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/user/userUpdate.jsp").forward(
					request, response);
		}
		if ("update".equals(method)) {
			userService.updateUser(user);
			queryAll(request, response, user);
		}
		if ("query".equals(method)) {
			queryAll(request, response, user);
		}
		if ("queryPage".equals(method)) {
			String userName = null, role = null;
		//	当查询条件是通过链接的时候，由于是中文，所以需要进行解码
				if (StringUtils.isNotBlank(request.getParameter("userName")))
					userName = new String(request.getParameter("userName")
							.getBytes("ISO-8859-1"), "UTF-8");
				if (StringUtils.isNotBlank(request.getParameter("role")))
					role = new String(request.getParameter("role").getBytes(
							"ISO-8859-1"), "UTF-8");
				user.setUserName(userName);
				user.setRole(role);
			queryAll(request, response, user);
		}
	}

	public void queryAll(HttpServletRequest request,
			HttpServletResponse response, User user) throws ServletException,
			IOException {
		int currPage;
		try {
			currPage = Integer.valueOf(request.getParameter("currPage"));
		} catch (Exception e) {
			currPage = 1;
		}
		Page page = new Page();
		List<User> list = userService.findAll(user);
		page.setTotalPage(list.size());
		page.toPage(currPage);
		list = userService.findAll(user, page);
		request.setAttribute("user", user);
		request.setAttribute("page", page);
		request.setAttribute("userList", list);
		request.getRequestDispatcher("/user/userList.jsp").forward(request,
				response);
	}
	public String changeBytes(String type,HttpServletRequest request) throws IOException{
		String str = new String(request.getParameter(type)
				.getBytes("ISO-8859-1"), "UTF-8");
		return str;
	}

}
