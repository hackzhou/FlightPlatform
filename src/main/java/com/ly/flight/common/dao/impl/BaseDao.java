package com.ly.flight.common.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ly.flight.common.dao.IBaseDao;

@SuppressWarnings("deprecation")
public class BaseDao extends HibernateDaoSupport implements IBaseDao {

	public Object load(Class<?> pojoClass, Serializable id) {
		return getHibernateTemplate().load(pojoClass, id);
	}
	
	public void save(Object pojo) throws Exception {
		getHibernateTemplate().save(pojo);
	}
	
	public List<?> queryBySql(String sql) {
		List<?> l = getHibernateTemplate().find(sql);
		return l;
	}
	
	public void update(Object pojo) throws Exception {
		getHibernateTemplate().update(pojo);
	}
	
	public void delete(Object pojo) throws Exception {
		getHibernateTemplate().delete(pojo);
	}
	
	public void delete(Class<?> pojoClass, Serializable id) throws Exception {
		Object o = this.load(pojoClass, id);
		getHibernateTemplate().delete(o);
	}
	
	public Object get(Class<?> pojoClass, Serializable id) {
		if (id == null)
			return null;
		return getHibernateTemplate().get(pojoClass, id);
	}
	
	public void insertOrUpdate(Object pojo) throws Exception {
		this.getHibernateTemplate().saveOrUpdate(pojo);
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
		@SuppressWarnings("unchecked")
		List list = (List) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
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
			}
		});
		return list;
	}

	public int executeByHql(final String hql) {
		return this.getHibernateTemplate().bulkUpdate(hql);
	}

	public int executeByHql(final String hql, Object[] param) {
		return this.getHibernateTemplate().bulkUpdate(hql, param);
	}

	public Iterator<?> iterate(String hql, Object[] objects) {
		return this.getHibernateTemplate().iterate(hql, objects);
	}

	public Iterator<?> iterate(String hql) {
		return this.getHibernateTemplate().iterate(hql);
	}

	public List<?> find(String hql) {
		return this.getHibernateTemplate().find(hql);
	}

	public Object findOne(String hql, Object[] param){
		List<?> list = this.getHibernateTemplate().find(hql, param);
		if(list.size()!=1){
			throw new RuntimeException("result must unique...");
		}
		return list.get(0);
	}

}
