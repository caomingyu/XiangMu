package com.msun.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.msun.db.DBHelper;
import com.msun.entity.cms_category;

public class cms_categoryDao {
//添加
		public static boolean insert(String category_name){
			Connection conn=null;
			PreparedStatement stmt=null;
			boolean flag = true;
					
			try
			{
				conn=DBHelper.getConnection();
				String sql = "insert into cms_category (category_name) value(?)";
				stmt=conn.prepareStatement(sql);			
				stmt.setString(1, category_name);	
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
		public ArrayList<cms_category> getAllItems() {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			ArrayList<cms_category> list = new ArrayList<cms_category>(); 
			try {
				conn = DBHelper.getConnection();
				String sql = "select * from cms_category;"; 
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					cms_category item = new cms_category();
					item.setId(rs.getInt("id"));
					item.setCategory_name(rs.getString("category_name"));
					item.setParent(rs.getString("parent"));
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
		//根据id删除一条数据
		public static boolean delete(int id){
			Connection conn=null;
			PreparedStatement stmt=null;
			boolean flag=true;
			
			try
			{
				conn=DBHelper.getConnection();
				String sql="delete from cms_category where id="+id;
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
