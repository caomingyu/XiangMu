<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>栏目信息修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  <body>
    <form action="${ pageContext.request.contextPath }/typeServlet?method=update" method="post">
    <fieldset>
    	<legend>栏目信息</legend>
    		<input type="hidden" name="typeId" value="${type.typeId}"> 
   		 栏目名：<input type="text" name="typeName" value="${ type.typeName}" /><br>
		描   述：<input type="text" name="creator" value="${type.creator }" readonly="readonly"/><br>
		<input type="submit" value="提交""/><input type="button" value="返回" onclick="history.go(-1);">
	</fieldset>
    </form>
 
  </body>
</html>
