package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.ContributesMapper;
import com.foresee.mapper.ContributesMapperCostom;
import com.foresee.pojo.Contributes;
import com.foresee.service.ContributesService;
import com.foresee.vo.ContributesVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ContributesServiceImpl  extends BasicsSvcImpl<Contributes> implements ContributesService{

	@Autowired
	private ContributesMapper contributesMapper;
	@Autowired
	private ContributesMapperCostom contributesMapperCostom;
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageInfo<Contributes> selectAllList(String communityId,int page,int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Contributes> list = contributesMapper.selectAllList(communityId);
		PageInfo<Contributes> pageInfo = new PageInfo<Contributes>(list);
		return pageInfo;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageInfo<ContributesVo> selectAllListHome(String communityId,int page,int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ContributesVo> list = contributesMapperCostom.selectAllListHome(communityId);
		PageInfo<ContributesVo> pageInfo = new PageInfo<ContributesVo>(list);
		return pageInfo;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public ContributesVo selectVoById(int id) {
		
		return contributesMapperCostom.selectVoById(id);
	}

	@Override
	public PageInfo<ContributesVo> searchContributes(String tagId, String searchDesc, String communityId, int page,
			int pageSize) {
		
		PageHelper.startPage(page, pageSize);
		List<ContributesVo> list = contributesMapperCostom.searchContributes(tagId, searchDesc, communityId);
		PageInfo<ContributesVo> pageInfo = new PageInfo<ContributesVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<ContributesVo> selectListCommunityId(int communityId, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ContributesVo> list = contributesMapperCostom.selectListCommunityId(communityId);
		PageInfo<ContributesVo> pageInfo = new PageInfo<ContributesVo>(list);
		return pageInfo;
	}

}
