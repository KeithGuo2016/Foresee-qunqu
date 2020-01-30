package com.foresee.mapper;

import java.util.List;

import com.foresee.pojo.CommunityFamily;
import com.foresee.utils.MyMapper;

public interface CommunityFamilyMapper extends MyMapper<CommunityFamily> {
	public List<CommunityFamily> selectListHaveCommunity();
}