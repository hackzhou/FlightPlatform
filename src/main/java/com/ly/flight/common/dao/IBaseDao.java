package com.ly.flight.common.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public interface IBaseDao {

	public Object load(Class<?> pojoClass, Serializable id);
	public Object get(Class<?> pojoClass, Serializable id);
	public void save(Object pojo) throws Exception;
	public void update(Object pojo) throws Exception;
	public void insertOrUpdate(Object pojo) throws Exception;
	public void delete(Object pojo) throws Exception;
	public void delete(Class<?> pojoClass, Serializable id) throws Exception;
	public Object findOne(String hql, Object[] param);
	public List<?> find(String hql);
	public List<?> find(String hql, Object[] param);
	public List<?> find(String queryString, Object[] param, boolean userCache);
	public List<?> find(String hql, Object[] params, int firstResult, int maxResult);
	public List<?> find(String hql, Object[] params, int firstResult, int maxResult, boolean userCache);
	public List<?> queryBySql(String sql);
	public int executeByHql(String hql);
	public int executeByHql(String hql, Object[] param);
	public Iterator<?> iterate(String hql);
	public Iterator<?> iterate(String hql, Object[] objects);

}
