package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.UserApplyMapperCostom;
import com.foresee.pojo.UserApply;
import com.foresee.service.UserApplyService;
import com.foresee.vo.UserApplyVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class UserApplyServiceImpl extends BasicsSvcImpl<UserApply> implements UserApplyService{

	@Autowired
	private UserApplyMapperCostom costom;
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<UserApplyVo> selectApplyTargetList(int applyTargetId, String applyType) {
		return costom.selectApplyTargetList(applyTargetId, applyType);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageInfo<UserApplyVo> selectMeApply(int userid, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<UserApplyVo> list =costom.selectMeApply(userid);
		PageInfo<UserApplyVo> pageInfo = new PageInfo<UserApplyVo>(list);
		return pageInfo;
	}

	@Override
	public List<UserApplyVo> selectAddCommunityApply(int userid) {
		return costom.selectAddCommunityApply(userid);
	}

	@Override
	public PageInfo<UserApplyVo> selectMeApplyAll(int userid, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<UserApplyVo> list =costom.selectMeApplyAll(userid);
		PageInfo<UserApplyVo> pageInfo = new PageInfo<UserApplyVo>(list);
		return pageInfo;
	}

}
