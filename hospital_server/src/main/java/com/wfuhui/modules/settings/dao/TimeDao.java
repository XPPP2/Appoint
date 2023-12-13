package com.wfuhui.modules.settings.dao;

import com.wfuhui.modules.settings.entity.TimeEntity;
import com.wfuhui.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TimeDao extends BaseDao<TimeEntity> {

	void delPeople(Integer timeId);
	
	void addPeople(Integer timeId);
	
}
