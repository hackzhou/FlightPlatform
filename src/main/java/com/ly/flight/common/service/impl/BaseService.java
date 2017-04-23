package com.ly.flight.common.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ly.flight.common.dao.IBaseDao;
import com.ly.flight.common.exception.BusinessException;
import com.ly.flight.common.service.IBaseService;

public class BaseService implements IBaseService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected IBaseDao baseDao;

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public int getCountByParameter(String queryString, Object[] parameter) {
		int orderIndex = queryString.indexOf("order");
		if (orderIndex != -1) {
			queryString = queryString.substring(0, orderIndex);
		}
		@SuppressWarnings("unchecked")
		List<Long> countList = (List<Long>) baseDao.find("select count(*) " + queryString, parameter);
		return countList.get(0).intValue();
	}

	public void delete(Object obj) throws BusinessException {
		try {
			baseDao.delete(obj);
		} catch (Exception e) {
			logger.error("", e);
			throw new BusinessException("删除失败!");
		}
	}

	public <T> List<T> queryByIds(Class<T> pojo, Integer[] ids) throws BusinessException {
		if (ids.length == 0) {
			throw new BusinessException("ids为空");
		}
		StringBuffer sb = new StringBuffer("(");
		for (Integer id : ids) {
			sb.append(id + ",");
		}
		sb.setLength(sb.length() - 1);
		sb.append(")");
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>) baseDao.find("from " + pojo.getSimpleName() + " where id in " + sb.toString());
		return list;

	}

	protected String convertHql(List<Integer> list) {
		if(list.isEmpty()){
			return null;
		}
		StringBuffer sb = new StringBuffer("");
		for (Integer i : list) {
			sb.append(i + ",");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

}
