package com.ly.flight.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.web.servlet.ModelAndView;
import com.ly.flight.common.config.GlobalValueConfig;
import com.ly.flight.entity.TUser;

public class BaseController implements MessageSourceAware {

	protected MessageSource messageSource;
	public static final String RESPONSE_CODE	= "responseCode";
	public static final String RESPONSE_MSG		= "responseMsg";
	public static final String DATA_LIST		= "dataList";
	public static final String DATA				= "data";
	public static final String DATA_COUNT		= "dataCount";
	public static final String SUCCESS_CODE		= "0000";
	public static final String FAILED_CODE		= "1111";

	public ModelAndView success(List<?> results, int count, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, SUCCESS_CODE);
		map.put(DATA_LIST, results);
		map.put(DATA_COUNT, count);
		mv.addAllObjects(map);
		return mv;
	}

	public Map<String, Object> successJson(Object result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, SUCCESS_CODE);
		map.put(DATA, result);
		return map;
	}

	public Map<String, Object> failedJson(String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, FAILED_CODE);
		map.put(RESPONSE_MSG, message);
		return map;
	}

	public ModelAndView fail(String responseCode, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, responseCode);
		map.put(RESPONSE_MSG, GlobalValueConfig.getMsgByCode(responseCode, ""));
		mv.addAllObjects(map);
		return mv;
	}

	public ModelAndView fail(String responseCode, String responseMsg, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, responseCode);
		map.put(RESPONSE_MSG, GlobalValueConfig.getMsgByCode(responseCode, responseMsg));
		mv.addAllObjects(map);
		return mv;
	}
	public ModelAndView success(String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, SUCCESS_CODE);
		mv.addAllObjects(map);
		return mv;
	}

	public ModelAndView success(Object data, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, SUCCESS_CODE);
		map.put(DATA, data);
		mv.addAllObjects(map);
		return mv;
	}

	public ModelAndView data(String name, Object data, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(RESPONSE_CODE, SUCCESS_CODE);
		map.put(name, data);
		mv.addAllObjects(map);
		return mv;
	}

	public ModelAndView fail(String code, Object[] args, String viewName) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("failure", true);
		String message = messageSource.getMessage(code, args, "Fail!", null);
		map.put("msg", message);
		mv.addAllObjects(map);
		return mv;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public int getStart(int page, int size) {
		int start = (page - 1) * size;
		return start < 0 ? 0 : start;
	}

	public TUser getCurrentUser(HttpServletRequest request) {
		TUser user = (TUser) request.getSession().getAttribute("user");
		return user;

	}

}
