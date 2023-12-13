package com.wfuhui.modules.appoint.service;

import com.wfuhui.modules.appoint.entity.CaseEntity;

import java.util.List;
import java.util.Map;


public interface CaseService {
	
	CaseEntity queryObject(Integer id);
	
	List<CaseEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CaseEntity cas);
	
	void update(CaseEntity cas);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
