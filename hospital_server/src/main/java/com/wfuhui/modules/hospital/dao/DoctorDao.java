package com.wfuhui.modules.hospital.dao;

import com.wfuhui.modules.hospital.entity.DoctorEntity;
import com.wfuhui.modules.sys.dao.BaseDao;

import org.apache.ibatis.annotations.Mapper;

/**
 * 医生
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 * @date 2021-01-11 21:15:29
 */
@Mapper
public interface DoctorDao extends BaseDao<DoctorEntity> {
	
}
