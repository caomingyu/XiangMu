<%@ page language="java" import="java.util.*,com.po.*,com.service.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>新闻内容</title>
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
		<form action="${ pageContext.request.contextPath }/newsServlet" method="post">
			<fieldset>
				<legend>
					<br>
					新闻内容<input type="text" name="title" id="title" value="${news.title }"/>
				</legend>
				<input type="hidden" name="path" id="path"
					value=<%=request.getContextPath() %> />
				<input type="hidden" name="method" value="update" />
				<input type="hidden" name="newsId" value="${news.newsId}" />
				<%--   栏目类别：
				<select name="typeId" id="typeId" style="width: 100">
					<option value="">
						请选择
					</option>
					<%
							News n = (News)request.getAttribute("news");
							NewsType type = new NewsType();
							NewsTypeService typeService = new NewsTypeService();
							List<NewsType> typeList = typeService.findAll(type);
								if(typeList!=null&&typeList.size()>0){
									for(int i=0;i<typeList.size();i++){
										type = typeList.get(i);%>
					<option value="<%=type.getTypeId() %>" <%if(n.getTypeId().equals(type.getTypeId()+""))  out.print("selected=selected");%>)>
						<%=type.getTypeName() %>
					</option>
					<%}
					}%>
				</select> --%>
				<br/>
				标题：
				<input type="text" name="title" id="title" value="${news.title }"/>
				<br>
				内容：<br/>
				<textarea name="content" style="width:200px;height:80px;" id="content"/></textarea>
				<br>

				<br />
				<input type="submit" value="更新" onclick="alert('更新成功');" /><input type="button" value="返回" onclick="history.go(-1);">
			</fieldset>
		</form>
	</body>

</html>
