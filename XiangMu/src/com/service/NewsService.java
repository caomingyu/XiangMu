package com.service;

import java.util.List;

import com.dao.NewsDao;
import com.po.Comment;
import com.po.News;
import com.po.NewsType;
import com.utils.Page;

public class NewsService {
	private NewsDao newsDao;
	public NewsService(){
		newsDao = new NewsDao();
	}
	public List findAll(News news,Page page){
		return newsDao.findAll(news, page);
	}
	public List findAll(News news){
		return newsDao.findAll(news);
	}
	public boolean addNews(News news){
		return newsDao.addNews(news);
	}
	public String findType(News news){
		return newsDao.findType(news);
	}
	public boolean delNews(News news){
		return newsDao.delNews(news);
	}
	public boolean updateNews(News news){
		return newsDao.updateNews(news);
	}
	public boolean updateState(News news,String state){
		return newsDao.updateState(news, state);
	}
	public List findComment(News news){
		return newsDao.findComment(news);
	}
	public void addComment(News news,Comment com){
		newsDao.addComment(news, com);
	}
}
