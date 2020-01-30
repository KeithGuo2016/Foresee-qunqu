package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.pojo.Communitys;
import com.foresee.utils.MyMapper;

public interface CommunitysMapper extends MyMapper<Communitys> {
	
	public List<Communitys> selectListByType();
	public List<Communitys> selectApllyCommunity(@Param("userid")int userid);
}