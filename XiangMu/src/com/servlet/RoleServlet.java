package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.po.Role;
import com.po.User;
import com.service.RoleService;
import com.service.UserService;
import com.utils.CopyPropertyUtil;
import com.utils.Page;

/**
 * 角色管理
 * 
 *
 */
public class RoleServlet  extends HttpServlet {
	static RoleService roleService;
	static {
		roleService = new RoleService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType( "text/xml;charset=UTF-8" );//设置响应格式
		//设置响应头信息
		response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		response.addHeader( "Expires", "0" );
		response.addHeader( "Pragma", "no-cache" );
		String method = request.getParameter("method");
		Role role = (Role) CopyPropertyUtil.copyPropertiesFromRequest(request,
				Role.class);
		PrintWriter out = response.getWriter();
		if ("query".equals(method)) {
			queryAll(request, response, role);
		}
		if ("add".equals(method)) {
			roleService.addRole(role);
			queryAll(request, response, role);
		}
		//验证角色名是否存在
		if("queryRole".equals(method)){
			role = roleService.checkRole(role);
			if(role.getRoleName()!=null){
				out.println("角色名已经存在");  
			}else{
				out.println("该角色名可以使用");
			}
			
		}
		if ("delete".equals(method)) {
			String str = request.getParameter("str");
			str = str.substring(0, str.length() - 1);
			String box[] = str.split(",");
			for (String temp : box) {
				int roleId = Integer.valueOf(temp);
				role.setRoleId(roleId);
				roleService.delRole(role);
			}
			queryAll(request, response, role);
		}
		if ("preUpdate".equals(method)) {
			String id = request.getParameter("id");
			role.setRoleId((Integer.valueOf(id)));
			role = roleService.findRole(role);
			request.setAttribute("role", role);
			request.getRequestDispatcher("/role/roleUpdate.jsp").forward(
					request, response);
		}
		if ("update".equals(method)) {
			roleService.updateRole(role);
			queryAll(request, response, role);
		}
		if("queryUser".equals(method)){
			List list = roleService.queryUser(role);
			request.setAttribute("userList", list);
			request.setAttribute("role", role);
			request.getRequestDispatcher("/role/userRoleList.jsp").forward(request,
					response);
		}
		if("selectUser".equals(method)){
			List list = roleService.selectUser();
			request.setAttribute("userList", list);
			request.setAttribute("role", role);
			request.getRequestDispatcher("/role/selectUser.jsp").forward(request,
					response);
		}
		if("addUser".equals(method)){
			String str = request.getParameter("str");
			roleService.addUser(str, role);
			List list = roleService.queryUser(role);
			request.setAttribute("userList", list);
			request.setAttribute("role", role);
			request.getRequestDispatcher("/role/userRoleList.jsp").forward(request,
					response);
		}
		if("delUser".equals(method)){
			String str = request.getParameter("str");
			roleService.delUser(str, role);
			List list = roleService.queryUser(role);
			request.setAttribute("userList", list);
			request.setAttribute("role", role);
			request.getRequestDispatcher("/role/userRoleList.jsp").forward(request,
					response);
		}
	}
	public void queryAll(HttpServletRequest request,
			HttpServletResponse response, Role role) throws ServletException,
			IOException {
		List<Role> list = roleService.findAll(role);
		request.setAttribute("roleList", list);
		request.setAttribute("role", role);
		request.getRequestDispatcher("/role/roleList.jsp").forward(request,
				response);
	}
}
