package com.wfuhui.modules.hospital.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wfuhui.modules.hospital.dao.DeptDao;
import com.wfuhui.modules.hospital.entity.DeptEntity;
import com.wfuhui.modules.hospital.service.DeptService;



@Service("deptService")
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao deptDao;
	
	@Override
	public DeptEntity queryObject(Integer id){
		return deptDao.queryObject(id);
	}
	
	@Override
	public List<DeptEntity> queryList(Map<String, Object> map){
		return deptDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return deptDao.queryTotal(map);
	}
	
	@Override
	public void save(DeptEntity dept){
		deptDao.save(dept);
	}
	
	@Override
	public void update(DeptEntity dept){
		deptDao.update(dept);
	}
	
	@Override
	public void delete(Integer id){
		deptDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		deptDao.deleteBatch(ids);
	}
	
}
