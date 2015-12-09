package com.api.business.service;

import com.api.business.bean.Project;
import com.api.core.service.IGenericService;

public interface IProjectService extends IGenericService<Project, String>{
	/** 
	* 方法说明 :项目创建
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param user</p>
	*/
	public Project create(Project project, String userId) throws Exception;
	
	/** 
	* 方法说明 :项目创建
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param user</p>
	*/
	public Project modify(Project project, String userId) throws Exception;
	
	/** 
	* 方法说明 :查询单个项目
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param user</p>
	*/
	public Project get(String id) throws Exception;
}
