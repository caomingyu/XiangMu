<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="refresh" content="0; url=<%=path%>/login.jsp" />
		
    <title>My JSP 'logout.jsp' starting page</title>
  </head>
  
  <body>
    <%
    response.setHeader("Pragma","No-cache");          
    response.setHeader("Cache-Control","no-cache");   
    response.setHeader("Cache-Control", "no-store");   
    response.setDateHeader("Expires",0);
    if(session!=null)
        session.invalidate();
    %>
  </body>
</html>
