package com.api.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.business.bean.Project;
import com.api.business.dao.IProjectDao;
import com.api.business.service.IProjectService;
import com.api.core.persistence.IGenericDao;
import com.api.core.service.impl.GenericService;

public class ProjectService extends GenericService<Project, String> implements IProjectService{

	@Autowired
	private IProjectDao projectDao;
	
	@Override
	protected IGenericDao getDao() {
		// TODO Auto-generated method stub
		return this.projectDao;
	}

}
