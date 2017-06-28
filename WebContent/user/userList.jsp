<%@ page language="java" import="java.util.*,com.po.*,com.service.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>用户信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
 <script type="text/javascript">
 	function add(){
 		window.location="user/userAdd.jsp";
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
		var flag = confirm('确认要删除记录？');
		if(flag == true){
			var arrSon = document.getElementsByName("chkSon");
			var str="";
			for( i=0;i<arrSon.length;i++){
				if(arrSon[i].checked==true){
					str+=arrSon[i].value+",";
				}
			}
			window.location="user?method=delete&str="+str;
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
    	<legend>用户信息查询</legend>
    	<input type="hidden" name="currPage" value="1"/>
   		用户名：<input type="text" name="userName" value="${user.userName}"/>
		角色：   		
						<select name="role" id=""role"" style="width: 100">
							<option value="">请选择</option>
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
   		<br>
		<input type="submit" value="查询""/>
	</fieldset>
    </form>
  	<table border="1" width="100%" bordercolor="#43A3E9" align="center">
  	<tr  bgcolor="#53BBF2">
  	   <td colspan="5" align="center" style="font-size: 24px;">
  		用户列表
  	   </td>
  	</tr>
  		<tr  bgcolor="#D5E8FD"  align="center" valign="middle">
  			<td>
					<input type="checkbox" name="chkAll" value="全选" id="chkAll"
						onClick="ChkAllClick('chkSon','chkAll')"/>
			</td>
			<td>序号</td>
  			<td>  用户名</td>
  			<td>  密码</td>
  			<td>  角色</td>
  		</tr>
  		<tbody  align="center" valign="middle" id="tab">
  		<%List<User> list =(ArrayList) request.getAttribute("userList"); 
  		UserService userService = new UserService();
  		 for(int i=0;i<list.size();i++){
  			 User user = list.get(i);
  			 String roleType = userService.getRoleType(user);
  		%>
  		<tr>
  		<td>
			<input type="checkbox" name="chkSon"
						value="<%=user.getUserId() %>" />
		</td>
			<td><%=i+1 %></td>
			<td><%=user.getUserName() %></td>
			<td><%=user.getPassword() %></td>
			<td><%=roleType %></td>
  		</tr>
  		<% 
  		}%>
  		  </tbody>
  	</table>
  	<table align="center"><tr  align="center" valign="middle"><td><input type="button" value="添加" onclick="add();"/>  <input type="button" value="修改" id="update" onclick="return update();"/>   <input type="button" value="删除" onclick="return del();"/></td></tr></table>
  	<table border="1"  width="100%" bordercolor="#43A3E9" align="center">
  	<tr  align="center" valign="middle">
  	<td>
  	<a href="${pageContext.request.contextPath}/user?currPage=1&method=queryPage&userName=${user.userName}&role=${user.role}">首页</a>
    <a href="${pageContext.request.contextPath}/user?currPage=${page.currPage-1 }&method=queryPage&userName=${user.userName}&role=${user.role}">上一页</a>
    <a href="${pageContext.request.contextPath}/user?currPage=${page.currPage+1 }&method=queryPage&userName=${user.userName}&role=${user.role}">下一页</a>
    <a href="${pageContext.request.contextPath}/user?currPage=${page.totalPage }&method=queryPage&userName=${user.userName}&role=${user.role}">尾页</a>
 		   当前页数：${page.currPage } 总页数：${page.totalPage}
  	</td>
  	</tr>
  	</table>
  </body>
  <script type="text/javascript">
<!--
var Ptr=document.getElementById("tab").getElementsByTagName("tr");
function $() {
    for (i=1;i<Ptr.length+1;i++) { 
    Ptr[i-1].style.backgroundColor = (i%2>0)?"#F7FBFF":"#DBEDFD"; 
    }
}
window.onload=$;
for(var i=0;i<Ptr.length;i++) {
    Ptr[i].onmouseover=function(){
    this.tmpClass=this.className;
    this.style.backgroundColor = "#58ADFA";
    
    };
    Ptr[i].onmouseout=function(){
    for (i=1;i<Ptr.length+1;i++) { 
    Ptr[i-1].style.backgroundColor = (i%2>0)?"#DBEDFD":"#F7FBFF"; 
    }
	}
}
//-->
</script>
</html>
