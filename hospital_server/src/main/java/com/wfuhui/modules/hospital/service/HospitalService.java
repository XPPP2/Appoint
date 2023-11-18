package com.wfuhui.modules.hospital.service;

import com.wfuhui.modules.hospital.entity.HospitalEntity;

import java.util.List;
import java.util.Map;

/**
 * 医院
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 * @date 2020-12-10 22:11:38
 */
public interface HospitalService {
	
	HospitalEntity queryObject(Integer id);
	
	List<HospitalEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HospitalEntity hospital);
	
	void update(HospitalEntity hospital);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
