package com.api.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.business.bean.AuthToken;
import com.api.business.dao.IAuthTokenDao;
import com.api.business.service.IAuthTokenService;
import com.api.core.persistence.IGenericDao;
import com.api.core.service.impl.GenericService;

public class AuthTokenService extends GenericService<AuthToken, String> implements IAuthTokenService{

	@Autowired
	private IAuthTokenDao	authTokenDao;
	
	@Override
	protected IGenericDao getDao() {
		// TODO Auto-generated method stub
		return authTokenDao;
	}

	@Override
	public void operationTimeOut(String id) {
		authTokenDao.operationTimeOut(id);
	}

	@Override
	public boolean isTimeOut(String id) {
		// TODO Auto-generated method stub
		return authTokenDao.isTimeOut(id);
	}

	@Override
	public void relet(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public AuthToken getAuthTokenByUser(String parentId) {
		// TODO Auto-generated method stub
		return authTokenDao.getAuthTokenByUser(parentId);
	}

	@Override
	public void mutexLockClear(String parentId, String atId) {
		// TODO Auto-generated method stub
		authTokenDao.mutexLockClear(parentId, atId);
	}

}
