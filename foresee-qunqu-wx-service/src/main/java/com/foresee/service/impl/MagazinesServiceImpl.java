package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.MagazinesMapperCostom;
import com.foresee.pojo.Magazines;
import com.foresee.service.MagazinesService;
import com.foresee.vo.MagazinesVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MagazinesServiceImpl extends BasicsSvcImpl<Magazines> implements MagazinesService{

	@Autowired
	private MagazinesMapperCostom costom;
	@Override
	public PageInfo<MagazinesVo> selectList(int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		
		List<MagazinesVo> list =costom.selectList();
		PageInfo<MagazinesVo> pageInfo = new PageInfo<MagazinesVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<MagazinesVo> selectByCommunitysId(int communitysId, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		
		List<MagazinesVo> list =costom.selectByCommunitysId(communitysId);
		PageInfo<MagazinesVo> pageInfo = new PageInfo<MagazinesVo>(list);
		return pageInfo;
	}

	@Override
	public PageInfo<MagazinesVo> searchMagazine(String tagId, String searchDesc, String communitysId, int page,
			int pageSize) {
		PageHelper.startPage(page, pageSize);
		
		List<MagazinesVo> list =costom.searchMagazine(tagId, searchDesc, communitysId);
		PageInfo<MagazinesVo> pageInfo = new PageInfo<MagazinesVo>(list);
		return pageInfo;
	}

	@Override
	public MagazinesVo selectById(int magazinesid) {
		
		return costom.selectById(magazinesid);
	}
	

}
