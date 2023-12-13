package com.wfuhui.modules.settings.dao;

import com.wfuhui.modules.settings.entity.SettingEntity;
import com.wfuhui.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SettingDao extends BaseDao<SettingEntity> {
	
}
