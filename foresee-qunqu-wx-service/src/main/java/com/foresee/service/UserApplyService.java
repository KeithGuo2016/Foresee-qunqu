package com.foresee.service;

import java.util.List;



import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.UserApply;

import com.foresee.vo.UserApplyVo;
import com.github.pagehelper.PageInfo;

public interface UserApplyService extends BasicsSvc<UserApply>{

	List<UserApplyVo> selectApplyTargetList(int applyTargetId,String applyType);
	
	PageInfo<UserApplyVo> selectMeApply(int userid,int page, int pageSize);
	
	public List<UserApplyVo> selectAddCommunityApply(int userid);
	PageInfo<UserApplyVo> selectMeApplyAll(int userid,int page, int pageSize);
	
}
