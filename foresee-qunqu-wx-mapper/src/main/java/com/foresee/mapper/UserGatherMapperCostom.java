package com.foresee.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.utils.MyMapper;
import com.foresee.vo.UserGatherVo;

public interface UserGatherMapperCostom extends MyMapper<UserGatherVo> {
	public List<UserGatherVo> selectMyGather(@Param("userid")int userid);
}