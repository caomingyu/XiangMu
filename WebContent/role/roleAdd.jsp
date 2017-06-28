<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>角色信息</title>
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
		var roleName=document.getElementById("roleName").value;
		var serverPath = document.getElementById("path").value;
		var url = serverPath+"/role";//服务器地址
			//请求参数
		var parameter = "method=queryRole&roleName="+roleName;
		request = httpRequest("post",url,true,callbackFunc,parameter);
	}

	function callbackFunc() {
		if (request.readyState == 4) {
			if (request.status == 200) {
					document.getElementById("spanRole").innerHTML = "<font color='red'>" + request.responseText + "</font>";
			}
		}
	}
</script>
	<body>
		<form action="${ pageContext.request.contextPath }/role" method="post">
			<fieldset>
				<legend>
					<br>
					类别信息
				</legend>
				<input type="hidden" name="path" id="path"
					value=<%=request.getContextPath() %> />
				<input type="hidden" name="method" value="add" />
				类别名称：
				<input type="text" name="roleName" id="roleName"
					onblur="validateName()" /><span id="spanRole"></span>
				<br>
				描述：
				<input type="text" name="description" />
				<br>
				<br />
				<input type="submit" value="提交" onclick="alert(添加成功)" /><input type="button" value="返回" onclick="history.go(-1);">
			</fieldset>
		</form>
	</body>
</html>
