package zyf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import zyf.dao.IUserDao;
import zyf.entity.User;
import zyf.utils.DBConnection;

public class Hb_UserDaoImpl implements IUserDao{

	@Override
	public int save(Connection conn, User user) throws Exception {
		String sql = "insert into user values(?,?,?,?)";
		return 0;
	}

	@Override
	public int update(Connection conn, User user) throws Exception {
		String sql = "update user set userid=?,username=?,password=?,txtcode=?";
		
		return 0;
	}

	@Override
	public int delete(Connection conn, User user) throws Exception {
		String sql = "delete from user where id=?";
		
		return 0;
	}

	@Override
	public User queryById(User user) throws Exception {
		String sql = "select * from user where userid=?";
		
		return null;
	}
	
	@Override
	public User queryByName(User user) throws Exception {
		String sql = "select * from user where username=?";
		
		return null;
	}

	@Override
	public List<User> query() throws Exception {
		String sql = "select * from user";
		return null;
	}

	
}
