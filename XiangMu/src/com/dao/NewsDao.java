package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.msun.db.DBHelper;
import com.msun.entity.cms_category;
import com.po.Comment;
import com.po.News;
import com.po.NewsType;
import com.po.User;
import com.utils.DBUtil;
import com.utils.Page;
import com.utils.TimeUtil;

public class NewsDao {
	
	
	public ArrayList<News> getAllItems() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<News> list = new ArrayList<News>(); 
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from news;"; 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				News item = new News();
				item.setNewsId(rs.getInt("newsId"));
				item.setTitle(rs.getString("title"));
				item.setContent(rs.getString("content"));
				item.setFirstTime(rs.getString("firstTime"));
				item.setAuthor(rs.getString("author"));
				item.setTypeId(rs.getString("typeId"));
				
				item.setState(rs.getString("state"));
				
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
	
	public List findAll(News news,Page page){
		Connection conn = DBUtil.getConnection();
		ArrayList list= new ArrayList();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from news where 1 = 1 ");
		if(StringUtils.isNotBlank(news.getTypeId())){
			sb.append(" and typeId = '"+news.getTypeId()+"'");
		}if(StringUtils.isNotBlank(news.getTitle())){
			sb.append(" and title like '%"+news.getTitle()+"%'");
		}if(StringUtils.isNotBlank(news.getContent())){
			sb.append(" and content like '%"+news.getContent()+"%'");
		}if(StringUtils.isNotBlank(news.getAuthor())){
			sb.append(" and author like '%"+news.getAuthor()+"%'");
		}if(StringUtils.isNotBlank(news.getState())){
			sb.append(" and state = '"+news.getState()+"'");
		}
		sb.append(" limit "+page.getStart()+","+page.getPageCount());
		ResultSet rs =  DBUtil.querySQL(sb.toString(), null,conn);
		try{
		while(rs.next()){
			News temp = new News();
			temp.setNewsId(rs.getInt(1));
			temp.setTitle(rs.getString("title"));
			temp.setContent(rs.getString("content"));
			temp.setFirstTime(rs.getString("firstTime"));
			temp.setTypeId(rs.getString("typeId"));
			temp.setAuthor(rs.getString("author"));
			temp.setState(rs.getString("state"));
			list.add(temp);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return list;
	}
	public List findAll(News news){
		Connection conn = DBUtil.getConnection();
		ArrayList list= new ArrayList();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from news where 1 = 1 ");
		if(news.getNewsId()>0){
			sb.append(" and newsId = '"+news.getNewsId()+"'");
		}
		if(StringUtils.isNotBlank(news.getTypeId())){
			sb.append(" and typeId = '"+news.getTypeId()+"'");
		}if(StringUtils.isNotBlank(news.getTitle())){
			sb.append(" and title like '%"+news.getTitle()+"%'");
		}if(StringUtils.isNotBlank(news.getContent())){
			sb.append(" and content like '%"+news.getContent()+"%'");
		}if(StringUtils.isNotBlank(news.getAuthor())){
			sb.append(" and author like '%"+news.getAuthor()+"%'");
		}if(StringUtils.isNotBlank(news.getState())){
			sb.append(" and state = '"+news.getState()+"'");
		}
		ResultSet rs =  DBUtil.querySQL(sb.toString(), null,conn);
		try{
		while(rs.next()){
			News temp = new News();
			temp.setNewsId(rs.getInt(1));
			temp.setTitle(rs.getString("title"));
			temp.setContent(rs.getString("content"));
			temp.setFirstTime(rs.getString("firstTime"));
			temp.setTypeId(rs.getString("typeId"));
			temp.setAuthor(rs.getString("author"));
			temp.setState(rs.getString("state"));
			list.add(temp);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, null);
		}
		return list;
	}	
	public boolean addNews(News news){
		news.setState("未审核");
		news.setFirstTime(TimeUtil.getTimeString());
		String sql = "insert into news(title,content,firstTime,typeId,author,state) values(?,?,?,?,?,?)";
		Object[] obj = {news.getTitle(),news.getContent(),news.getFirstTime(),news.getTypeId(),news.getAuthor(),news.getState()};
		return DBUtil.updateSQL(sql, obj);
	}
	public boolean delNews(News news){
		String sql ="delete from news where newsId = ?";
		return DBUtil.updateSQL(sql, new Object[]{news.getNewsId()});
	}
	public boolean updateNews(News news){
		String sql ="update news set title = ?,content = ?,typeId= ? where newsId = ?";
		return DBUtil.updateSQL(sql, new Object[]{news.getTitle(),news.getContent(),news.getTypeId(),news.getNewsId()});
	}
	public String findType(News news){
		Connection conn = DBUtil.getConnection();
		NewsType type = new NewsType();
		String sql ="select * from newsType where typeId = ?";
		ResultSet rs =  DBUtil.querySQL(sql, new Object[]{news.getTypeId()},conn);
		try {
			if(rs.next()){
				type.setTypeName(rs.getString("typeName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return type.getTypeName();
	}
	public boolean updateState(News news,String state){
		String sql ="update news set state = ? where newsId = ?";
		return DBUtil.updateSQL(sql, new Object[]{state,news.getNewsId()});
	}
	//查找该文章的评论
	public List findComment(News news){
		Connection conn = DBUtil.getConnection();
		List list = new ArrayList();
		String sql ="select * from comment where newsId = ?";
		ResultSet rs = DBUtil.querySQL(sql, new Object[]{news.getNewsId()},conn);
		try {
			while(rs.next()){
				Comment comment = new Comment();
				comment.setCommentId(rs.getInt(1));
				comment.setContent(rs.getString("content"));
				comment.setPTime(rs.getString("pTime"));
				comment.setNewsId(news.getNewsId()+"");
				comment.setName(rs.getString("name"));
				list.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, null);
		}
		return list;
	}
	public void addComment(News news,Comment com){
		com.setPTime(TimeUtil.getTimeString());
		String sql ="insert into comment(content,name,pTime,newsId) values(?,?,?,?)";
		DBUtil.updateSQL(sql, new Object[]{com.getContent(),com.getName(),com.getPTime(),news.getNewsId()});
	}
}
