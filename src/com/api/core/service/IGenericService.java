package com.api.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IGenericService<T, PK extends Serializable> {
	

	public Integer count(final String propertyName, final Object propertyValue);

	public Integer count(final String[] propertyNames, final Object[] propertyValues);

	public T findById(PK id);
	
	public List<T> findByIds(List<PK> ids);
	
	public List<T> findByMap(String[] properties, Object[] propertyValues,String orderBy, String order);
	
	public List<PK> findIdsByMap(String[] properties, Object[] propertyValues, String orderBy, String order);
	
	public List<T> findAll();
	
	public Serializable insert(T o) throws Exception;

	public T update(T o) throws Exception;

	public int update(PK id, String[] properties, Object[] propertyValues) throws Exception;

	public int updateByIdsMap(List<PK> ids,String[] properties, Object[] propertyValues) throws Exception;

	public void deleteById(PK id) throws Exception;
	
	public void deleteByIds(List<PK> ids) throws Exception;

	public void deleteByIdsMap(List<PK> ids,String[] properties, Object[] propertyValues) throws Exception;

	public int deleteByMap(String[] properties, Object[] propertyValues) throws Exception;
	
	public List<T> pageQueryBy(String[] properties, Object[] propertyValues, String orderBy, String order, int pageSize, int pageNo);
	//自定义获取列表页
	public List<Map<String, Object>> findByStatementPostfix(String statementPostfix,String[] properties, Object[] propertyValues,String orderBy, String order,  int pageSize, int pageNo);
	
}
