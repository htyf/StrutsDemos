package zyf.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库连接的工具类
 * @author zyf
 */
public class DBConnection {
	private static  String username;
	private static  String password;
	private static  String driver;
	private static  String url;
	
	static{
		Properties p = new Properties();
		String path = DBConnection.class.getClassLoader().getResource("/").getPath();
		try {
			p.load(new FileInputStream(new File(path+"db.properties")));
			username = p.getProperty("username","root");
			password = p.getProperty("password","soysouce");
			driver = p.getProperty("driver","com.mysql.jdbc.Driver");
			url = p.getProperty("url","jdbc:mysql://localhost:3306/ebuy");
		} catch (FileNotFoundException e) {
			System.out.println("没有找到指定的文件");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		//加载数据库驱动
		Class.forName(driver);
		//返回数据库连接
		return DriverManager.getConnection(url, username, password);
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void close(Connection conn,Statement ps,ResultSet rs){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("连接关闭失败");
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println("对象关闭失败");
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("结果集关闭失败");
				e.printStackTrace();
			}
		}
	}
}
