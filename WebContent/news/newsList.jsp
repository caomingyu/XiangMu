<%@ page language="java" import="java.util.*,com.po.*,com.service.*"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>新闻信息列表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
	</head>
	<script type="text/javascript">
 	function add(){
 		window.location="news/newsAdd.jsp";
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
			window.location="newsServlet?method=delete&str="+str;
		}
		return flag;
	}
	function access(){
		var flag = confirm('确认要审核通过？');
		if(flag == true){
			var arrSon = document.getElementsByName("chkSon");
			var str="";
			for( i=0;i<arrSon.length;i++){
				if(arrSon[i].checked==true){
					str+=arrSon[i].value+",";
				}
			}
			window.location="newsServlet?method=access&str="+str;
		}
		return flag;
	}
	function reject(){
		var flag = confirm('确认要 不通过？');
		if(flag == true){
			var arrSon = document.getElementsByName("chkSon");
			var str="";
			for( i=0;i<arrSon.length;i++){
				if(arrSon[i].checked==true){
					str+=arrSon[i].value+",";
				}
			}
			window.location="newsServlet?method=reject&str="+str;
		}
		return flag;
	}	
	function update(){
			var arrSon = document.getElementsByName("chkSon");
			for( i=0;i<arrSon.length;i++){
				if(arrSon[i].checked==true){
					window.location="newsServlet?method=preUpdate&id="+arrSon[i].value;
				}
			}
	}
</script>

	<body>

		<form  action="${ pageContext.request.contextPath }/newsServlet?method=query" method="post">
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
				状态:
				<select  name="state" id="typeId" style="width: 100">
				<option>请选择</option>
				<option>已审核</option>
				<option>未审核</option>
				<option>不通过</option>
				</select>
   		<input type="submit" value="查询""/>
		</fieldset>
		</form>

		<table border="1" width="100%" bordercolor="#43A3E9" align="center">
			<tr bgcolor="#53BBF2">
				<td colspan="7" align="center" style="font-size: 24px;">
					新闻信息列表
				</td>
			</tr>
			<tr bgcolor="#D5E8FD" align="center" valign="middle">
				<td>
					<input type="checkbox" name="chkAll" value="全选" id="chkAll"
						onClick="ChkAllClick('chkSon','chkAll')" />
				</td>
				<td>序号</td>
				<td>
					标题
				</td>
				<td>
					作者
				</td>
					<td>
					内容
				</td>
				<td>
					发布时间
				</td>
				<td>
					状态
				</td>
				<td>
					栏目
				</td>
			</tr>
			<tbody  align="center" valign="middle" id="tab">
				<%List<News> list =(ArrayList) request.getAttribute("newsList"); 
  		 for(int i=0;i<list.size();i++){
  			 News news = list.get(i);
  			 NewsService newsService = new NewsService();
  			String typeName = newsService.findType(news);
  		%>
				<tr> 
					<td>
						<input type="checkbox" name="chkSon"
							value="<%=news.getNewsId() %>" />
					</td>
					<td><%=i+1 %></td>
					<td><%=news.getTitle() %></td>
					<td><%=news.getAuthor() %></td>
					<td><%=news.getContent() %></td>
					<td><%=news.getFirstTime() %></td>
					<td><%=news.getState() %></td>
					<td><%=typeName %></td>
				</tr>
		<% 
  		}%>
		</tbody>
		</table>
		 <table align="center">
			<tr align="center" valign="middle">
				<td>
					<input type="button" value="添加" onclick="add();" />
					<input type="button" value="修改" id="update"
						onclick="return update();" />
					<%User user = (User)request.getSession().getAttribute("user");
					if(user.getRole().equals("1")){
					%>
					<input type="button" value="删除" onclick="return del();"/>
					<input type="button" value="审核通过" onclick="return access();"/>
					<input type="button" value="不通过" onclick="return reject();"/>
					<%} %> 
				</td>
			</tr>
		</table>  
		<table border="1" width="100%" bordercolor="#43A3E9" align="center">
			<tr align="center" valign="middle">
				<td>
					<a
						href="${pageContext.request.contextPath}/newsServlet?currPage=1&method=queryPage&title=${news.title}&typeId=${news.typeId}&state=${news.state}">首页</a>
					<a
						href="${pageContext.request.contextPath}/newsServlet?currPage=${page.currPage-1 }&method=queryPage&title=${news.title}&typeId=${news.typeId}&state=${news.state}">上一页</a>
					<a
						href="${pageContext.request.contextPath}/newsServlet?currPage=${page.currPage+1 }&method=queryPage&title=${news.title}&typeId=${news.typeId}&state=${news.state}">下一页</a>
					<a
						href="${pageContext.request.contextPath}/newsServlet?currPage=${page.totalPage }&method=queryPage&title=${news.title}&typeId=${news.typeId}&state=${news.state}">尾页</a>
					当前页数：${page.currPage } 总页数：${page.totalPage}
				</td>
			</tr>
		</table>
	</body>

</html>
