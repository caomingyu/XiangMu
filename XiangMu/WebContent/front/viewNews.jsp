<%@ page language="java" import="java.util.*,com.po.*,com.service.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新闻信息</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/FunctionJs.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/request.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/ueditor/editor_config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=request.getContextPath() %>/ueditor/editor_all.js"></script>			
	</head>
	<script type="text/javascript">
	</script>
	<body>
		<table border="1" width="800px" bordercolor="#43A3E9" align="center">
			<tr bgcolor="#D5E8FD" align="center" >
				<td  >
				<h1 style="text-align:center;font-size: 16px">
				${news.title }</h1>
				
				</td>
			</tr>
			<tr>
				<td>
					发布者:<span style="color:red">${news.author }</span> &nbsp;&nbsp;&nbsp;发布时间：<span style="color:red">${news.firstTime }</span> 
				</td>
			</tr>
			<tr>
				<td>
					${news.content }
				</td>
			</tr>
			<tr align="center"><td align="center"><input type="button" value="返回" onclick="window.location='front/index.jsp';"></td></tr>
		</table>	
		<br/>
		<table  border="1" width="800px" bordercolor="#43A3E9" align="center">
		<%List<Comment> list = (ArrayList)request.getAttribute("cList");
		  for(int i=0;i<list.size();i++){
			  Comment com = list.get(i);
		%>
		<tr><td>评论者：<%=com.getName() %></td><td>评论时间: <%=com.getPTime() %></td></tr>
		<tr><td colspan="2">内容:<%=com.getContent() %></td></tr>
		<%} %>
		</table>
		<table  align="center"   border="1" width="800px" ><tr><td>
		<form action="frontServlet?method=addComment" method="post">
		<fieldset>
		<legend>发表评论</legend>
		<input name="newsId" value="${news.newsId }" type="hidden">
		昵称：<input type="text" name="name"><br/>
		内容:<script  id="editor" type="text/plain" name="content">很好！</script>	
		<input type="submit" value="提交">
		</fieldset>
		</form>
		</td></tr></table>
	</body>
<script type="text/javascript">
   //实例化编辑器
    var ue = UE.getEditor('editor');
    ue.addListener('ready',function(){
        this.focus()
    });

</script>		
</html>
