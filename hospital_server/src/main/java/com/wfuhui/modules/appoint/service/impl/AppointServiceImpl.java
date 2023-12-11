package com.wfuhui.modules.appoint.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wfuhui.modules.appoint.dao.AppointDao;
import com.wfuhui.modules.appoint.entity.AppointEntity;
import com.wfuhui.modules.appoint.service.AppointService;
import com.wfuhui.modules.settings.dao.TimeDao;



@Service("appointService")
public class AppointServiceImpl implements AppointService {
	@Autowired
	private AppointDao appointDao;
	
	@Autowired
	private TimeDao timeDao;
	
	@Override
	public AppointEntity queryObject(Integer id){
		return appointDao.queryObject(id);
	}
	
	@Override
	public List<AppointEntity> queryList(Map<String, Object> map){
		return appointDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return appointDao.queryTotal(map);
	}
	
	@Override
	public void save(AppointEntity appoint){
		appointDao.save(appoint);
		timeDao.addPeople(appoint.getTimeId());
	}
	
	@Override
	public void update(AppointEntity appoint){
		appointDao.update(appoint);
	}
	
	@Override
	public void delete(Integer id){
		appointDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		appointDao.deleteBatch(ids);
	}

	@Override
	public List<Map<String, String>> queryAppointCount() {
		return appointDao.queryAppointCount();
	}
	
}
