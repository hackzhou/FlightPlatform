package com.ly.flight.common.service;

import com.ly.flight.common.exception.BusinessException;

public interface IBaseService {

	public int getCountByParameter(String queryString, Object[] parameter) throws BusinessException;

}