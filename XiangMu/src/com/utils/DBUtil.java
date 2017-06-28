package com.utils;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
	private static String driver = "";
	private static String url = "";
	private static String user = "";
	private static String pwd = "";

	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static ComboPooledDataSource source = null;

	static {
		init();
		source = new ComboPooledDataSource();
		try {
			source.setDriverClass(driver);
			source.setJdbcUrl(url);
			source.setUser(user);
			source.setPassword(pwd);
			source.setMaxPoolSize(100);
			source.setMaxIdleTime(500);
			source.setInitialPoolSize(20);
			source.setMinPoolSize(5);
		} catch (PropertyVetoException e) {
			System.out.println("");
			e.printStackTrace();
		}
	}

	
	private static void init() {
		Properties p = new Properties();
		try {
			p.load(DBUtil.class.getClassLoader().getResourceAsStream(
					"dbInfo.properties"));
			driver = p.getProperty("DRIVER");
			user = p.getProperty("USER");
			pwd = p.getProperty("PASSWORD");
			url = p.getProperty("URL");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static Connection getConnection() {
		try {
			conn = source.getConnection();
		} catch (SQLException e) {
			System.out.println("");
			e.printStackTrace();
		}
		return conn;
	}


	public static boolean updateSQL(String sql, Object obj[]) {
		conn = getConnection();
		boolean flag = false;
		if (sql != null || !"".equals(sql)) {
			try {
				ps = conn.prepareStatement(sql);
				if (obj != null) {
					for (int i = 0; i < obj.length; i++) {
						ps.setObject(i + 1, obj[i]);
					}
				}
				if (ps.executeUpdate() > 0) {
					flag = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeAll(null, ps, conn);
			}
		}
		return flag;
	}

	
	public static ResultSet querySQL(String sql, Object obj[],Connection conn) {
//		conn = getConnection();
		if (sql != null || !"".equals(sql)) {
			try {
				ps = conn.prepareStatement(sql);
				if (obj != null) {
					for (int i = 0; i < obj.length; i++) {
						ps.setObject(i + 1, obj[i]);
					}
				}
				rs = ps.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}

	public static void closeAll(ResultSet rs, PreparedStatement ps,
			Connection conn) {

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DBUtil();
		System.out.println(getConnection());
	}

}
