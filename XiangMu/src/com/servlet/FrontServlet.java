package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.po.Comment;
import com.po.News;
import com.service.NewsService;
import com.utils.CopyPropertyUtil;
import com.utils.Page;
/**
 * 前台展示数据的servlet
 * 
 *
 */
public class FrontServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		String method = request.getParameter("method");
		News news = (News) CopyPropertyUtil.copyPropertiesFromRequest(request,
				News.class);
		Comment com = (Comment) CopyPropertyUtil.copyPropertiesFromRequest(request,
				Comment.class);
		news.setState("已审核");
		if("query".equals(method)){
			queryAll(request, response, news);
		}if("viewNews".equals(method)){
			view(request, response, news);
		}if("addComment".equals(method)){
			news.setContent("");
			newsService.addComment(news, com);
			view(request, response, news);
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
	public void view(HttpServletRequest request,
			HttpServletResponse response, News news) throws ServletException, IOException{
		news = (News) newsService.findAll(news).get(0);
		List<?> cList = newsService.findComment(news);
		request.setAttribute("news", news);
		request.setAttribute("cList", cList);
		request.getRequestDispatcher("/front/viewNews.jsp").forward(request,
				response);
	}
	@SuppressWarnings("unchecked")
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
		request.getRequestDispatcher("/front/content.jsp").forward(request,
				response);
	}	
}
