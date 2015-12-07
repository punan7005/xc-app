package com.api.core.persistence.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.api.core.persistence.IGenericDao;

@SuppressWarnings("unchecked")
public class IbatisGenericDao<T, PK extends Serializable> implements
		IGenericDao<T, PK> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public static final String POSTFIX_SELECTBYID = ".selectById";
	public static final String POSTFIX_SELECTBYIDS = ".selectByIds";
	public static final String POSTFIX_SELECTBYMAP = ".selectByMap";
	public static final String POSTFIX_PKSELECTMAP = ".pkSelectByMap";
	public static final String POSTFIX_COUNT = ".count";
	public static final String POSTFIX_COUNTLIKEBYMAP = ".countLikeByMap";
	public static final String POSTFIX_INSERT = ".insert";
	public static final String POSTFIX_DELETEBYID = ".deleteById";
	public static final String POSTFIX_DELETEBYIDS = ".deleteByIds";
	public static final String POSTFIX_DELETEBYIDSMAP = ".deleteByIdsMap";
	public static final String POSTFIX_DELETEBYMAP = ".deleteByMap";
	public static final String POSTFIX_UPDATE = ".update";
	public static final String POSTFIX_UPDATEBYMAP = ".updateByMap";
	public static final String POSTFIX_UPDATEBYIDSMAP = ".updateByIdsMap";

	protected Class<T> clazz;

	protected String clazzName;

	protected T t;

	// 定义主从数据库服务器，主数据库负责（Write op），从数据库负责（read op�?
	@Autowired
	protected SqlMapClientTemplate masterSqlMapClientTemplate;

	@Autowired
	protected SqlMapClientTemplate slaveSqlMapClientTemplate;

	public IbatisGenericDao() {
		// 通过范型反射，取得在子类中定义的class.
		clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		clazzName = clazz.getSimpleName();
		try {
			t = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer count(String propertyName, Object propertyValue) {
		return count(new String[] { propertyName },
				new Object[] { propertyValue });
	}

	@Override
	public Integer count(String[] propertyNames, Object[] propertyValues) {

		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < propertyNames.length; i++) {
			map.put(propertyNames[i], propertyValues[i]);
		}
		return (Integer) slaveSqlMapClientTemplate.queryForObject(clazz
				.getName()
				+ POSTFIX_COUNT, map);

	}



	@Override
	public T findById(PK id) {
		if (id == null)
			return null;

		return (T) slaveSqlMapClientTemplate.queryForObject(clazz.getName()
				+ POSTFIX_SELECTBYID, id);

	}

	@Override
	public List<T> findByIds(List<PK> ids) {
		if (ids == null || ids.size() == 0)
			return Collections.emptyList();

		return (List<T>) slaveSqlMapClientTemplate.queryForList(clazz.getName()
				+ POSTFIX_SELECTBYIDS, ids);

	}

	@Override
	public List<T> findByMap(String[] properties, Object[] propertyValues,
			String orderBy, String order) {
		
//		if (properties.length == 0) {
//			return Collections.emptyList();
//		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		if (orderBy != null) {
			map.put("orderBy", orderBy);
			map.put("order", order);
		}
		return (List<T>) slaveSqlMapClientTemplate.queryForList(clazz.getName()
				+ POSTFIX_SELECTBYMAP, map);

	}
	
	public List<PK> findIdsByMap(String[] properties, Object[] propertyValues, String orderBy, String order) {

		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		if (orderBy != null) {
			map.put("orderBy", orderBy);
			map.put("order", order);
		}
		return (List<PK>) slaveSqlMapClientTemplate.queryForList(clazz.getName() + POSTFIX_PKSELECTMAP, map);

	}
	
	@Override
	public List<T> findAll(){
		return (List<T>) slaveSqlMapClientTemplate.queryForList(clazz.getName()
				+ POSTFIX_SELECTBYMAP, null);
	}
	@Override
	public List<T> pageQueryBy(String[] properties, Object[] propertyValues,
			String orderBy, String order, int pageSize, int pageNo) {

//		if (properties.length == 0) {
//			return Collections.emptyList();
//		}

		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		if (orderBy != null) {
			map.put("orderBy", orderBy);
			map.put("order", order);
		}
		map.put("limit", true);
		map.put("start", (pageNo - 1) * pageSize);// limit 操作
		map.put("end", pageSize);
		return (List<T>) slaveSqlMapClientTemplate.queryForList(clazz.getName()
				+ POSTFIX_SELECTBYMAP, map);
	}
	@Override
	public Serializable insert(T o) throws Exception {

		return (Serializable) masterSqlMapClientTemplate.insert(clazz.getName()
				+ POSTFIX_INSERT, o);
	}
	
	@Override
	public List<Serializable> insert(final List<T> o) throws Exception {
		List<Serializable> ids = new ArrayList<Serializable>();
		masterSqlMapClientTemplate.execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				try {
					for (T t : o) {
						insert(t);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				executor.executeBatch();
				return null;
			}
		});
		return ids;
	}
	@Override
	public T update(T o) throws Exception {
		masterSqlMapClientTemplate.update(clazz.getName() + POSTFIX_UPDATE, o);
		return o;
	}
	@Override
	public int update(PK id, String[] properties, Object[] propertyValues)
			throws Exception {
		if (properties.length == 0) {
			return 0;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		map.put("id", id);
		return masterSqlMapClientTemplate.update(clazz.getName()
				+ POSTFIX_UPDATEBYMAP, map);
	}
	@Override
	public int updateByIdsMap(List<PK> ids, String[] properties,
			Object[] propertyValues) throws Exception {
		if (properties.length == 0) {
			return 0;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		map.put("ids", ids);
		return masterSqlMapClientTemplate.update(clazz.getName()
				+ POSTFIX_UPDATEBYIDSMAP, map);
	}
	@Override
	public void deleteById(PK id) throws Exception {
		if (id == null)
			return;
		masterSqlMapClientTemplate.delete(clazz.getName() + POSTFIX_DELETEBYID,
				id);
	}
	@Override
	public void deleteByIds(List<PK> ids) throws Exception {
		if (ids == null || ids.size() == 0)
			return;
		masterSqlMapClientTemplate.delete(
				clazz.getName() + POSTFIX_DELETEBYIDS, ids);
	}
	@Override
	public void deleteByIdsMap(List<PK> ids, String[] properties,
			Object[] propertyValues) throws Exception {
		
		if (properties.length == 0) {
			return;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		map.put("ids", ids);
		masterSqlMapClientTemplate.delete(clazz.getName()
				+ POSTFIX_DELETEBYIDSMAP, map);
	}
	@Override
	public int deleteByMap(String[] properties, Object[] propertyValues)
			throws Exception {
		if (properties.length == 0) {
			return 0;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		return masterSqlMapClientTemplate.delete(clazz.getName()
				+ POSTFIX_DELETEBYMAP, map);
	}

	//--------------------------------------自定义SQLMAP操作-----------------------------------//
	
	@Override
	public List<Map<String, Object>> findByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues, String orderBy,
			String order, int pageSize, int pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		if (orderBy != null) {
			map.put("orderBy", orderBy);
			map.put("order", order);
		}
		map.put("limit", true);
		map.put("start", (pageNo - 1) * pageSize);// limit 操作
		map.put("end", pageSize);
		List<Map<String,Object>> list = slaveSqlMapClientTemplate.queryForList(clazz.getName()
				+ statementPostfix, map);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues, String orderBy,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		if (orderBy != null) {
			map.put("orderBy", orderBy);
			map.put("order", order);
		}
		List<Map<String,Object>> list = slaveSqlMapClientTemplate.queryForList(clazz.getName()
				+ statementPostfix, map);
		return list;
	}

	@Override
	public void updateByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		masterSqlMapClientTemplate.update(clazz.getName() + statementPostfix,
				map);
	}
	@Override
	public void deleteByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		masterSqlMapClientTemplate.delete(clazz.getName() + statementPostfix,
				map);
	}

	@Override
	public Integer countByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		return (Integer) slaveSqlMapClientTemplate.queryForObject(clazz.getName()
				+ statementPostfix, map);
	}
	@Override
	public void insertByStatementPostfix(String statementPostfix,
			String[] properties, Object[] propertyValues) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < properties.length; i++) {
			map.put(properties[i], propertyValues[i]);
		}
		masterSqlMapClientTemplate.insert(clazz.getName() + statementPostfix,
				map);
	}

}
