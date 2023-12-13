package com.wfuhui.modules.hospital.service;

import com.wfuhui.modules.hospital.entity.DeptEntity;

import java.util.List;
import java.util.Map;


public interface DeptService {
	
	DeptEntity queryObject(Integer id);
	
	List<DeptEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DeptEntity dept);
	
	void update(DeptEntity dept);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
