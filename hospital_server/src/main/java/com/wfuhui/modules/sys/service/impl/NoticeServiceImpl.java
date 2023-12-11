package com.wfuhui.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wfuhui.modules.sys.dao.NoticeDao;
import com.wfuhui.modules.sys.entity.NoticeEntity;
import com.wfuhui.modules.sys.service.NoticeService;



@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public NoticeEntity queryObject(Integer id){
		return noticeDao.queryObject(id);
	}
	
	@Override
	public List<NoticeEntity> queryList(Map<String, Object> map){
		return noticeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return noticeDao.queryTotal(map);
	}
	
	@Override
	public void save(NoticeEntity notice){
		noticeDao.save(notice);
	}
	
	@Override
	public void update(NoticeEntity notice){
		noticeDao.update(notice);
	}
	
	@Override
	public void delete(Integer id){
		noticeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		noticeDao.deleteBatch(ids);
	}
	
}
