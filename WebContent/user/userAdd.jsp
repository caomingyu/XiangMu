<%@ page language="java" import="java.util.*,com.po.*,com.service.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>用户信息</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/FunctionJs.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/request.js"></script>
	</head>
	<script type="text/javascript">
	var request = false;
	function validateName(){
		var userName=document.getElementById("userName").value;
		var serverPath = document.getElementById("path").value;
		var url = serverPath+"/user";//服务器地址
			//请求参数
		var parameter = "method=queryUser&userName="+userName;
		request = httpRequest("post",url,true,callbackFunc,parameter);
	}

	function callbackFunc() {
		if (request.readyState == 4) {
			if (request.status == 200) {
					document.getElementById("spanUser").innerHTML = "<font color='red'>" + request.responseText + "</font>";
			}
		}
	}
</script>
	<body>
		<form action="${ pageContext.request.contextPath }/user" method="post">
			<fieldset>
				<legend>
					<br>
					用户信息
				</legend>
				<input type="hidden" name="path" id="path"
					value=<%=request.getContextPath() %> />
				<input type="hidden" name="method" value="add" />
				用户名：
				<input type="text" name="userName" id="userName"
					onblur="validateName()" />
				<span id="spanUser"></span>
				<br>
				密码：
				<input type="text" name="password" />
				<br>
				角色类别：
				<select name="role" id="" role"" style="width: 100">
					<option value="">
						请选择
					</option>
					<%
							Role role = new Role();
							RoleService roleService = new RoleService();
							List<Role> roleList = roleService.findAll(role);
								if(roleList!=null&&roleList.size()>0){
									for(int i=0;i<roleList.size();i++){
										role=roleList.get(i);%>
					<option value="<%=role.getRoleId() %>">
						<%=role.getRoleName() %>
					</option>
					<%}
								}%>
				</select>
				<br />
				<input type="submit" value="提交" onclick="alert(添加成功)" />
			</fieldset>
		</form>
	</body>
</html>
