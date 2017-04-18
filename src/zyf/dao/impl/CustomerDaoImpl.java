package zyf.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import zyf.dao.ICustomerDao;
import zyf.dao.IUserDao;
import zyf.entity.Customer;
import zyf.entity.User;
import zyf.utils.DBConnection;

public class CustomerDaoImpl implements ICustomerDao{

	@Override
	public int save(Connection conn, Customer cust) throws Exception {
		String sql = "insert into customer values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cust.getCustName());
		ps.setString(2, cust.getCustLevel());
		ps.setString(3, cust.getCustSource());
		ps.setString(4, cust.getCustLinkman());
		ps.setString(5, cust.getCustPhone());
		ps.setString(6, cust.getCustMobile());
		ps.setString(7, cust.getCustAddress());
		ps.setString(8, cust.getCustZip());
		ps.setString(9, cust.getCustFax());
		ps.setString(10, cust.getCustWebsite());
		return ps.executeUpdate();
	}

	@Override
	public int update(Connection conn, Customer cust) throws Exception {
		String sql = "update customer set custName=?,custLevel=?,custSource=?,custLinkman=?,custPhone=?,custMobile=?,custAddress=?,custZip=?,custFax=?,custWebsite=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cust.getCustName());
		ps.setString(2, cust.getCustLevel());
		ps.setString(3, cust.getCustSource());
		ps.setString(4, cust.getCustLinkman());
		ps.setString(5, cust.getCustPhone());
		ps.setString(6, cust.getCustMobile());
		ps.setString(7, cust.getCustAddress());
		ps.setString(8, cust.getCustZip());
		ps.setString(9, cust.getCustFax());
		ps.setString(10, cust.getCustWebsite());
		return ps.executeUpdate();
	}

	@Override
	public int delete(Connection conn, Customer cust) throws Exception {
		String sql = "delete from customer where custName=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cust.getCustName());
		return ps.executeUpdate();
	}

	@Override
	public Customer queryById(Customer cust) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer queryByName(Customer cust) throws Exception {
		Connection conn = DBConnection.getConnection();
		String sql = "select * from customer where custName=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cust.getCustName());
		ResultSet rs = ps.executeQuery();
		Customer c=null;
		if(rs.next()){
			c=new Customer();
			c.setCustName(rs.getString(1));
			c.setCustLevel(rs.getString(1));
			c.setCustSource(rs.getString(1));
			c.setCustLinkman(rs.getString(1));
			c.setCustPhone(rs.getString(1));
			c.setCustMobile(rs.getString(1));
			c.setCustAddress(rs.getString(1));
			c.setCustZip(rs.getString(1));
			c.setCustFax(rs.getString(1));
			c.setCustWebsite(rs.getString(1));
		}
		return c;
	}

	@Override
	public List<Customer> query() throws Exception {
		List<Customer> custlist = new ArrayList<>();
		Connection conn = DBConnection.getConnection();
		String sql = "select * from customer";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Customer c = new Customer();
			c.setCustName(rs.getString(1));
			c.setCustLevel(rs.getString(1));
			c.setCustSource(rs.getString(1));
			c.setCustLinkman(rs.getString(1));
			c.setCustPhone(rs.getString(1));
			c.setCustMobile(rs.getString(1));
			c.setCustAddress(rs.getString(1));
			c.setCustZip(rs.getString(1));
			c.setCustFax(rs.getString(1));
			c.setCustWebsite(rs.getString(1));
			custlist.add(c);
		}
		return custlist;
	}

	@Override
	public List<Customer> queryByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}


