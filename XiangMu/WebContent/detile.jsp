<%@ page language="java"  import="java.util.*,com.po.*,com.service.*,com.dao.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/public.css" type="text/css" rel="stylesheet" />
		<link rel="stylesheet" href="css/index.css"  type="text/css"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrap1">
			<div class="head">
				<div class="head_left"></div>
				<div class="head_right">
					<form>
						<input type="text" style="background: url(images/search_input.jpg) no-repeat ;width: 145px;padding-left:2px;height: 30px;float: left;"/>
						<input type="button" style="background: url(images/search_button.jpg);width: 42px;height: 30px;float: left;" />
					</form>
				</div>
				<div class="clear"></div>
				<div class="head_ul">
					<ul>
						<li class="li_img"><a href="#"><img src="images/list_sy.gif"></a></li>
						<li><a href="#">学院概况</a></li>
						<li><a href="#">办学特色</a></li>
						<li><a href="#">学科专业</a></li>
						<li><a href="#">科学研究</a></li>
						<li><a href="#">人才培养</a></li>
						<li><a href="#">招生信息</a></li>
						<li><a href="#">学生就业</a></li>
						<li><a href="#">合作交流</a></li>
						<li><a href="#">社会服务</a></li>
						<li><a href="#">党建工作</a></li>
						<li><a href="#">教职员工</a></li>
						<li><a href="#">学院资讯</a></li>
					</ul>
				</div>
			</div>
			<div class="wrap">
				<div class="banner"></div>
				<div class="mainbody">
					<div class="mainbody_left">
						<div class="mainbody_left_h"><h1>栏目结构</h1></div>
						<div class="mainbody_left_list">
							<ul>
								<li onclick="DisplayList()" class="lione">
									<a href="#">详细信息</a>
									
								</li>
								<li onclick="DisplayList()" class="lione">
									<a href="index01.html">返回</a>
									
								</li>
							</ul>
						</div>
					</div>
					<div class="mainbody_right">
						<div class="mainbody_right_h">
							<h1>新闻信息列表</h1>
						</div>
						<div class="mainbody_right_list">
							<table border="0" width="100%" bordercolor="#43A3E9" align="center" style="font-size:14px;font-family:"微软雅黑"">
			
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
					发布时间
				</td>
				
			
			</tr>
			
			<tbody  align="center" valign="middle" id="tab">
				<%
				 NewsDao ListDao=new NewsDao();
				ArrayList<News> list=ListDao.getAllItems(); 


				if(list!=null&&list.size()>0)
		          {
		             for(int i=0;i<list.size();i++)
		             {
		                News item=list.get(i);
  		%>
				<tr> 
					<td>
						<input type="checkbox" name="chkSon"
							value="<%=item.getNewsId() %>" />
					</td>
					<td><%=i+1 %></td>
					<td><%=item.getTitle() %></td>
					<td><%=item.getAuthor() %></td>
					<td><%=item.getFirstTime() %></td>
					
					
				</tr>
		<% 
  		}
		          }
  		%>
  		
  		
  		
		</tbody>
		</table> 
						</div>
					</div>
				</div>
			</div>
			<div class="footer">
				<div class="footer_img"></div>
				<p class="p1" style="float: left;">版权所有© 东北师范大学信息与软件工程学院 地址：吉林省长春市净月大街2555号 邮编：130117</p>
				<p class="p2" style="float: right;">联系我们 | E-mail </p>
			</div>
		</div>
	
	
		
	
		
</body>
</html>