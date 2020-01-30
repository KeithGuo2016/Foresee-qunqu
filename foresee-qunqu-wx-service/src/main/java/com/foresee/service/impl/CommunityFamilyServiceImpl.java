package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.CommunityFamilyMapper;
import com.foresee.pojo.CommunityFamily;
import com.foresee.service.CommunityFamilyService;

@Service
public class CommunityFamilyServiceImpl extends BasicsSvcImpl<CommunityFamily> implements CommunityFamilyService{
	@Autowired
	private CommunityFamilyMapper mapper;
	@Override
	public List<CommunityFamily> selectListHaveCommunity() {
		return mapper.selectListHaveCommunity();
	}

}
