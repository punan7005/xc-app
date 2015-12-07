package com.api.core.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.api.core.persistence.IGenericDao;
import com.api.core.service.IGenericService;



public abstract class GenericService<T, PK extends Serializable> implements IGenericService<T, PK> {

	abstract protected IGenericDao getDao();
	
	@Override
	public Integer count(String propertyName, Object propertyValue) {
		return getDao().count(propertyName, propertyValue);
	}

	@Override
	public Integer count(String[] propertyNames, Object[] propertyValues) {
		return getDao().count(propertyNames, propertyValues);
	}

	
	@Transactional
	@Override
	public void deleteById(PK id) throws Exception {
		getDao().deleteById(id);
	}

	@Transactional
	@Override
	public void deleteByIds(List<PK> ids) throws Exception {
		getDao().deleteByIds(ids);
	}

	@Transactional
	@Override
	public int deleteByMap(String[] properties, Object[] propertyValues) throws Exception {
		return getDao().deleteByMap(properties, propertyValues);
	}
	
	@Transactional
	@Override
	public void deleteByIdsMap(List<PK> ids,String[] properties, Object[] propertyValues) throws Exception{
		getDao().deleteByIdsMap(ids,properties, propertyValues);
	}

	@Override
	public T findById(PK id) {
		return (T)getDao().findById(id);
	}

	@Override
	public List<T> findByIds(List<PK> ids) {
		return getDao().findByIds(ids);
	}
	
	@Override
	public List<PK> findIdsByMap(String[] properties, Object[] propertyValues, String orderBy, String order) {
		return getDao().findIdsByMap(properties, propertyValues, orderBy, order);
	}
	
	@Override
	public List<T> findByMap(String[] properties, Object[] propertyValues, String orderBy, String order) {
		return getDao().findByMap(properties, propertyValues, orderBy, order);
	}
	
	@Override
	public List<T> findAll(){
		return getDao().findAll();
	}
	
	@Transactional
	@Override
	public Serializable insert(T o) throws Exception {
		return getDao().insert(o);
	}

	@Override
	public List<T> pageQueryBy(String[] properties, Object[] propertyValues, String orderBy, String order, int pageSize, int pageNo) {
		return getDao().pageQueryBy(properties, propertyValues, orderBy, order, pageSize, pageNo);
	}
	@Override
	public List<Map<String, Object>> findByStatementPostfix(String statementPostfix, String[] properties, Object[] propertyValues, String orderBy, String order, int pageSize, int pageNo) {
		return getDao().findByStatementPostfix(statementPostfix, properties, propertyValues, orderBy, order, pageSize, pageNo);
	}

	@Transactional
	@Override
	public int update(PK id, String[] properties, Object[] propertyValues) throws Exception {
		return getDao().update(id, properties, propertyValues);
	}

	@Transactional
	@Override
	public T update(T o) throws Exception {
		return (T)getDao().update(o);
	}
	@Transactional
	@Override
	public int updateByIdsMap(List<PK> ids, String[] properties, Object[] propertyValues) throws Exception {
		return getDao().updateByIdsMap(ids, properties, propertyValues);
	}
}
