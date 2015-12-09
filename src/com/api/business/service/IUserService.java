package com.api.business.service;

import com.api.business.bean.User;
import com.api.core.service.IGenericService;

public interface IUserService extends IGenericService<User, String>{

	
	/** 
	* 方法说明 :用户是否存在
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param user</p>
	*/
	public boolean isExist(String phoneNo);
	
	/** 
	* 方法说明 :用户登陆
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param user</p>
	*/
	public User login(String phoneNo, String passWord);
	
	/** 
	* 方法说明 :用户信息修改
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param user</p>
	*/
	public User modify(User user) throws Exception;
	
	/** 
	* 方法说明 :用户信息修改
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param user</p>
	*/
	public User create(String phoneNo, String passWord) throws Exception;
}
