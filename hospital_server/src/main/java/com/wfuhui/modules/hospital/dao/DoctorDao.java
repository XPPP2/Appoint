package com.wfuhui.modules.hospital.dao;

import com.wfuhui.modules.hospital.entity.DoctorEntity;
import com.wfuhui.modules.sys.dao.BaseDao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorDao extends BaseDao<DoctorEntity> {
	
}
