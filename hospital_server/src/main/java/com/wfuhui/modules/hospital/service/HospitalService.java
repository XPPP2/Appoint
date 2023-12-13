package com.wfuhui.modules.hospital.service;

import com.wfuhui.modules.hospital.entity.HospitalEntity;

import java.util.List;
import java.util.Map;


public interface HospitalService {
	
	HospitalEntity queryObject(Integer id);
	
	List<HospitalEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HospitalEntity hospital);
	
	void update(HospitalEntity hospital);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
