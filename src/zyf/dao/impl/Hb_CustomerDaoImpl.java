package zyf.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import zyf.dao.ICustomerDao;
import zyf.dao.IUserDao;
import zyf.entity.Customer;
import zyf.entity.User;
import zyf.utils.DBConnection;

public class Hb_CustomerDaoImpl implements ICustomerDao{

	@Override
	public int save(Connection conn, Customer cust) throws Exception {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		int status = 0;
		try{
			//加载默认配置文件
			Configuration cfg = new Configuration().configure();
			//创建会话工厂
			sessionFactory = cfg.buildSessionFactory();
			//通过会话工厂获取session
			session = sessionFactory.openSession();
			//开启事务
			transaction = session.beginTransaction();
			Serializable save = session.save(cust);
			status=1;
			System.out.println("保存成功："+save);
			transaction.commit();
		}catch(Exception e){
			status=0;
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
			sessionFactory.close();
			System.out.println("***zyf.dao.impl.Hb_CustomerDaoImpl.save(Connection, Customer)***");
		}

		return status;
	}

	@Override
	public int update(Connection conn, Customer cust) throws Exception {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try{
			//加载默认配置文件
			Configuration cfg = new Configuration().configure();
			//创建会话工厂
			sessionFactory = cfg.buildSessionFactory();
			//通过会话工厂获取session
			session = sessionFactory.openSession();
			//开启事务
			transaction = session.beginTransaction();
			//执行更新
			session.update(cust);
			transaction.commit();
		}catch(Exception  e){
			transaction.rollback();
			e.printStackTrace();
			return 0;
		}finally{
			session.close();
			sessionFactory.close();
			System.out.println("***zyf.dao.impl.Hb_CustomerDaoImpl.update(Connection, Customer)***");
		}
		return 1;

	}

	@Override
	public int delete(Connection conn, Customer cust) throws Exception {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;
		try{
			//加载默认配置文件
			Configuration cfg = new Configuration().configure();
			//创建会话工厂
			sessionFactory = cfg.buildSessionFactory();
			//通过会话工厂获取session
			session = sessionFactory.openSession();
			//开启事务
			transaction = session.beginTransaction();
			Customer customer = queryById(cust);
			if(customer==null){
				return 0;
			}
			session.delete(customer);
			transaction.commit();
		}catch(Exception  e){
			transaction.rollback();
			e.printStackTrace();
			return 0;
		}finally{
			session.close();
			sessionFactory.close();
			System.out.println("***zyf.dao.impl.Hb_CustomerDaoImpl.delete(Connection, Customer)***");
		}
		return 1;

	}

	@Override
	public Customer queryById(Customer cust) throws Exception {
		SessionFactory sessionFactory = null;
		Session session = null;
		Customer customer = null;
		try{
		//加载默认配置文件
		Configuration cfg = new Configuration().configure();
		//创建会话工厂
		 sessionFactory = cfg.buildSessionFactory();
		//通过会话工厂获取session
		 session = sessionFactory.openSession();
		//执行查询
		//创建Query对象,传入HQL语句
		/*
		 * sql:select * from t_user
		 * hql:1.如果查询的是所有数据  * 那么select * 必须省略
		 * 	   2.查询的不是表，而是类名  不是表的列名而是类的属性名
		 * 	   3.如果条件是可变的，一定要在两边加上单引号
		 */
		Query query = session.createQuery("from Customer where custId='"+cust.getCustId()+"'");
		List<Customer> custList = query.list();
		if(custList.size()==0){
			return null;
		}
		 customer = custList.get(0);
		}finally{
			session.close();
			sessionFactory.close();
			System.out.println("***zyf.dao.impl.Hb_CustomerDaoImpl.queryById(Customer)***");
		}
		return customer;

	}

	@Override
	public Customer queryByName(Customer cust) throws Exception {
		//加载默认配置文件
		Configuration cfg = new Configuration().configure();
		//创建会话工厂
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		//通过会话工厂获取session
		Session session = sessionFactory.openSession();
		//执行查询
		Query query = session.createQuery("from Customer where custName='"+cust.getCustName()+"'");
		List<Customer> custList = query.list();
		System.out.println("custList="+custList);
		if(custList.size()==0){
			return null;
		}
		Customer customer = custList.get(0);
		
		session.close();
		sessionFactory.close();
		System.out.println("***zyf.dao.impl.Hb_CustomerDaoImpl.queryByName(Customer)***");
		return customer;

	}

	@Override
	public List<Customer> query() throws Exception {
		//加载默认配置文件
		Configuration cfg = new Configuration().configure();
		//创建会话工厂
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		//通过会话工厂获取session
		Session session = sessionFactory.openSession();
		//执行查询
		Query query = session.createQuery("from Customer");
		List<Customer> custList = query.list();
		
		session.close();
		sessionFactory.close();
		System.out.println("***zyf.dao.impl.Hb_CustomerDaoImpl.query()***");
		return custList;

	}

	@Override
	public List<Customer> queryByName(String name) throws Exception {
		//加载默认配置文件
		Configuration cfg = new Configuration().configure();
		//创建会话工厂
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		//通过会话工厂获取session
		Session session = sessionFactory.openSession();
		//执行查询
		Query query = session.createQuery("from Customer where custName like '%"+name+"%'");
		List<Customer> custList = query.list();
		session.close();
		sessionFactory.close();
		System.out.println("***zyf.dao.impl.Hb_CustomerDaoImpl.queryByName(String)***");
		return custList;

	}


}


