package com.service;

import java.util.List;

import com.dao.RoleDao;
import com.po.Role;
import com.utils.Page;

public class RoleService {
	private RoleDao roleDao;
	public RoleService(){
		roleDao = new RoleDao();
	}

	public List findAll(Role role,Page page){
		return roleDao.findAll(role,page);
	}
	public List findAll(Role role){
		return roleDao.findAll(role);
	}
	public boolean addRole(Role role){
		return roleDao.addRole(role);
	}
	public boolean delRole(Role role){
		return roleDao.delRole(role);
	}
	public Role findRole(Role role){
		return roleDao.findRole(role);
	}
	public boolean updateRole(Role role){
		return roleDao.updateRole(role);
	}
	public Role checkRole(Role role){
		return roleDao.checkRole(role);
	}
	//查询角色成员
	public List queryUser(Role role){
		return roleDao.queryUser(role);
	}
	//查询没有角色的成员
	public List selectUser(){
		return roleDao.selectUser();
	}
	//添加组成员
	public void addUser(String str,Role role){
		roleDao.addUser(str, role);
	}
	//移除组成员
	public void delUser(String str,Role role){
		roleDao.delUser(str, role);
	}
}
