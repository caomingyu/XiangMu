<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>角色信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
 <script type="text/javascript">
 	function add(){
 		window.location="role/roleAdd.jsp";
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
			window.location="role?method=delete&str="+str;
		}
		return flag;
	}
	function update(){
			var arrSon = document.getElementsByName("chkSon");
			for( i=0;i<arrSon.length;i++){
				if(arrSon[i].checked==true){
					window.location="role?method=preUpdate&id="+arrSon[i].value;
				}
			}
	}
</script>
  
  <body>
    <form action="${ pageContext.request.contextPath }/role?method=query" method="post">
    <fieldset>
    	<legend>类别查询</legend>
    	<input type="hidden" name="currPage" value="1"/>
   		角色名：<input type="text" name="roleName" value="${role.roleName}"/>
		<input type="submit" value="查询""/>
	</fieldset>
    </form>
  	<table border="1" width="100%" bordercolor="#43A3E9" align="center">
  	<tr  bgcolor="#53BBF2">
  	   <td colspan="4" align="center" style="font-size: 24px;">
  		角色信息列表
  	   </td>
  	</tr>
  		<tr  bgcolor="#D5E8FD"  align="center" valign="middle">
  			<td>
					<input type="checkbox" name="chkAll" value="全选" id="chkAll"
						onClick="ChkAllClick('chkSon','chkAll')"/>
			</td>
  			<td>  类别</td>
  			<td>  描&nbsp;述</td>
  			<td>  操作</td>
  		</tr>
  		<tbody  align="center" valign="middle">
  		<c:forEach items="${roleList}" var="role">
  		<tr>
  		<td>
			<input type="checkbox" name="chkSon"
						value="${role.roleId }" />
				</td>
			<td>${role.roleName}</td>
			<td>${role.description}</td>
			<td><a href="role?method=queryUser&roleId=${role.roleId }">成员</a></td>
  		</tr>
  		</c:forEach>
  		 </tbody>
  	</table>
  	<table align="center"><tr  align="center" valign="middle"><td><input type="button" value="添加" onclick="add();"/>  <input type="button" value="修改" id="update" onclick="return update();"/>   <input type="button" value="删除" onclick="return del();"/></td></tr></table>
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
