package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.utils.MyMapper;
import com.foresee.vo.UserNewsVo;

public interface UserNewsMapperCostom extends MyMapper<UserNewsVo> {
	
	public List<UserNewsVo> selectMyNews(@Param("userid")int userid);
}