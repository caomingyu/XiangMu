package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;


import com.po.User;
import com.utils.DBUtil;
import com.utils.Page;

public class UserDao {
	public User checkUser(User user){
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from user where 1 = 1 ");
		if(StringUtils.isNotBlank(user.getUserName())){
			sb.append(" and userName = '"+user.getUserName()+"'");
		}if(StringUtils.isNotBlank(user.getPassword())){
			sb.append(" and password = '"+user.getPassword()+"'");
		}
		ResultSet rs = DBUtil.querySQL(sb.toString(), null,conn);
		user = new User();
		try {
			if(rs.next()){
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return user;
	}
	
	public List findAll(User user,Page page){
		Connection conn = DBUtil.getConnection();
		ArrayList list= new ArrayList();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from user where 1 = 1 ");
		if(StringUtils.isNotBlank(user.getUserName())){
			sb.append(" and userName like '%"+user.getUserName()+"%'");
		}if(StringUtils.isNotBlank(user.getRole())){
			sb.append(" and role like '%"+user.getRole()+"%'");
		}
		sb.append(" limit "+page.getStart()+","+page.getPageCount());
		ResultSet rs =  DBUtil.querySQL(sb.toString(), null,conn);
		try{
		while(rs.next()){
			User temp = new User();
			temp.setUserId(rs.getInt(1));
			temp.setUserName(rs.getString(2));
			temp.setPassword(rs.getString(3));
			temp.setRole(rs.getString(4));
			list.add(temp);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return list;
	}
	public List findAll(User user){
		Connection conn = DBUtil.getConnection();
		ArrayList list= new ArrayList();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from user where 1 = 1 ");
		if(StringUtils.isNotBlank(user.getUserName())){
			sb.append(" and userName like '%"+user.getUserName()+"%'");
		}if(StringUtils.isNotBlank(user.getRole())){
			sb.append(" and role like '%"+user.getRole()+"%'");
		}
		ResultSet rs =  DBUtil.querySQL(sb.toString(), null,conn);
		try{
		while(rs.next()){
			User temp = new User();
			temp.setUserId(rs.getInt(1));
			temp.setUserName(rs.getString(2));
			temp.setPassword(rs.getString(3));
			temp.setRole(rs.getString(4));
			list.add(temp);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return list;
	}
	public User findUser(User user){
		Connection conn = DBUtil.getConnection();
		String sql ="select * from user where userId = ?";
		ResultSet rs = DBUtil.querySQL(sql, new Object[]{user.getUserId()},conn);
		try {
			if(rs.next()){
				user.setUserName(rs.getString("UserName"));
				user.setPassword(rs.getString("Password"));
				user.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return user;
	}
	public boolean addUser(User user){
		String sql = "insert into user(username,password,role) values(?,?,?)";
		Object[] obj = {user.getUserName(),user.getPassword(),user.getRole()};
		return DBUtil.updateSQL(sql, obj);
	}
	public boolean delUser(User user){
		String sql ="delete from user where userId = ?";
		Object[] obj ={user.getUserId()};
		return DBUtil.updateSQL(sql, obj);
	}
	public boolean updateUser(User user){
		String sql ="update user set userName = ?, password = ?,role = ? where userId = ?";
		Object[] obj = {user.getUserName(),user.getPassword(),user.getRole(),user.getUserId()};
		return DBUtil.updateSQL(sql, obj);
	}
	public String roleType(User user){
		Connection conn = DBUtil.getConnection();
		String roleType = "匿名";
		String sql = "select * from role where roleId = ?";
		ResultSet rs = DBUtil.querySQL(sql, new Object[]{user.getRole()},conn);
		try {
			if(rs.next()){
				roleType = rs.getString("roleName");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return roleType;
	}
}
