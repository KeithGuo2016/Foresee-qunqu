package com.foresee.service;

import java.util.List;

import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.CommunityFamily;

public interface CommunityFamilyService extends BasicsSvc<CommunityFamily> {
	List<CommunityFamily> selectListHaveCommunity();
}
