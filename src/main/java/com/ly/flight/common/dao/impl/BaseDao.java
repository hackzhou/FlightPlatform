package com.ly.flight.common.dao.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.ly.flight.common.dao.IBaseDao;

public class BaseDao extends HibernateDaoSupport implements IBaseDao {

	@Override
	public Object load(Class<?> pojoClass, Serializable id) {
		return this.getHibernateTemplate().load(pojoClass, id);
	}
	
	@Override
	public Object get(Class<?> pojoClass, Serializable id) {
		if (id == null)
			return null;
		return this.getHibernateTemplate().get(pojoClass, id);
	}
	
	@Override
	public void save(Object pojo) throws Exception {
		this.getHibernateTemplate().save(pojo);
	}
	
	@Override
	public void update(Object pojo) throws Exception {
		this.getHibernateTemplate().update(pojo);
	}
	
	@Override
	public void insertOrUpdate(Object pojo) throws Exception {
		this.getHibernateTemplate().saveOrUpdate(pojo);
	}
	
	@Override
	public void delete(Object pojo) throws Exception {
		this.getHibernateTemplate().delete(pojo);
	}
	
	@Override
	public void delete(Class<?> pojoClass, Serializable id) throws Exception {
		Object o = this.load(pojoClass, id);
		this.getHibernateTemplate().delete(o);
	}
	
	@Override
	public Object findOne(String hql, Object[] param){
		List<?> list = this.getHibernateTemplate().find(hql, param);
		if(list.size()!=1){
			throw new RuntimeException("result must unique...");
		}
		return list.get(0);
	}
	
	@Override
	public List<?> find(String hql) {
		return this.getHibernateTemplate().find(hql);
	}
	
	@Override
	public List<?> find(String hql, Object[] param) {
		return this.getHibernateTemplate().find(hql, param);
	}
	
	@Override
	public List<?> find(String hql, Object[] param, boolean userCache) {
		this.getHibernateTemplate().setCacheQueries(userCache);
		return this.getHibernateTemplate().find(hql, param);
	}
	
	@Override
	public List<?> find(final String queryString, final Object[] params, final int firstResult, final int maxResult) {
		return this.find(queryString, params, firstResult, maxResult, false);
	}
	
	@Override
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

	@Override
	public List<?> queryBySql(String sql) {
		List<?> l = this.getHibernateTemplate().find(sql);
		return l;
	}

	@Override
	public int executeByHql(final String hql) {
		return this.getHibernateTemplate().bulkUpdate(hql);
	}

	@Override
	public int executeByHql(final String hql, Object[] param) {
		return this.getHibernateTemplate().bulkUpdate(hql, param);
	}

	@Override
	public Iterator<?> iterate(String hql) {
		return this.getHibernateTemplate().iterate(hql);
	}
	
	@Override
	public Iterator<?> iterate(String hql, Object[] objects) {
		return this.getHibernateTemplate().iterate(hql, objects);
	}
	
}
