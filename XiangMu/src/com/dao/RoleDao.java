package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;


import com.po.Role;
import com.po.User;
import com.utils.DBUtil;
import com.utils.Page;

public class RoleDao {
//	public User checkUser(Role role){
//		String sql = "select * from role where roleName='"+role.getRoleName()+"'";
//		ResultSet rs = DBUtil.querySQL(sql, null);
//		role = new Role();
//		try {
//			if(rs.next()){
//				role.setRoleId(rs.getInt("roleId"));
//				role.setRoleName(rs.getString("roleName"));
//				role.setDescription(rs.getString("description"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			DBUtil.closeAll(rs, null, null);
//		}
//		return role;
//	}
	
	public List findAll(Role role,Page page){
		Connection conn = DBUtil.getConnection();
		ArrayList list= new ArrayList();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from role where 1 = 1 ");
		if(StringUtils.isNotBlank(role.getRoleName())){
			sb.append(" and roleName like '%"+role.getRoleName()+"%'");
		}
		sb.append(" limit "+page.getStart()+","+page.getPageCount());
		ResultSet rs =  DBUtil.querySQL(sb.toString(), null,conn);
		try{
		while(rs.next()){
			Role temp = new Role();
			temp.setRoleId(rs.getInt("roleId"));
			temp.setRoleName(rs.getString("roleName"));
			temp.setDescription(rs.getString("description"));
			list.add(temp);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return list;
	}
	public List findAll(Role role){
		Connection conn = DBUtil.getConnection();
		ArrayList list= new ArrayList();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from role where 1 = 1 ");
		if(StringUtils.isNotBlank(role.getRoleName())){
			sb.append(" and roleName like '%"+role.getRoleName()+"%'");
		}
		ResultSet rs =  DBUtil.querySQL(sb.toString(), null,conn);
		try{
		while(rs.next()){
			Role temp = new Role();
			temp.setRoleId(rs.getInt("roleId"));
			temp.setRoleName(rs.getString("roleName"));
			temp.setDescription(rs.getString("description"));
			list.add(temp);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return list;
	}
	public Role checkRole(Role role){
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from role where 1 = 1 ");
		if(StringUtils.isNotBlank(role.getRoleName())){
			sb.append(" and roleName = '"+role.getRoleName()+"'");
		}
		ResultSet rs = DBUtil.querySQL(sb.toString(), null,conn);
		role = new Role();
		try {
			if(rs.next()){
				role.setRoleId(rs.getInt("roleId"));
				role.setRoleName(rs.getString("roleName"));
				role.setDescription(rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return role;
	}
	public Role findRole(Role role){
		Connection conn = DBUtil.getConnection();
		String sql ="select * from role where roleId = ?";
		ResultSet rs = DBUtil.querySQL(sql, new Object[]{role.getRoleId()},conn);
		try {
			if(rs.next()){
				role.setRoleId(rs.getInt("roleId"));
				role.setRoleName(rs.getString("roleName"));
				role.setDescription(rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return role;
	}
	public boolean addRole(Role role){
		String sql = "insert into role(roleName,description) values(?,?)";
		Object[] obj = {role.getRoleName(),role.getDescription()};
		return DBUtil.updateSQL(sql, obj);
	}
	public boolean delRole(Role role){
		String sql ="delete from role where roleId = ?";
		Object[] obj ={role.getRoleId()};
		return DBUtil.updateSQL(sql, obj);
	}
	public boolean updateRole(Role role){
		String sql ="update role set roleName = ?, description = ? where roleId = ?";
		Object[] obj = {role.getRoleName(),role.getDescription(),role.getRoleId()};
		return DBUtil.updateSQL(sql, obj);
	}
	public List queryUser(Role role){
		Connection conn = DBUtil.getConnection();
		String sql ="select * from user where role = ?";
		List list = new ArrayList();
		ResultSet rs = DBUtil.querySQL(sql, new Object[]{role.getRoleId()},conn);
		try {
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return list;
	}
	public List selectUser(){
		Connection conn = DBUtil.getConnection();
		String sql ="select * from user where role =''";
		List list = new ArrayList();
		ResultSet rs = DBUtil.querySQL(sql, null,conn);
		try {
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return list;
	}
	public void addUser(String str,Role role){
		List list = null;
		String temp[]=str.split(",");
		String sql="";
		for(String userId:temp){
			sql ="update user set role = ? where userId = ?";
			DBUtil.updateSQL(sql, new Object[]{role.getRoleId(),userId});
		}
	}
	//移除组成员
	public void delUser(String str,Role role){
		List list = null;
		String temp[]=str.split(",");
		String sql="";
		for(String userId:temp){
			sql ="update user set role = ? where userId = ?";
			DBUtil.updateSQL(sql, new Object[]{null,userId});
		}
	}	
}
