package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;


import com.po.NewsType;
import com.po.Role;
import com.po.User;
import com.utils.DBUtil;
import com.utils.Page;

public class NewsTypeDao {
	public List findAll(NewsType type){
		Connection conn = DBUtil.getConnection();
		ArrayList list= new ArrayList();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from newsType where 1 = 1 ");
		if(StringUtils.isNotBlank(type.getTypeName())){
			sb.append(" and typeName like '%"+type.getTypeName()+"%'");
		}
		ResultSet rs =  DBUtil.querySQL(sb.toString(), null,conn);
		try{
		while(rs.next()){
			NewsType temp = new NewsType();
			temp.setTypeId(rs.getInt(1));
			temp.setTypeName(rs.getString("typeName"));
			temp.setCreator(rs.getString("creator"));
			list.add(temp);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(rs, null, conn);
		}
		return list;
	}
	
	public boolean addType(NewsType type){
		String sql = "insert into newsType(typeName,creator) values(?,?)";
		Object[] obj = {type.getTypeName(),type.getCreator()};
		return DBUtil.updateSQL(sql, obj);
	}
	public boolean delType(NewsType type){
		String sql ="delete from newsType where typeId = ?";
		Object[] obj ={type.getTypeId()};
		return DBUtil.updateSQL(sql, obj);
	}
	public boolean updateType(NewsType type){
		String sql ="update newsType set typeName = ?, creator = ? where typeId = ?";
		Object[] obj = {type.getTypeName(),type.getCreator(),type.getTypeId()};
		return DBUtil.updateSQL(sql, obj);
	}
}
