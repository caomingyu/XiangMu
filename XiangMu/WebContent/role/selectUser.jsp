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

	function ChkAllClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		var tempState = cbAll.checked;
		for (i = 0; i < arrSon.length; i++) {
			if (arrSon[i].checked != tempState)
				arrSon[i].click();
		}
	}
	function add(){
			var arrSon = document.getElementsByName("chkSon");
			var str="";
			for( i=0;i<arrSon.length;i++){
				if(arrSon[i].checked==true){
					str+=arrSon[i].value+",";
				}
			}
			window.returnValue = str;
			window.close();
	}
	function closeWin(){
		window.returnValue = "";
		window.close();
	}
</script>
  
  <body>
    <form action="${ pageContext.request.contextPath }/user?method=query" method="post">
    <fieldset>
    	<legend>用户信息查询</legend>
    	<input type="hidden" name="currPage" value="1"/>
   		用户名：<input type="text" name="userName" value="${user.userName}"/>

   		<br>
		<input type="submit" value="查询""/>
	</fieldset>
    </form>
  	<table border="1" width="100%" bordercolor="#43A3E9" align="center">
  	<tr  bgcolor="#53BBF2">
  	   <td colspan="4" align="center" style="font-size: 24px;">
  		用户信息列表
  	   </td>
  	</tr>
  		<tr  bgcolor="#D5E8FD"  align="center" valign="middle">
  			<td>
					<input type="checkbox" name="chkAll" value="全选" id="chkAll"
						onClick="ChkAllClick('chkSon','chkAll')"/>
			</td>
  			<td>  用户名</td>
  		</tr>
  		<tbody  align="center" valign="middle" id="tab">
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
  	<table align="center"><tr  align="center" valign="middle"><td> <input type="button" value="确定" onclick="return add();"/>
  	<input type="button" value="关闭" onclick="return closeWin();"/>
  	</td></tr></table>

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
