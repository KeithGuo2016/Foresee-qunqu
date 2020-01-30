package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.utils.MyMapper;
import com.foresee.vo.ContributeDeliveryVo;

public interface ContributeDeliveryMapperCostom extends MyMapper<ContributeDeliveryVo> {
	
	public List<ContributeDeliveryVo> selectByContributesId( @Param("contributesId")int contributesId);
	
	public ContributeDeliveryVo selectVoById( @Param("id")int id);
}