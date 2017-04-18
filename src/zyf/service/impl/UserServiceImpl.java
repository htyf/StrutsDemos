package zyf.service.impl;

import zyf.dao.IUserDao;
import zyf.dao.impl.UserDaoImpl;
import zyf.entity.User;
import zyf.service.IUserService;

public class UserServiceImpl implements IUserService{
	IUserDao iud = new UserDaoImpl();

	@Override
	public User login(User user) throws Exception {
		if(user==null){
			return null;
		}
		User u = iud.queryByName(user);
		if(u==null){
			return null;
		}else{
			return u;
		}
	}

}
