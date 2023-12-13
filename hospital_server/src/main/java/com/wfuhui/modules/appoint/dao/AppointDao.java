package com.wfuhui.modules.appoint.dao;

import com.wfuhui.modules.appoint.entity.AppointEntity;
import com.wfuhui.modules.sys.dao.BaseDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AppointDao extends BaseDao<AppointEntity> {

	List<Map<String, String>> queryAppointCount();
	
}
