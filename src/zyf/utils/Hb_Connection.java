package zyf.utils;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import zyf.entity.Customer;

public class Hb_Connection {
	public static void main(String[] args) {
		Session session = getConnection();
		Transaction transaction = session.beginTransaction();
		Customer customer = new Customer("娜娜", "2", "58同城", "火舞二号", 
				"15623451874", "16723412432", "杭州市上城区", 
				"445789", "235667", "www.huowu.com");
		int save = (int)session.save(customer);
		transaction.commit();
		close(null, session);
		System.out.println(save);
	}

	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Session getConnection() {
		//加载默认配置文件
		Configuration cfg = new Configuration().configure();
		//创建会话工厂
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		//通过会话工厂获取session
		Session openSession = sessionFactory.openSession();
		return openSession;
	}
	
	/**
	 * 关闭资源
	 * @param sf
	 * @param session
	 */
	public static void close(SessionFactory sf , Session session){
		if(sf!=null){
			sf.close();
		}
		if(session!=null){
			session.close();
		}
	}

}
