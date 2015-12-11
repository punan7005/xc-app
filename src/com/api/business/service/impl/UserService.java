package com.api.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.api.business.bean.User;
import com.api.business.dao.IUserDao;
import com.api.business.service.IUserService;
import com.api.core.persistence.IGenericDao;
import com.api.core.service.impl.GenericService;
import com.api.tools.DateTools;
import com.api.tools.DateUtils;
import com.api.tools.UuidFactory;

public class UserService extends GenericService<User, String> implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	@Override
	protected IGenericDao getDao() {
		// TODO Auto-generated method stub
		return this.userDao;
	}

	@Override
	public boolean isExist(String phoneNo) {
		// TODO Auto-generated method stub
		boolean flag = false;
		List<User> users = this.userDao.findByMap(new String[]{"phoneNo", "status"}, new Object[]{phoneNo, 0}, null, null);
		if(!users.isEmpty()) flag = true;
		return flag;
	}

	@Override
	public User login(String phoneNo, String passWord) {
		// TODO Auto-generated method stub
		User rUser = null;
		List<User> users = this.userDao.findByMap(new String[]{"phoneNo", "passWord", "status"}, new Object[]{phoneNo, passWord, 0}, null, null);
		if(!users.isEmpty()) rUser = users.get(0);
		return rUser;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public User modify(User user) throws Exception {
		// TODO Auto-generated method stub
		User rUser = null;
		this.userDao.update(user);
		rUser = this.userDao.findById(user.getUuid());
		return rUser;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public User create(String phoneNo, String passWord, int type) throws Exception {
		// TODO Auto-generated method stub
		User create = new User();
		String uuid = UuidFactory.getUuid();
		create.setUuid(uuid);
		create.setCreateId(uuid);
		create.setCreateTime(DateUtils.getDateTime());
		create.setPhoneNo(phoneNo);
		create.setPassWord(passWord);
		create.setType(type);
		this.userDao.insert(create);
		User rUser = this.userDao.findById(uuid);
		return rUser;
	}

	@Override
	public User get(String id) {
		// TODO Auto-generated method stub
		User rUser = this.userDao.findById(id);
		return rUser;
	}

	
}
