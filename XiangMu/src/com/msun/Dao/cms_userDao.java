package com.msun.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.msun.db.DBHelper;
import com.msun.entity.cms_user;

public class cms_userDao {
	public static boolean insert(String name,String psw){
		Connection conn=null;
		PreparedStatement stmt=null;
		boolean flag = true;
				
		try
		{
			conn=DBHelper.getConnection();
			String sql = "insert into cms_user (name,psw) value(?,?)";
			stmt=conn.prepareStatement(sql);			
			stmt.setString(1, name);
			stmt.setString(2, psw);		
			stmt.executeUpdate();
		}
		catch(Exception ex){
			ex.printStackTrace();			
		}
		finally
		{
			if(stmt!=null)
			{
				try
				{
					stmt.close();
					stmt=null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		return flag;
	}

	// 查找所有
	public ArrayList<cms_user> getAllItems() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<cms_user> list = new ArrayList<cms_user>(); 
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from cms_user;"; 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				cms_user item = new cms_user();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setPsw(rs.getString("psw"));
				list.add(item);
			}
			return list; 
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	// 查找一条信息
	public cms_user getItemsByName(String name) {
		Connection conn=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		try
		{
			conn=DBHelper.getConnection();
			String sql="select * from cms_user where name=?;";
			stmt=conn.prepareStatement(sql);
			stmt.setString(1,name);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				cms_user item=new cms_user();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setPsw(rs.getString("psw"));
				return item;
			}else{
			return null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			// 
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
	}
	//根据id删除一条数据
			public static boolean delete(int id){
				Connection conn=null;
				PreparedStatement stmt=null;
				boolean flag=true;
				
				try
				{
					conn=DBHelper.getConnection();
					String sql="delete from cms_user where id="+id;
					stmt=conn.prepareStatement(sql);
					stmt.executeUpdate();
					
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				finally
				{
					
					if(stmt!=null)
					{
						try
						{
							stmt.close();
							stmt=null;
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
					}
				}
				return flag;
			}
			
}