<%@ page language="java" import="java.util.*,com.po.*,com.service.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>角色成员列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
 <script type="text/javascript">
 	function add(){
 	 		var roleId = document.getElementById("roleId").value;
 			var url ="role?method=selectUser&roleId="+roleId;
 			var value = window.showModalDialog(url,"","");
 			if(value!="")
 				window.location = 'role?method=addUser&str='+value+'&roleId='+roleId;
 	}
	function ChkAllClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		var tempState = cbAll.checked;
		for (i = 0; i < arrSon.length; i++) {
			if (arrSon[i].checked != tempState)
				arrSon[i].click();
		}
	}
	function del(){
		var roleId = document.getElementById("roleId").value;
		var flag = confirm('确认要删除记录？');
		if(flag == true){
			var arrSon = document.getElementsByName("chkSon");
			var str="";
			for( i=0;i<arrSon.length;i++){
				if(arrSon[i].checked==true){
					str+=arrSon[i].value+",";
				}
			}
			window.location="role?method=delUser&str="+str+'&roleId='+roleId;
		}
		return flag;
	}
	function update(){
			var arrSon = document.getElementsByName("chkSon");
			for( i=0;i<arrSon.length;i++){
				if(arrSon[i].checked==true){
					window.location="user?method=preUpdate&id="+arrSon[i].value;
				}
			}
	}
</script>
  
  <body>
    <form action="${ pageContext.request.contextPath }/user?method=query" method="post">
    <fieldset>
    	<legend>信息查询</legend>
    	<input type="hidden" name="currPage" value="1"/>
   		用户名：<input type="text" name="userName" value="${user.userName}"/>
		<input type="submit" value="查询""/>
	</fieldset>
    </form>
    <input type="hidden" id="roleId" value="${role.roleId}" name="roleId">
  	<table border="1" width="100%" bordercolor="#43A3E9" align="center">
  	<tr  bgcolor="#53BBF2">
  	   <td colspan="4" align="center" style="font-size: 24px;">
  		角色成员列表
  	   </td>
  	</tr>
  		<tr  bgcolor="#D5E8FD"  align="center" valign="middle">
  			<td>
					<input type="checkbox" name="chkAll" value="全选" id="chkAll"
						onClick="ChkAllClick('chkSon','chkAll')"/>
			</td>
  			<td>  用户名</td>
  		</tr>
  		<tbody  align="center" valign="middle">
  		<%List<User> list =(ArrayList) request.getAttribute("userList"); 
  		 for(int i=0;i<list.size();i++){
  			 User user = list.get(i);
  		%>
  		<tr>
  		<td>
			<input type="checkbox" name="chkSon"
					value="<%=user.getUserId() %>" />
		</td>
			<td><%=user.getUserName() %></td>
  		</tr>
  		<% 
  		}%>
  		</tbody>
  	</table>
  	<table align="center"><tr  align="center" valign="middle"><td>   <input type="button" value="删除" onclick="return del();"/>
  	<input type="button" value="返回" onclick="window.location='role?method=query'"/>
  	</td></tr></table>
  </body>

</html>
