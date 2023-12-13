package com.wfuhui.modules.appoint.service;

import com.wfuhui.modules.appoint.entity.AppointEntity;

import java.util.List;
import java.util.Map;


public interface AppointService {
	
	AppointEntity queryObject(Integer id);
	
	List<AppointEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AppointEntity appoint);
	
	void update(AppointEntity appoint);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<Map<String, String>> queryAppointCount();
}
