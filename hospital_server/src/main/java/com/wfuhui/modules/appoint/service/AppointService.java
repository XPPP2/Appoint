package com.wfuhui.modules.appoint.service;

import com.wfuhui.modules.appoint.entity.AppointEntity;

import java.util.List;
import java.util.Map;

/**
 * 挂号
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 * @date 2020-12-10 22:11:38
 */
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
