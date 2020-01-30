package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.TagMapperCostom;
import com.foresee.pojo.Tag;
import com.foresee.service.TagService;
import com.foresee.vo.TagVo;

@Service
public class TagServiceImpl extends BasicsSvcImpl<Tag> implements TagService{
	@Autowired
	private TagMapperCostom costom;

	@Override
	public List<TagVo> selectArticlesTagLike(int userid) {
		
		return costom.selectArticlesTagLike(userid);
	}

	@Override
	public List<TagVo> selectCommunitysTagLike(int userid) {
		
		return costom.selectCommunitysTagLike(userid);
	}

	@Override
	public List<TagVo> selectContributeTagLike(int userid) {
		
		return costom.selectContributeTagLike(userid);
	}

	@Override
	public List<TagVo> selectMagazineTagLike(int userid) {
		
		return costom.selectMagazineTagLike(userid);
	}
}
