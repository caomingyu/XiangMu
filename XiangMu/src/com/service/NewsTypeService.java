package com.service;

import java.util.List;

import com.dao.NewsTypeDao;
import com.po.NewsType;

public class NewsTypeService {
	private NewsTypeDao typeDao;
	public NewsTypeService(){
		typeDao = new NewsTypeDao();
	}
	public List findAll(NewsType type){
		return typeDao.findAll(type);
	}
	public boolean addType(NewsType type){
		return typeDao.addType(type);
	}
	public boolean delType(NewsType type){
		return typeDao.delType(type);
	}
	public boolean updateType(NewsType type){
		return typeDao.updateType(type);
	}
}
