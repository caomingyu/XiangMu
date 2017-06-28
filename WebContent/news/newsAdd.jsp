<%@ page language="java" import="java.util.*,com.po.*,com.service.*" pageEncoding="UTF-8"%>
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

	<body>
		<form action="${ pageContext.request.contextPath }/newsServlet" method="post">
			<fieldset>
				<legend>
					<br>
					新闻信息
				</legend>
				<input type="hidden" name="path" id="path"
					value=<%=request.getContextPath() %> />
				<input type="hidden" name="method" value="add" />
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
				<br/>
				标题：
				<input type="text" name="title" id="title" />
				<br>
				内容：
					
				<textarea name="content" style="width:200px;height:80px;" id="content"/></textarea>
				
				<br>
				<br />
				<input type="submit" value="提交" onclick="alert('添加成功');" /><input type="button" value="返回" onclick="history.go(-1);">
			</fieldset>
		</form>	
	</body>

</html>
