package com.msun.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.msun.db.DBHelper;
import com.msun.entity.cms_article;

public class cms_articleDao {
	//添加
	public static boolean insert(int category_id,String title,String digist,String author,String date){
		Connection conn=null;
		PreparedStatement stmt=null;
		boolean flag = true;
				
		try
		{
			conn=DBHelper.getConnection();
			String sql = "insert into cms_article (category_id,title,digist,author,date) value(?,?,?,?,?)";
			stmt=conn.prepareStatement(sql);			
			stmt.setInt(1, category_id);
			stmt.setString(2, title);
			stmt.setString(3, digist);
			stmt.setString(4, author);
			stmt.setString(5, date);
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
	//更新新闻
	public static boolean update(int id,int category_id,String title,String digist,String author){
		Connection conn=null;
		PreparedStatement stmt=null;
		boolean flag = true;		
		try
		{
			conn=DBHelper.getConnection();
			String sql = "update cms_article set category_id='"+category_id+"', title = '"+title+"', digist = '"+digist+"', author='"+author+"' where id='"+id+"'";
			stmt=conn.prepareStatement(sql);
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
	public ArrayList<cms_article> getAllItems() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<cms_article> list = new ArrayList<cms_article>(); 
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from cms_article;"; 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				cms_article item = new cms_article();
				item.setId(rs.getInt("id"));
				item.setCategory_id(rs.getInt("category_id"));
				item.setTitle(rs.getString("title"));
				item.setDigist(rs.getString("digist"));
				item.setAuthor(rs.getString("author"));
				item.setDate(rs.getString("date"));
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

	// 查找相关信息
	public ArrayList<cms_article> getItemsByCategory(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<cms_article> list = new ArrayList<cms_article>(); 
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from cms_article where category_id="+id; 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				cms_article items = new cms_article();
				items.setId(rs.getInt("id"));
				items.setCategory_id(rs.getInt("category_id"));
				items.setTitle(rs.getString("title"));
				items.setDigist(rs.getString("digist"));
				items.setAuthor(rs.getString("author"));
				items.setDate(rs.getString("date"));
				list.add(items);
			}
			return list; 

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
	//查询文章详情
	public ArrayList<cms_article> getItemsById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<cms_article> list = new ArrayList<cms_article>(); 
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from cms_article where id="+id; 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				cms_article items = new cms_article();
				items.setId(rs.getInt("id"));
				items.setCategory_id(rs.getInt("category_id"));
				items.setTitle(rs.getString("title"));
				items.setDigist(rs.getString("digist"));
				items.setAuthor(rs.getString("author"));
				items.setDate(rs.getString("date"));
				list.add(items);
			}
			return list; 

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
					String sql="delete from cms_article where id="+id;
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
