<%@ page language="java" import="java.util.*,com.po.*,com.service.*"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
	<table border="1" width="600px" bordercolor="#43A3E9" align="center"><tr align="center"><td ><span style="font-size: 26px">简易门户网站</span></td></tr></table>
		<table border="1" width="600px" bordercolor="#43A3E9" align="center">
			<tr>
				<td >
					<h2>新闻模块</h2>
				</td>
			</tr>
			<tr>
				<td ><form  action="${ pageContext.request.contextPath }/frontServlet?method=query" method="post">
		<fieldset>
		<legend>新闻信息查询</legend>
		 <input type="hidden" name="currPage" value="1"/>
   		标题：<input type="text" name="title" value="${news.title}"/>
   		
   			栏目类别：
				<select name="typeId" id="typeId" style="width: 100">
					<option value="">
						请选择
					</option>
					<%
							NewsType type = new NewsType();
							NewsTypeService typeService = new NewsTypeService();
							List<NewsType> typeList = typeService.findAll(type);
								if(typeList!=null&&typeList.size()>0){
									for(int i=0;i<typeList.size();i++){
										type = typeList.get(i);%>
					<option value="<%=type.getTypeId() %>">
						<%=type.getTypeName() %>
					</option>
					<%}
					}%>
				</select>
   		<input type="submit" value="查询""/>
		</fieldset>
		</form></td>
			</tr>
		</table>
		<table border="1" width="600px" bordercolor="#43A3E9" align="center">
			<tr bgcolor="#D5E8FD" align="center" valign="middle">
				<td>
					序号
				</td>
				<td>
					标题
				</td>
				<td>
					发布时间
				</td>
				<td>
					栏目
				</td>
			</tr>
			<tbody align="center" valign="middle" id="tab">


				<%
					List<News> list = (ArrayList) request.getAttribute("newsList");
					for (int i = 0; i < list.size(); i++) {
						News news = list.get(i);
						NewsService newsService = new NewsService();
						String typeName = newsService.findType(news);
						
				%>
				<tr>
					<td><%=i + 1%></td>
					<td>  <a href="frontServlet?method=viewNews&newsId=<%=news.getNewsId() %>"><%=news.getTitle()%></a></td>
					<td><%=news.getFirstTime()%></td>
					<td><%=typeName%></td>
				</tr>
				<%
					}
				%>
			</tbody>
			<tr bordercolor="#43A3E9"><td colspan="4">
					<a
						href="${pageContext.request.contextPath}/frontServlet?currPage=1&method=queryPage&title=${news.title}&typeId=${news.typeId}&state=${news.state}">首页</a>
					<a
						href="${pageContext.request.contextPath}/frontServlet?currPage=${page.currPage-1 }&method=queryPage&title=${news.title}&typeId=${news.typeId}&state=${news.state}">上一页</a>
					<a
						href="${pageContext.request.contextPath}/frontServlet?currPage=${page.currPage+1 }&method=queryPage&title=${news.title}&typeId=${news.typeId}&state=${news.state}">下一页</a>
					<a
						href="${pageContext.request.contextPath}/frontServlet?currPage=${page.totalPage }&method=queryPage&title=${news.title}&typeId=${news.typeId}&state=${news.state}">尾页</a>
					当前页数：${page.currPage } 总页数：${page.totalPage}</td></tr>
		</table>		
	</body>
</html>
