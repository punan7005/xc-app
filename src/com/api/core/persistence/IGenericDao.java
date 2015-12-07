package com.api.core.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 泛化DAO基类.
 * @param <T> DAO访问的entity
 * @param <PK> DAO访问的entity的主键类
 */
public interface IGenericDao<T, PK extends Serializable> {

	public Integer count(final String propertyName, final Object propertyValue);

	public Integer count(final String[] propertyNames, final Object[] propertyValues);

	public Integer countByStatementPostfix(String statementPostfix,String[] properties, Object[] propertyValues);

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

	public List<Map<String, Object>> findByStatementPostfix(String statementPostfix,String[] properties, Object[] propertyValues,String orderBy, String order,  int pageSize, int pageNo);
	
	public void updateByStatementPostfix(String statementPostfix,String[] properties, Object[] propertyValues);

	public void deleteByStatementPostfix(String statementPostfix,String[] properties, Object[] propertyValues);

	public void insertByStatementPostfix(String statementPostfix,String[] properties, Object[] propertyValues);
	
	public List<Serializable> insert(final List<T> o) throws Exception;

	List<Map<String, Object>> findByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues, String orderBy,
			String order);

}
