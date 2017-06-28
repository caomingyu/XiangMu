package com.service;

import java.util.List;

import com.dao.UserDao;
import com.po.User;
import com.utils.Page;

public class UserService {
	private UserDao userDao;
	public UserService(){
		userDao = new UserDao();
	}
	public User checkUser(User user){
		return userDao.checkUser(user);
	}
	public List findAll(User user,Page page){
		return userDao.findAll(user,page);
	}
	public List findAll(User user){
		return userDao.findAll(user);
	}
	public boolean addUser(User user){
		return userDao.addUser(user);
	}
	public boolean delUser(User user){
		return userDao.delUser(user);
	}
	public User findUser(User user){
		return userDao.findUser(user);
	}
	public boolean updateUser(User user){
		return userDao.updateUser(user);
	}
	public String getRoleType(User user){
		return userDao.roleType(user);
	}
}
