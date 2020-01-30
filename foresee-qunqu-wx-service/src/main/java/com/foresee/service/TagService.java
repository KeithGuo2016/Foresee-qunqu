package com.foresee.service;

import java.util.List;

import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.Tag;
import com.foresee.vo.TagVo;

public interface TagService extends BasicsSvc<Tag>{
	public List<TagVo> selectArticlesTagLike(int userid);
	public List<TagVo> selectCommunitysTagLike(int userid);
	public List<TagVo> selectContributeTagLike(int userid);
	public List<TagVo> selectMagazineTagLike(int userid);
	
	

}
