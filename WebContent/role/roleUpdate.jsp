<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色信息修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <body>
    <form action="${ pageContext.request.contextPath }/role?method=update" method="post">
    <fieldset>
    	<legend>类别信息</legend>
    		<input type="hidden" name="roleId" value="${ role.roleId}"> 
   		 类别名称：<input type="text" name="roleName" value="${ role.roleName}" /><br>
		描   述：<input type="text" name="description" value="${role.description }"/><br>
		<input type="submit" value="提交""/><input type="button" value="返回" onclick="history.go(-1);">
	</fieldset>
    </form>
 
  </body>
</html>
