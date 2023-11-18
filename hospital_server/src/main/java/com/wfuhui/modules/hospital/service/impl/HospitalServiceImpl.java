package com.wfuhui.modules.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wfuhui.modules.hospital.dao.HospitalDao;
import com.wfuhui.modules.hospital.entity.HospitalEntity;
import com.wfuhui.modules.hospital.service.HospitalService;



@Service("hospitalService")
public class HospitalServiceImpl implements HospitalService {
	@Autowired
	private HospitalDao hospitalDao;
	
	@Override
	public HospitalEntity queryObject(Integer id){
		return hospitalDao.queryObject(id);
	}
	
	@Override
	public List<HospitalEntity> queryList(Map<String, Object> map){
		return hospitalDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return hospitalDao.queryTotal(map);
	}
	
	@Override
	public void save(HospitalEntity hospital){
		hospitalDao.save(hospital);
	}
	
	@Override
	public void update(HospitalEntity hospital){
		hospitalDao.update(hospital);
	}
	
	@Override
	public void delete(Integer id){
		hospitalDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		hospitalDao.deleteBatch(ids);
	}
	
}
