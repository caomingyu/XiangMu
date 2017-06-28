<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>首页</title>
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
	function submitCheck() {
		var userName = $('#userName').val();
		var password = $('#password').val();
		if (userName == "") {
			$('#msg').html("请输入用户名！");
			return false;
		}
		if (password == "") {
			$('#msg').html("请输入密码！");
			return false;
		}
		return true;
	}
</script>
</head>
<body style="margin:0px;padding:0px">
	<div style="margin: 100px auto; width:30%">
		<form action="${pageContext.request.contextPath }/user?method=login" onsubmit="return submitCheck();"
			method="post" id="loginForm">
			<fieldset>
			<legend>用户登录</legend>
			用户名：<input type="text" id="userName" name="userName" /><br /> <br/>
			密  &nbsp;&nbsp;&nbsp;码：<input
				type="password" id="password" name="password" /><br /> 
				<input type="submit" value="登录" /> <br /> 
			<span id="msg" style="color: red">${msg}</span>
			</fieldset>
		</form>
	</div>
</body>
</html>