package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.pojo.Contributes;
import com.foresee.utils.MyMapper;

public interface ContributesMapper extends MyMapper<Contributes> {
	public List<Contributes> selectAllList(@Param("communityId")String communityId);
}