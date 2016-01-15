package com.api.business.dao;

import com.api.business.bean.AuthToken;
import com.api.core.persistence.IGenericDao;

public interface IAuthTokenDao extends IGenericDao<AuthToken, String>{

	/** 
	* 方法说明 ：生成authToken
	* @author  joker 
	* 创建时间：2015-03-16
	* <p>@param user</p>
	*/
	public AuthToken add(String parentId);
	
	/** 
	* 方法说明 :authtoken超时
	* @author  joker 
	* 创建时间：2015-03-16
	* <p>@param user</p>
	*/
	public void operationTimeOut(String id);
	
	/** 
	* 方法说明 :authtoken是否超时
	* @author  joker 
	* 创建时间：2015-03-16
	* <p>@param user</p>
	*/
	public boolean isTimeOut(String id);
	
	/** 
	* 方法说明 :authtoken续租
	* @author  joker 
	* 创建时间：2015-03-16
	* <p>@param user</p>
	*/
	public void relet(String id);
	
	/** 
	* 方法说明 :获取用户的authtoken
	* @author  joker 
	* 创建时间：2015-04-28
	* <p>@param user</p>
	*/
	public AuthToken getAuthTokenByUser(String parentId);
	
	/** 
	* 方法说明 :authtoken互斥处理
	* @author  joker 
	* 创建时间：2015-04-28
	* <p>@param user</p>
	*/
	public void mutexLockClear(String parentId, String atId);
}
