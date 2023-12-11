package com.wfuhui.modules.appoint.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wfuhui.modules.appoint.dao.CaseDao;
import com.wfuhui.modules.appoint.entity.CaseEntity;
import com.wfuhui.modules.appoint.service.CaseService;



@Service("caseService")
public class CaseServiceImpl implements CaseService {
	@Autowired
	private CaseDao caseDao;
	
	@Override
	public CaseEntity queryObject(Integer id){
		return caseDao.queryObject(id);
	}
	
	@Override
	public List<CaseEntity> queryList(Map<String, Object> map){
		return caseDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return caseDao.queryTotal(map);
	}
	
	@Override
	public void save(CaseEntity cas){
		caseDao.save(cas);
	}
	
	@Override
	public void update(CaseEntity cas){
		caseDao.update(cas);
	}
	
	@Override
	public void delete(Integer id){
		caseDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		caseDao.deleteBatch(ids);
	}
	
}
