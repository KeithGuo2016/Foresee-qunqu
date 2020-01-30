package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.CommunitysMapper;
import com.foresee.mapper.CommunitysMapperCostom;
import com.foresee.pojo.Communitys;
import com.foresee.service.CommunitysService;
import com.foresee.vo.CommunityVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CommunitysServiceImpl extends BasicsSvcImpl<Communitys> implements CommunitysService{

	@Autowired
	private CommunitysMapperCostom costom;
	@Autowired
	private CommunitysMapper communitysMapper;
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public List<CommunityVo> selectHome() {
		PageHelper.startPage(1, 3);
		
		List<CommunityVo> list =costom.selectHome();
		new PageInfo<CommunityVo>(list);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public List<Communitys> selectListByType() {
		return communitysMapper.selectListByType();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public CommunityVo selectCommunityVoById(int id,String userid) {
		return costom.selectCommunityVoById(id,userid);
	}

	@Override
	public PageInfo<CommunityVo> selectFollowList(int userid,int page,int pageSize) {
		PageHelper.startPage(page, pageSize);
		
		List<CommunityVo> list =costom.selectFollowList(userid);
		PageInfo<CommunityVo> pageInfo = new PageInfo<CommunityVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<CommunityVo> searchCommunitys(String tagId, String searchDesc, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		
		List<CommunityVo> list =costom.searchCommunitys(tagId, searchDesc);
		PageInfo<CommunityVo> pageInfo = new PageInfo<CommunityVo>(list);
		return pageInfo;
	}

	@Override
	public List<Communitys> selectApllyCommunity(int userid) {
		
		return communitysMapper.selectApllyCommunity(userid);
	}

}
