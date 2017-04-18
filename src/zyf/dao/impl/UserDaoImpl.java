package zyf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import zyf.dao.IUserDao;
import zyf.entity.User;
import zyf.utils.DBConnection;

public class UserDaoImpl implements IUserDao{

	@Override
	public int save(Connection conn, User user) throws Exception {
		String sql = "insert into user values(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUserid());
		ps.setString(2, user.getUsername());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getTxtcode());
		return ps.executeUpdate();
	}

	@Override
	public int update(Connection conn, User user) throws Exception {
		String sql = "update user set userid=?,username=?,password=?,txtcode=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUserid());
		ps.setString(2, user.getUsername());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getTxtcode());
		return ps.executeUpdate();
	}

	@Override
	public int delete(Connection conn, User user) throws Exception {
		String sql = "delete from user where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUserid());
		return ps.executeUpdate();
	}

	@Override
	public User queryById(User user) throws Exception {
		Connection conn = DBConnection.getConnection();
		String sql = "select * from user where userid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUserid());
		ResultSet rs = ps.executeQuery();
		User u=null;
		if(rs.next()){
			u = new User();
			u.setUserid(rs.getString(1));
			u.setUsername(rs.getString(2));
			u.setPassword(rs.getString(3));
			u.setTxtcode(rs.getString(4));
		}
		return u;
	}
	
	@Override
	public User queryByName(User user) throws Exception {
		Connection conn = DBConnection.getConnection();
		String sql = "select * from user where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUsername());
		ResultSet rs = ps.executeQuery();
		User u=null;
		if(rs.next()){
			u = new User();
			u.setUserid(rs.getString(1));
			u.setUsername(rs.getString(2));
			u.setPassword(rs.getString(3));
			u.setTxtcode(rs.getString(4));
		}
		return u;
	}

	@Override
	public List<User> query() throws Exception {
		List<User> userlist = new ArrayList<>();
		Connection conn = DBConnection.getConnection();
		String sql = "select * from user";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			User u = new User();
			u.setUserid(rs.getString(1));
			u.setUsername(rs.getString(2));
			u.setPassword(rs.getString(3));
			u.setTxtcode(rs.getString(4));
			userlist.add(u);
		}
		return userlist;
	}

	
}
