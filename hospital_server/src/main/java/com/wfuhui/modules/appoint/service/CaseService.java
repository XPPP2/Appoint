package com.wfuhui.modules.appoint.service;

import com.wfuhui.modules.appoint.entity.CaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 病例
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 * @date 2020-12-10 22:11:38
 */
public interface CaseService {
	
	CaseEntity queryObject(Integer id);
	
	List<CaseEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CaseEntity cas);
	
	void update(CaseEntity cas);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
