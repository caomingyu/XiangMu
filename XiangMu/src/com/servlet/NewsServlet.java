package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.po.News;
import com.po.User;
import com.service.NewsService;
import com.service.UserService;
import com.utils.CopyPropertyUtil;
import com.utils.Page;
/**
 * 新闻控制器
 *
 *
 */
public class NewsServlet extends HttpServlet {
	static NewsService newsService;
	static{
		newsService = new NewsService();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType( "text/xml;charset=UTF-8" );//设置响应格式
		//设置响应头信息
		response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		response.addHeader( "Expires", "0" );
		response.addHeader( "Pragma", "no-cache" );
		String method = request.getParameter("method");
		News news = (News) CopyPropertyUtil.copyPropertiesFromRequest(request,
				News.class);
		PrintWriter out = response.getWriter();
		if("query".equals(method)){
			queryAll(request, response, news);
		}if("add".equals(method)){
			User user = (User) request.getSession().getAttribute("user");
			if(user!=null)
				news.setAuthor(user.getUserName());
			else
				news.setAuthor("匿名");
			newsService.addNews(news);
			news = new News();
			queryAll(request, response, news);
		}if("preUpdate".equals(method)){
			String id = request.getParameter("id");
			news.setNewsId(Integer.valueOf(id));
			news = (News) newsService.findAll(news).get(0);
			request.setAttribute("news", news);
			request.getRequestDispatcher("/news/newsUpdate.jsp").forward(request,
					response);
		}if("update".equals(method)){
			newsService.updateNews(news);
			queryAll(request, response, news);
		}if("delete".equals(method)){
			String str = request.getParameter("str");
			String box[] = str.split(",");
			for (String temp : box) {
				news.setNewsId(Integer.valueOf(temp));
				newsService.delNews(news);
			}
			news = new News();
			queryAll(request, response, news);
		}if("access".equals(method)){
			String str = request.getParameter("str");
			String box[] = str.split(",");
			String state="已审核";
			for (String temp : box) {
				news.setNewsId(Integer.valueOf(temp));
				newsService.updateState(news, state);
			}
			news = new News();
			queryAll(request, response, news);
		}if("reject".equals(method)){
			String str = request.getParameter("str");
			String box[] = str.split(",");
			String state="不通过";
			for (String temp : box) {
				news.setNewsId(Integer.valueOf(temp));
				newsService.updateState(news, state);
			}
			news = new News();
			queryAll(request, response, news);
		}
		
		if("queryPage".equals(method)){
			String title=null,typeId=null,state=null;
			//	当查询条件是通过链接的时候，由于是中文，所以需要进行解码
					if (StringUtils.isNotBlank(request.getParameter("title")))
						title = new String(request.getParameter("title")
								.getBytes("ISO-8859-1"), "UTF-8");
					if (StringUtils.isNotBlank(request.getParameter("typeId")))
						typeId = new String(request.getParameter("typeId")
								.getBytes("ISO-8859-1"), "UTF-8");
					if (StringUtils.isNotBlank(request.getParameter("state")))
						state = new String(request.getParameter("state")
								.getBytes("ISO-8859-1"), "UTF-8");
					news.setTitle(title);
					news.setTypeId(typeId);
					news.setState(state);
					queryAll(request, response, news);
		}
	}
	public void queryAll(HttpServletRequest request,
			HttpServletResponse response, News news) throws ServletException,
			IOException {
		int currPage;
		try {
			currPage = Integer.valueOf(request.getParameter("currPage"));
		} catch (Exception e) {
			currPage = 1;
		}
		Page page = new Page();
		List<News> list = newsService.findAll(news);
		page.setTotalPage(list.size());
		page.toPage(currPage);
		list = newsService.findAll(news, page);
		request.setAttribute("news", news);
		request.setAttribute("page", page);
		request.setAttribute("newsList", list);
		request.getRequestDispatcher("/news/newsList.jsp").forward(request,
				response);
	}
}
