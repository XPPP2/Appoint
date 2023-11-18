package com.wfuhui.modules.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wfuhui.modules.hospital.dao.DoctorDao;
import com.wfuhui.modules.hospital.entity.DoctorEntity;
import com.wfuhui.modules.hospital.service.DoctorService;



@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private DoctorDao doctorDao;
	
	@Override
	public DoctorEntity queryObject(Integer id){
		return doctorDao.queryObject(id);
	}
	
	@Override
	public List<DoctorEntity> queryList(Map<String, Object> map){
		return doctorDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return doctorDao.queryTotal(map);
	}
	
	@Override
	public void save(DoctorEntity doctor){
		doctorDao.save(doctor);
	}
	
	@Override
	public void update(DoctorEntity doctor){
		doctorDao.update(doctor);
	}
	
	@Override
	public void delete(Integer id){
		doctorDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		doctorDao.deleteBatch(ids);
	}
	
}
