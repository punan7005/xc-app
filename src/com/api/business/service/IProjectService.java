package com.api.business.service;

import java.util.List;

import com.api.business.bean.Project;
import com.api.core.service.IGenericService;

public interface IProjectService extends IGenericService<Project, String>{
	/** 
	* 方法说明 :项目创建
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param </p>
	*/
	public Project create(Project project, String userId) throws Exception;
	
	/** 
	* 方法说明 :项目创建
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param </p>
	*/
	public Project modify(Project project, String userId) throws Exception;
	
	/** 
	* 方法说明 :查询单个项目
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param </p>
	*/
	public Project get(String id) throws Exception;
	
	/** 
	* 方法说明 :查询单个项目
	* @author  joker 
	* 创建时间：2015-12-09
	* <p>@param </p>
	*/
	public List<Project> getsByUser(String userId, int pageSize, int pageNo);
}
