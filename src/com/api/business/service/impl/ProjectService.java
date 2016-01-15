package com.api.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.api.business.bean.Project;
import com.api.business.dao.IProjectDao;
import com.api.business.service.IProjectService;
import com.api.core.persistence.IGenericDao;
import com.api.core.service.impl.GenericService;
import com.api.tools.DateUtils;
import com.api.tools.UuidFactory;

public class ProjectService extends GenericService<Project, String> implements IProjectService{

	@Autowired
	private IProjectDao projectDao;
	
	@Override
	protected IGenericDao getDao() {
		// TODO Auto-generated method stub
		return this.projectDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Project create(Project project, String userId) throws Exception {
		// TODO Auto-generated method stub
		String uuid = UuidFactory.getUuid();
		project.setUuid(uuid);
		project.setCreateId(userId);
		project.setCreateTime(DateUtils.getDateTime());
		this.projectDao.insert(project);
		Project rProject = this.projectDao.findById(uuid);
		return rProject;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Project modify(Project project, String userId) throws Exception {
		// TODO Auto-generated method stub
		project.setUpdateTime(DateUtils.getDateTime());
		project.setUpdateId(userId);
		this.projectDao.update(project);
		Project rProject = this.projectDao.findById(project.getUuid());
		return rProject;
	}

	@Override
	public Project get(String id) throws Exception {
		// TODO Auto-generated method stub
		Project rProject = this.projectDao.findById(id);
		return rProject;
	}

	@Override
	public List<Project> getsByUser(String userId, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		List<Project> list = this.projectDao.pageQueryBy(new String[]{"createId", "status"}, new Object[]{userId, 0}, "create_time", "desc", pageSize, pageNo);
		return list;
	}

	@Override
	public List<Project> gets(int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		List<Project> list = this.projectDao.pageQueryBy(new String[]{"status"}, new Object[]{0}, "create_time", "desc", pageSize, pageNo);
		return list;
	}

}
