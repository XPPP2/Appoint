package com.wfuhui.modules.sys.service;

import com.wfuhui.modules.sys.entity.NoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * 公告
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
public interface NoticeService {
	
	NoticeEntity queryObject(Integer id);
	
	List<NoticeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(NoticeEntity notice);
	
	void update(NoticeEntity notice);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
