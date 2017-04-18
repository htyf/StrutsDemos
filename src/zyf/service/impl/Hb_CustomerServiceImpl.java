package zyf.service.impl;

import java.sql.Connection;
import java.util.List;

import zyf.dao.ICustomerDao;
import zyf.dao.impl.CustomerDaoImpl;
import zyf.dao.impl.Hb_CustomerDaoImpl;
import zyf.entity.Customer;
import zyf.service.ICustomerService;
import zyf.utils.DBConnection;

public class Hb_CustomerServiceImpl implements ICustomerService{
//	ICustomerDao icd = new CustomerDaoImpl();
	ICustomerDao icd = new Hb_CustomerDaoImpl();



	@Override
	public Customer addCustomer(Customer cust) throws Exception {
		if(cust==null){
			return null;
		}
		Customer customer = icd.queryByName(cust);
		if(customer==null){
			/*Connection conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			int save = icd.save(conn, cust);*/
			int save = icd.save(null, cust);//返回的是id
			if(save>0){
//				conn.commit();
				Customer c = icd.queryByName(cust);
				return c;
			}else{
//				conn.rollback();
				return null;
			}
		}else{
			return null;
		}
	
	}
	/**
	 * 判断用户是否存在
	 * @param cust
	 * @return
	 * @throws Exception
	 */
	public Customer isExist(Customer cust) throws Exception{
		if(cust==null){
			return null;
		}
		Customer customer = icd.queryByName(cust);
		if(customer==null){
			return null;
		}else{
			return customer;
		}
		
	}
	@Override
	public List<Customer> getAllCustomer() throws Exception {
		// TODO Auto-generated method stub
		return icd.query();
	}
	@Override
	public Customer getCustomerByName(Customer cust) throws Exception {
		// TODO Auto-generated method stub
		return icd.queryByName(cust);
	}
	@Override
	public List<Customer> getAllCustomerByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return icd.queryByName(name);
	}
	
	@Override
	public int deleteCustomer(Customer cust) throws Exception {
		// TODO Auto-generated method stub
		return icd.delete(null, cust);
	}
	@Override
	public int updateCustomer(Customer cust) throws Exception {
		// TODO Auto-generated method stub
		return icd.update(null, cust);
	}
	@Override
	public Customer getCustomerById(Customer cust) throws Exception {
		// TODO Auto-generated method stub
		return icd.queryById(cust);
	}
	
	
	
}
