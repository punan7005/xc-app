package com.api.business.dao.impl;

import com.api.business.bean.User;
import com.api.business.dao.IUserDao;
import com.api.core.persistence.impl.IbatisGenericDao;

public class UserDao extends IbatisGenericDao<User, String> implements IUserDao{

}
