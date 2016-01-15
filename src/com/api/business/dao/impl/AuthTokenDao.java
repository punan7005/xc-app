package com.api.business.dao.impl;

import java.util.List;

import com.api.business.bean.AuthToken;
import com.api.business.dao.IAuthTokenDao;
import com.api.core.persistence.impl.IbatisGenericDao;
import com.api.tools.UuidFactory;

public class AuthTokenDao extends IbatisGenericDao<AuthToken, String> implements IAuthTokenDao{

	/** 
	* 方法说明 ：生成authToken
	* @author  joker 
	* 创建时间：2015-03-16
	* <p>@param String parentId</p>
	*/
	@Override
	public AuthToken add(String parentId) {
		// TODO Auto-generated method stub
		String uuid = UuidFactory.getUuid();
		AuthToken authToken = new AuthToken();
		authToken.setParentId(parentId);
		authToken.setUuid(uuid);
		authToken.setStartTime(System.currentTimeMillis());
		
		try {
			this.insert(authToken);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		authToken = this.findById(uuid);
		return authToken;
	}

	@Override
	public void operationTimeOut(String id) {
		// TODO Auto-generated method stub
		try {
			this.update(id, new String[]{"status"}, new Object[]{1});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isTimeOut(String id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		AuthToken authToken = this.findById(id);
		if(authToken != null){
			if(authToken.getStatus() == 1) return true;
			long thisTime = System.currentTimeMillis();
			if((thisTime - authToken.getStartTime().longValue()) / 1000 / 60 / 60 / 24 > 7) flag = true;
		}
		return flag;
	}

	@Override
	public void relet(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AuthToken getAuthTokenByUser(String parentId) {
		// TODO Auto-generated method stub
		AuthToken authToken = null;
		List<AuthToken> authTokens = this.findByMap(new String[]{"parentId", "status"}, new Object[]{parentId, 0}, null, null);
		if(!authTokens.isEmpty()){
			authToken = authTokens.get(0);
		}
		return authToken;
	}

	@Override
	public void mutexLockClear(String parentId, String atId) {
		// TODO Auto-generated method stub
		AuthToken authToken = null;
		List<AuthToken> authTokens = this.findByMap(new String[]{"parentId", "status"}, new Object[]{parentId, 0}, null, null);
		if(!authTokens.isEmpty()){
			for(int i = 0; i < authTokens.size(); i++){
				authToken = authTokens.get(i);
				if(!atId.equals(authToken.getUuid()))
					try {
						this.update(authToken.getUuid(), new String[]{"status"}, new Object[]{1});
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}

}
