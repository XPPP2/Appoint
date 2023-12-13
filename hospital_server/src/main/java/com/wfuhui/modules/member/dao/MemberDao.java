package com.wfuhui.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wfuhui.modules.member.entity.MemberEntity;
import com.wfuhui.modules.sys.dao.BaseDao;



@Mapper
public interface MemberDao extends BaseDao<MemberEntity> {

	MemberEntity queryByLoginName(String loginName);
}
