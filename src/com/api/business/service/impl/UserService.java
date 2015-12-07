package com.api.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.business.bean.User;
import com.api.business.dao.IUserDao;
import com.api.business.service.IUserService;
import com.api.core.persistence.IGenericDao;
import com.api.core.service.impl.GenericService;

public class UserService extends GenericService<User, String> implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	@Override
	protected IGenericDao getDao() {
		// TODO Auto-generated method stub
		return this.userDao;
	}

	
}
