package com.ly.flight.common.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.ly.flight.common.dao.IBaseDao;

public class BaseDao extends HibernateDaoSupport implements IBaseDao {

	public Object load(Class<?> pojoClass, Serializable id) {
		return this.getHibernateTemplate().load(pojoClass, id);
	}
	
	public Object get(Class<?> pojoClass, Serializable id) {
		if (id == null)
			return null;
		return this.getHibernateTemplate().get(pojoClass, id);
	}
	
	public void save(Object pojo) throws Exception {
		this.getHibernateTemplate().save(pojo);
	}
	
	public void update(Object pojo) throws Exception {
		this.getHibernateTemplate().update(pojo);
	}
	
	public void insertOrUpdate(Object pojo) throws Exception {
		this.getHibernateTemplate().saveOrUpdate(pojo);
	}
	
	public void delete(Object pojo) throws Exception {
		this.getHibernateTemplate().delete(pojo);
	}
	
	public void delete(Class<?> pojoClass, Serializable id) throws Exception {
		Object o = this.load(pojoClass, id);
		this.getHibernateTemplate().delete(o);
	}
	
	public Object findOne(String hql, Object[] param){
		List<?> list = this.getHibernateTemplate().find(hql, param);
		if(list.size()!=1){
			throw new RuntimeException("result must unique...");
		}
		return list.get(0);
	}
	
	public List<?> find(String hql) {
		return this.getHibernateTemplate().find(hql);
	}
	
	public List<?> find(String hql, Object[] param) {
		return this.getHibernateTemplate().find(hql, param);
	}
	
	public List<?> find(String hql, Object[] param, boolean userCache) {
		this.getHibernateTemplate().setCacheQueries(userCache);
		return this.getHibernateTemplate().find(hql, param);
	}
	
	public List<?> find(final String queryString, final Object[] params, final int firstResult, final int maxResult) {
		return this.find(queryString, params, firstResult, maxResult, false);
	}
	
	@SuppressWarnings("rawtypes")
	public List<?> find(final String queryString, final Object[] params, final int firstResult, final int maxResult, final boolean userCache) {
		List<?> list = (List<?>) this.getHibernateTemplate().execute((Session session)-> {
			Query query = session.createQuery(queryString);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			query.setCacheable(userCache);
			if (userCache) {
			}
			return query.setFirstResult((firstResult - 1) * maxResult).setMaxResults(maxResult).list();
		});
		return list;
	}

	public List<?> queryBySql(String sql) {
		List<?> l = this.getHibernateTemplate().find(sql);
		return l;
	}

	public int executeByHql(final String hql) {
		return this.getHibernateTemplate().bulkUpdate(hql);
	}

	public int executeByHql(final String hql, Object[] param) {
		return this.getHibernateTemplate().bulkUpdate(hql, param);
	}

	public Iterator<?> iterate(String hql) {
		return this.getHibernateTemplate().iterate(hql);
	}
	
	public Iterator<?> iterate(String hql, Object[] objects) {
		return this.getHibernateTemplate().iterate(hql, objects);
	}
	
}
