<%@ page language="java" import="java.util.*,com.po.*,com.service.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户信息修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <body>
    <form action="${ pageContext.request.contextPath }/user?method=update" method="post">
    <fieldset>
    	<legend>用户信息</legend>
    		<input type="hidden" name="userId" value="${ user.userId}"> 
   		用户名：<input type="text" name="userName" value="${ user.userName}" /><br>
		密码：<input type="text" name="password" value="${user.password }"/><br>
		角色类别：   		
						<select name="role" id=""role"" style="width: 100">
							<option value="">请选择</option>
							<%
							User u =(User)request.getAttribute("user");
							Role role = new Role();
							RoleService roleService = new RoleService();
							List<Role> roleList = roleService.findAll(role);
								if(roleList!=null&&roleList.size()>0){
									for(int i=0;i<roleList.size();i++){
										role=roleList.get(i);%>
											<option value="<%=role.getRoleId() %>"  <%if(u.getRole().equals(role.getRoleId()+"")) out.print("selected=selected"); %>)>
												<%=role.getRoleName() %>
											</option>
									<%}
								}%>
							</select>
   		<br>
		<input type="submit" value="提交""/><input type="button" value="返回" onclick="history.go(-1);">
	</fieldset>
    </form>
 
  </body>
</html>
